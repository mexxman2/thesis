package com.thesis.recommenderapp.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
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
    public String registerForm() {
        return "register";
    }

    @RequestMapping(value = "register", params = "friendId")
    public String registerFriendForm(@RequestParam String friendId, HttpServletResponse response) {
        response.addCookie(new Cookie("friendId", friendId));
        return "register";
    }

    @RequestMapping(value = "registerUserPost", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("registerUser") @Valid RegistrationRequest registrationRequest,
                               BindingResult bindingResult, @CookieValue("friendId") String friendId, HttpServletRequest request) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "register";
        } else {
            result = registerUserIfNotYetPresent(registrationRequest, bindingResult, friendId, request);
        }
        return result;
    }

    private String registerUserIfNotYetPresent(RegistrationRequest registrationRequest, BindingResult bindingResult,
                                               String friendId, HttpServletRequest request) {
        String result;
        try {
            Long id = userService.registerUser(registrationRequest);
            result = "redirect:index";
            addFriendIfNeeded(friendId, id);
            request.login(registrationRequest.getUserName(), registrationRequest.getPassword());
        } catch (UsernameAlreadyExistsException e) {
            bindingResult.rejectValue("userName", "error.userAlreadyExists", "Username already exists.");
            result = "register";
        } catch (ServletException e) {
            result = "redirect:login";
        }
        return result;
    }

    private void addFriendIfNeeded(String friendId, Long id) {
        if (friendId != null) {
            userService.addFriend(Long.valueOf(friendId), id);
        }
    }

}
