package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailAddress {

    @Email(message = "Not a valid email address")
    private String email;

}
