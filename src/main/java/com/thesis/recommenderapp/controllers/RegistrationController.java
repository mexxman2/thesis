package com.thesis.recommenderapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesis.recommenderapp.domain.RegistrationRequest;
import com.thesis.recommenderapp.service.UserService;
import com.thesis.recommenderapp.service.exceptions.UsernameAlreadyExistsException;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("registerUser")
    public RegistrationRequest createRegistrationRequestModel() {
        return new RegistrationRequest();
    }

    @RequestMapping(value = "register")
    public String registerForm(HttpSession httpSession) {
        return "register";
    }

    @RequestMapping(value = "register", params = "friendId")
    public String registerFriendForm(@RequestParam Long friendId, HttpSession httpSession) {
        httpSession.setAttribute("friendId", friendId);
        return "register";
    }

    @RequestMapping(value = "registerUserPost", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("registerUser") @Valid RegistrationRequest registrationRequest,
                               BindingResult bindingResult, HttpSession httpSession) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "register";
        } else {
            Long id = (Long) httpSession.getAttribute("friendId");
            result = registerUserIfNotYetPresent(registrationRequest, bindingResult, id);
        }
        return result;
        //TODO: login after register.
    }

    private String registerUserIfNotYetPresent(RegistrationRequest registrationRequest, BindingResult bindingResult, Long friendId) {
        String result;
        try {
            Long id = userService.registerUser(registrationRequest);
            result = "redirect:index";
            if (friendId != null) {
                userService.addFriend(friendId, id);
            }
        } catch (UsernameAlreadyExistsException e) {
            bindingResult.rejectValue("userName", "error.userAlreadyExists", "Username already exists.");
            result = "register";
        }
        return result;
    }

}
