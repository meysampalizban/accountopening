package com.practice.validations;

import com.practice.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
		Map<String,List<Object>> messages =
				exception.getBindingResult().getFieldErrors().stream().
						collect(Collectors.toMap(T -> T.getField()
								,S -> new ArrayList<>(List.of(S.getDefaultMessage()))
								,(r,n) -> {
									r.addAll(n);
									return r;
								}));
		
		Response res = Response.builder().
				msg(messages).statusCode(HttpStatus.BAD_REQUEST).timeStamp(Instant.now()).build();
		
		return new ResponseEntity<>(res,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SpecialException.class)
	public ResponseEntity<Response> SpecialExceptionHandler(SpecialException specialException){
		Response res = Response.builder().msg(specialException.getMsg()).statusCode(specialException.getHttpStatus()).timeStamp(Instant.now()).build();
		return new ResponseEntity<>(res,new HttpHeaders(),specialException.getHttpStatus());
	}
}
