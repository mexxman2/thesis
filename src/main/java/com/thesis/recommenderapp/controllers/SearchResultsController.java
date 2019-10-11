package com.thesis.recommenderapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.service.ItemService;
import com.thesis.recommenderapp.service.UserService;

@Controller
public class SearchResultsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping(value = "searchBySubstring", method = RequestMethod.POST)
    public String searchBySubstring(Model model, SearchString searchString) {
        model.addAttribute("items", itemService.getItemsBySubstring(searchString.getSearchSubstring()));
        model.addAttribute("users", userService.getUsersBySubstring(searchString.getSearchSubstring()));
        return "search_results";
    }

}
