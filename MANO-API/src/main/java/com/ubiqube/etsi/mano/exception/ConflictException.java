package com.ubiqube.etsi.mano.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

public class ConflictException extends WebApplicationException {

	public ConflictException() {
		super();
	}

	public ConflictException(String _detail) {
		super(Response.serverError().status(Status.CONFLICT).type(MediaType.APPLICATION_JSON_TYPE).entity(new ProblemDetails(409, _detail)).build());
	}
}
