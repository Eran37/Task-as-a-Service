package com.example.taas.advice;


import com.example.taas.dto.ErrDto;
import com.example.taas.exceptions.BusinessLogicException;
import com.example.taas.exceptions.TaskSecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TaskControllerAdvice {
    //security exceptions 401\403\409 and more
    @ExceptionHandler(value = {TaskSecurityException.class})
    public ResponseEntity<?> handleSecurityException(TaskSecurityException e) {
        return new ResponseEntity<>(e.getSecurityMsg().getMsg(), e.getSecurityMsg().getStatus());

    }

    // handling business-login exceptions: 400 usually
    @ExceptionHandler(value = {BusinessLogicException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDto handleException(Exception e) {
        return new ErrDto(e.getMessage());
    }

    // handling controllers exceptions -> Validation exceptions:
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
