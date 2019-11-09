package com.thesis.recommenderapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thesis.recommenderapp.domain.SearchString;
import com.thesis.recommenderapp.domain.UploadItemRequest;
import com.thesis.recommenderapp.service.ItemService;
import com.thesis.recommenderapp.service.exceptions.SearchReturnedErrorException;
import com.thesis.recommenderapp.service.exceptions.ShouldBeMoreSpecificException;

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

    @RequestMapping(value = "upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "uploadItemPost", method = RequestMethod.POST)
    public String uploadItemPost(@ModelAttribute("uploadItem") @Valid UploadItemRequest uploadItemRequest, BindingResult bindingResult) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "upload";
        } else {
            result = trySaveItem(uploadItemRequest, bindingResult);
        }
        return result;
    }

    private String trySaveItem(@ModelAttribute("uploadItem") @Valid UploadItemRequest uploadItemRequest, BindingResult bindingResult) {
        String result;
        try {
            Long id = itemService.saveItem(uploadItemRequest);
            result = "redirect:details?itemId=" + id;
        } catch (SearchReturnedErrorException e) {
            bindingResult.rejectValue("titleOrURL", "error.notFound", "I couldn't find that item on imdb");
            result = "upload";
        } catch (ShouldBeMoreSpecificException e) {
            bindingResult.rejectValue("titleOrURL", "error.notSpecificEnough", "Please add a more specific title");
            result = "upload";
        }
        return result;
    }

}
