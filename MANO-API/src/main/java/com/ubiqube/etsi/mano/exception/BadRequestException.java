package com.ubiqube.etsi.mano.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

public class BadRequestException extends WebApplicationException {

	public BadRequestException() { super(); }

	public BadRequestException(String _detail) {
		super(Response.serverError().status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).entity(new ProblemDetails(400, _detail)).build());
	}
}
