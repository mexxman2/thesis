package com.thesis.recommenderapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.service.ItemService;

@Controller
public class DetailsController {

    @Autowired
    private ItemService itemService;

    @ModelAttribute("addToWatchListItem")
    public AddToWatchListItem createWatchListItemModel() {
        return new AddToWatchListItem();
    }

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping("/details")
    public String details(Model model, @RequestParam Long itemId) {
        Item item = itemService.getItem(itemId);
        boolean isMovie = item.getClass().getSimpleName().equals("Movie");
        model.addAttribute("item", item);
        model.addAttribute("isMovie", isMovie);
        return "details";
    }

}
