package com.thesis.recommenderapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.service.WatchedService;

@Controller
public class AddToWatchListController {

    @Autowired
    private WatchedService watchedService;

    @RequestMapping(value = "addToWatchList", method = RequestMethod.POST)
    public String addToWatchList(AddToWatchListItem addToWatchListItem, Principal principal) {
        watchedService.saveWatched(addToWatchListItem, principal.getName());
        return "forward:watch_list?page=1";
    }

}
