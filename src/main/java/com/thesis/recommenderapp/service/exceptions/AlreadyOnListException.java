package com.thesis.recommenderapp.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Item already in list")
public class AlreadyOnListException extends RuntimeException {
}
