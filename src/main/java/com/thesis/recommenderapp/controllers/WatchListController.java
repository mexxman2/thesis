package com.thesis.recommenderapp.controllers;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;
import com.thesis.recommenderapp.service.UserService;
import com.thesis.recommenderapp.service.WatchedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

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
    public String watchList(Model model, @RequestParam Integer page, Principal principal,
                            @PageableDefault(sort = "item.title", direction = Sort.Direction.ASC) Pageable watchedPageable) {
        User user = userService.getUserByUserName(principal.getName());
        Page<Watched> watchedList = watchedService.getWatchedList(user.getId(), watchedPageable);
        addAttributes(model, page, watchedList.getContent());
        return "watch_list";
    }

    @RequestMapping(value = "watch_list", params = {"userId", "page"})
    public String otherUserWatchList(Model model, @RequestParam Long userId, @RequestParam Integer page, Principal principal,
                                     @PageableDefault(sort = "item.title", direction = Sort.Direction.ASC) Pageable watchedPageable) {
        User user = userService.getUser(userId);
        User currentUser = userService.getUserByUserName(principal.getName());
        Page<Watched> watchedList = watchedService.getWatchedList(user.getId(), watchedPageable);
        addUserAttributesIfNotSameAsCurrentUser(model, user, currentUser);
        addAttributes(model, page, watchedList.getContent());
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
        model.addAttribute("hasNext", (page * 10) < watchedList.size());
        model.addAttribute("hasPrev", page > 1);
    }

}
