package com.thesis.recommenderapp.controllers;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private RecommendationService recommendationService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @RequestMapping(value = {"/", "index"})
    public String index(Model model) {
        model.addAttribute("topTenItems", recommendationService.getTopTenPopularItems());
        return "index";
    }

}
