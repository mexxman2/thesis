package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UploadItemRequest {

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private String description;
    @NotEmpty(message = "Type cannot be empty")
    private String type;
    private Integer numberOfSeasons;
    private Integer numberOfEpisodes;

}
