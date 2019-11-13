package com.thesis.recommenderapp.controllers;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;
import com.thesis.recommenderapp.service.UserService;
import com.thesis.recommenderapp.service.WatchedService;
import lombok.extern.slf4j.Slf4j;
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

@Controller
@Slf4j
public class WatchListController {

    @Autowired
    private WatchedService watchedService;
    @Autowired
    private UserService userService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping(value = "watch_list")
    public String watchList(Model model, Principal principal,
                            @PageableDefault(sort = "item.title", direction = Sort.Direction.ASC) Pageable watchedPageable) {
        User user = userService.getUserByUserName(principal.getName());
        Page<Watched> watchedPage = watchedService.getWatchedList(user.getId(), watchedPageable);
        watchedPage.getContent().forEach(watched -> log.info(watched.getItem().getTitle()));
        model.addAttribute("items", watchedPage.getContent());
        model.addAttribute("totalPages", watchedPage.getTotalPages());
        model.addAttribute("current", watchedPageable.getPageNumber());
        model.addAttribute("previous", watchedPageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", watchedPageable.next().getPageNumber());
        return "watch_list";
    }

    @RequestMapping(value = "watch_list", params = "userId")
    public String otherUserWatchList(Model model, @RequestParam Long userId, Principal principal,
                                     @PageableDefault(sort = "item.title", direction = Sort.Direction.ASC) Pageable watchedPageable) {
        User user = userService.getUser(userId);
        User currentUser = userService.getUserByUserName(principal.getName());
        Page<Watched> watchedPage = watchedService.getWatchedList(user.getId(), watchedPageable);
        watchedPage.getContent().forEach(watched -> log.info(watched.getItem().getTitle()));
        addUserAttributesIfNotSameAsCurrentUser(model, user, currentUser);
        model.addAttribute("items", watchedPage.getContent());
        model.addAttribute("totalPages", watchedPage.getTotalPages());
        model.addAttribute("current", watchedPageable.getPageNumber());
        model.addAttribute("previous", watchedPageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", watchedPageable.next().getPageNumber());
        return "watch_list";
    }

    private void addUserAttributesIfNotSameAsCurrentUser(Model model, User user, User currentUser) {
        if (!user.equals(currentUser)) {
            model.addAttribute("user", user);
            model.addAttribute("isFriend", currentUser.getFriends().contains(user));
        }
    }

}
