package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UploadItemRequest {

    @NotEmpty(message = "Title or URL cannot be empty")
    private String titleOrURL;
    @NotEmpty(message = "Type cannot be empty")
    private String type;

}
