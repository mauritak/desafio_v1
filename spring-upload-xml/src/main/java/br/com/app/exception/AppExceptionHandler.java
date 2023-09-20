package br.com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public final ResponseEntity<ExceptionResponse> naoEncontradoException(RegistroNaoEncontradoException ex,
			WebRequest request) {

		ExceptionResponse build = ExceptionResponse.builder().message(ex.getMessage()).build();
		return ResponseEntity.status(HttpStatus.OK).body(build);
	}

}
