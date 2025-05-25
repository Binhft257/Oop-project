package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.model.ErrorResonseDTO;

@ControllerAdvice
public class cotrollerAdviser {
	 @ExceptionHandler(customexception.FieldRequiredException.class)
		public ResponseEntity<Object> FieldRequiredException(
				customexception.FieldRequiredException ex, WebRequest request){  // method nay se dc tu dong dc goi neu co exception khi run
			ErrorResonseDTO errorResponse= new ErrorResonseDTO();
			errorResponse.setError(ex.getMessage());
			List<String> details= new ArrayList<>();
			details.add("check lai brand");
			errorResponse.setDetail(details);
			return new ResponseEntity<>(errorResponse,HttpStatus.BAD_GATEWAY);// cau truc cua ResponEntity(body(json),http)
		}
}
