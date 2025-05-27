package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.model.ErrorResonseDTO;

import customexception.FieldRequiredException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequired(FieldRequiredException ex, WebRequest request) {
        ErrorResonseDTO errorResponse = new ErrorResonseDTO();
        errorResponse.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Vui lòng kiểm tra lại dữ liệu đầu vào (ví dụ: brand không chứa số).");
        errorResponse.setDetail(details);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
