package com.thesis.recommenderapp.controllers;

import com.thesis.recommenderapp.dao.ItemDao;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.UploadItemRequest;
import com.thesis.recommenderapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ItemUploadController {

    @Autowired
    private ItemService itemService;

    @ModelAttribute("searchString")
    public SearchString createSearchStringModel() {
        return new SearchString();
    }

    @ModelAttribute("uploadItem")
    public UploadItemRequest createUploadItemRequestModel() {
        return new UploadItemRequest();
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "uploadItemPost", method = RequestMethod.POST)
    public String uploadItemPost(@ModelAttribute("uploadItem") @Valid UploadItemRequest uploadItemRequest, BindingResult bindingResult) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "upload";
        } else {
            itemService.uploadItem(uploadItemRequest);
            result = "redirect:index";
        }
        return result;
    }

}
