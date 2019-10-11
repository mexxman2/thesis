package com.thesis.recommenderapp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.UserService;

@Controller
public class FriendsListController {

    @Autowired
    private UserService userService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping("friends_list")
    public String friendsList(Model model, @RequestParam Integer page, Principal principal) {
        List<User> friends = userService.getFriends(principal.getName());
        addAttributes(model, page, friends);
        return "friends_list";
    }

    private void addAttributes(Model model, Integer page, List<User> friends) {
        if (friends.size() > page * 10) {
            model.addAttribute("items", friends.subList((page - 1) * 10, page * 10));
        } else {
            model.addAttribute("items", friends.subList((page - 1) * 10, friends.size()));
        }
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("isNext", (page * 10) < friends.size());
        model.addAttribute("isPrev", page > 1);
    }

}
