package com.thesis.recommenderapp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thesis.recommenderapp.domain.EmailAddress;
import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.EmailSenderService;
import com.thesis.recommenderapp.service.UserService;

@Controller
public class FriendsListController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService emailSenderService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @ModelAttribute("email")
    public EmailAddress createEmailAddressModel() {
        return new EmailAddress();
    }

    @RequestMapping("friends_list")
    public String friendsList(Model model, @RequestParam Integer page, Principal principal) {
        Set<User> friends = userService.getFriends(principal.getName());
        addAttributes(model, page, new ArrayList<>(friends));
        return "friends_list";
    }

    @RequestMapping("sendEmail")
    public String sendEmail(@ModelAttribute("email") EmailAddress email, BindingResult bindingResult,
                            Principal principal, Model model, HttpServletRequest request) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EmailAddress>> violations = validator.validate(email);
        if (violations.isEmpty()) {
            model.addAttribute("emailSent", true);
            User user = userService.getUserByUserName(principal.getName());
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();
            emailSenderService.sendInvite(email.getEmail(), user, baseUrl);
        } else {
            for (ConstraintViolation<EmailAddress> violation : violations) {
                bindingResult.reject(violation.getMessage(), violation.getMessage());
            }
        }
        return "forward:friends_list?page=1";
    }

    private void addAttributes(Model model, Integer page, List<User> friends) {
        if (friends.size() > page * 10) {
            model.addAttribute("friends", friends.subList((page - 1) * 10, page * 10));
        } else {
            model.addAttribute("friends", friends.subList((page - 1) * 10, friends.size()));
        }
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("isNext", (page * 10) < friends.size());
        model.addAttribute("isPrev", page > 1);
    }

}
