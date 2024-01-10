package com.mgiang2015.SpringVideoPlatform.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mgiang2015.SpringVideoPlatform.exception.CourseNotFoundException;

@ControllerAdvice
public class CourseNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(CourseNotFoundException exception) {
        return exception.getMessage();
    }
}
