package com.practice.validations;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SpecialException extends RuntimeException {
	
	private final Map<String,List<Object>> msg;
	private final Integer httpStatus;
	
	public SpecialException(Map<String,List<Object>> msg, Integer httpStatus){
		this.httpStatus = httpStatus;
		this.msg = msg;
	}
}
