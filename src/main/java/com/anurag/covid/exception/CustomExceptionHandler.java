package com.anurag.covid.exception;

import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AutoConfigureAfter
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {ServerDownException.class})
	public ResponseEntity<Object> handleUserNotFoundException(ServerDownException exception, WebRequest request){
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Author","Anurag Upadhyay");
		return new ResponseEntity<Object>(new CustomException(exception.getMessage(), request.getDescription(false)), headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request){
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Author","Anurag Upadhyay");
		return new ResponseEntity<Object>(new CustomException(exception.getMessage(), request.getDescription(false)), headers, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult=ex.getBindingResult();
		
		String errorMessage=bindingResult.getAllErrors()
				.stream().
				map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Author","Anurag Upadhyay");
		return new ResponseEntity<Object>(new CustomException("Validation failed.", errorMessage), headers, HttpStatus.BAD_REQUEST);
	}
	
	

}
