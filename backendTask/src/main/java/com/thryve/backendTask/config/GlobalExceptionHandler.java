package com.thryve.backendTask.config;

import com.thryve.backendTask.util.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {


    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<String> handleCustomException(CustomException customException) {
        return new ResponseEntity<>(customException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
