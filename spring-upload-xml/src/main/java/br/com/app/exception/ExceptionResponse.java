package br.com.app.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
	private String message;

	public ExceptionResponse(String message) {
		super();
		this.message = message;
	}

}
