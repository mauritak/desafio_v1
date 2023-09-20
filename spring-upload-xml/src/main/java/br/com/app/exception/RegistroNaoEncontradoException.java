package br.com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 7195138072520842587L;

	public RegistroNaoEncontradoException(String message) {
		super(message);
	}

	public RegistroNaoEncontradoException(Exception exception) {
		super(exception);
	}
}
