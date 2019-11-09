package com.thesis.recommenderapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import com.thesis.recommenderapp.domain.Watched;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.UserService;
import com.thesis.recommenderapp.service.WatchedService;

@Controller
public class WatchListController {

    @Autowired
    private WatchedService watchedService;
    @Autowired
    private UserService userService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping(value = "watch_list", params = "page")
    public String watchList(Model model, @RequestParam Integer page, Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        List<Watched> watchedList = watchedService.getWatchedList(user.getId());
        addAttributes(model, page, watchedList);
        return "watch_list";
    }

    @RequestMapping(value = "watch_list", params = {"userId", "page"})
    public String otherUserWatchList(Model model, @RequestParam Long userId, @RequestParam Integer page, Principal principal) {
        User user = userService.getUser(userId);
        User currentUser = userService.getUserByUserName(principal.getName());
        List<Watched> watchedList = watchedService.getWatchedList(user.getId());
        addUserAttributesIfNotSameAsCurrentUser(model, user, currentUser);
        addAttributes(model, page, watchedList);
        return "watch_list";
    }

    private void addUserAttributesIfNotSameAsCurrentUser(Model model, User user, User currentUser) {
        if (!user.equals(currentUser)) {
            model.addAttribute("user", user);
            model.addAttribute("isFriend", currentUser.getFriends().contains(user));
        }
    }

    private void addAttributes(Model model, Integer page, List<Watched> watchedList) {
        if (watchedList.size() > page * 10) {
            model.addAttribute("items", watchedList.subList((page - 1) * 10, page * 10));
        } else {
            model.addAttribute("items", watchedList.subList((page - 1) * 10, watchedList.size()));
        }
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("isNext", (page * 10) < watchedList.size());
        model.addAttribute("isPrev", page > 1);
    }

}
