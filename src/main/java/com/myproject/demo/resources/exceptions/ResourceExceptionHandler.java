package com.myproject.demo.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;

@ControllerAdvice//->Interceptar as exceções que ocorrerem para que esse objeto possa executar um possivel tratamento
public class ResourceExceptionHandler {
	private StandardError err;

	//TRATAMENTO PERSONALIZADO DA EXCEPTION "ResourceNotFoundException"
	@ExceptionHandler(ResourceNotFoundException.class)//->Idicar que esse metodo irá interceptar qualquer exception do tipo que foi definido como parametro dessa annotation e fazer o tratamento que estiver dentro do metodo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //codigo error: 404
		
		err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		//status.value()-> Passar para inteiro/int
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
		
	}
}
