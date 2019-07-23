package com.ubiqube.etsi.mano.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<ProblemDetails> handleRunTimeException(RuntimeException e) {
		LOG.error("Exception", e);
		final ProblemDetails problemDetails = new ProblemDetails(500, e.getMessage());
		return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<ProblemDetails> handleWebApplicationException(ResponseStatusException e) {
		LOG.error("Exception", e);
		final ProblemDetails problemDetails = new ProblemDetails(e.getStatus().value(), e.getReason());
		return new ResponseEntity<>(problemDetails, e.getStatus());
	}
}
