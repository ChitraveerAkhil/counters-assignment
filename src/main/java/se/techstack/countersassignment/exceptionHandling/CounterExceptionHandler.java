package se.techstack.countersassignment.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import se.techstack.countersassignment.vo.ResponseVO;

@ControllerAdvice
public class CounterExceptionHandler {

	@ExceptionHandler({ CounterNotFoundException.class })
	public ResponseEntity<ResponseVO<String>> handleNotFoundException(CounterNotFoundException e) {
		return formErrorResponse(HttpStatus.NOT_FOUND, e);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
	}

	private ResponseEntity<ResponseVO<String>> formErrorResponse(HttpStatus status, CounterNotFoundException e) {

		ResponseVO<String> responseBody = new ResponseVO<>(e.getMessage(), status);
		return ResponseEntity.status(status).body(responseBody);
	}
}
