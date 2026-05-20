package com.farm_management.fms.common.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ApiErrorResponse {
    private final LocalDateTime timestamp ;
    private final int status ;
    private final String message ;
    private Map<String,Object> errors ;
    public ApiErrorResponse(int status , String message){
        timestamp = LocalDateTime.now();
        this.status = status ;
        this.message = message ;
    }
    public ApiErrorResponse(int status , String message , Map<String,Object> errors){
        timestamp = LocalDateTime.now();
        this.status = status ;
        this.message = message ;
        this.errors = errors;
    }
}
