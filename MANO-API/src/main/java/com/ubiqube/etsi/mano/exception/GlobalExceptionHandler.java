package com.ubiqube.etsi.mano.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

/**
 * There is a problem in content negotiation, Spring will refuse to return
 * something else than the accept from the client, leading to a
 * NOT_ACCEPTABLE_CONTANT (406) error.
 *
 * @see https://github.com/spring-projects/spring-framework/issues/20865
 * @author olivier
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ProblemDetails> handleRunTimeException(final RuntimeException e) {
		LOG.error("Exception", e);
		final ProblemDetails problemDetails = new ProblemDetails(500, e.getMessage());
		return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<ProblemDetails> handleWebApplicationException(final ResponseStatusException e) {
		LOG.error("Exception", e);
		final ProblemDetails problemDetails = new ProblemDetails(e.getStatus().value(), e.getReason());
		return new ResponseEntity<>(problemDetails, e.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException e, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final ProblemDetails problemDetails = new ProblemDetails(status.value(), e.getMessage());
		return new ResponseEntity<>(problemDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException e, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final ProblemDetails problemDetails = new ProblemDetails(status.value(), e.getMessage());
		return new ResponseEntity<>(problemDetails, status);
	}

}
