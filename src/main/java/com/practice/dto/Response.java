package com.practice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Response {
	private Instant timeStamp;
	private HttpStatus statusCode;
	private Map<String,List<Object>> msg;
}
