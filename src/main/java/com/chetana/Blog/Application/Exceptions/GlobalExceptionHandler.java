package com.chetana.Blog.Application.Exceptions;


import com.chetana.Blog.Application.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorisedException.class)
    public ResponseEntity<ApiResponse> unAuthorisedExceptionHandler(UnAuthorisedException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> beanDataException(MethodArgumentNotValidException ex)
    {
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
          String fieldName = ((FieldError)error).getField();
          String message = error.getDefaultMessage();
          map.put(fieldName,message);
        });

        return new ResponseEntity<Map<String, String>>(map,HttpStatus.BAD_REQUEST);
    }

   // @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> anyException(Exception ex)
    {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
        return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
