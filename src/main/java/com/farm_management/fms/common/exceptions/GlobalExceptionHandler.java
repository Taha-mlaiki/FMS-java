package com.farm_management.fms.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handelValidationError(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((err)-> errors.put(err.getField(),err.getDefaultMessage()));
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),"validation failed",errors);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse InvalidCredentialsException(InvalidCredentialsException ex){
        return new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse UserNotFoundExceptionHandler(UserNotFoundException ex){
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse EmailAlreadyExistExceptionHandler(EmailAlreadyExistException ex){
        return new ApiErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }
}
