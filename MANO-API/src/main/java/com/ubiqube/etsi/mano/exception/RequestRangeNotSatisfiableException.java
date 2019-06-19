package com.ubiqube.etsi.mano.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

public class RequestRangeNotSatisfiableException extends WebApplicationException {

	public RequestRangeNotSatisfiableException() {
		super();
	}

	public RequestRangeNotSatisfiableException(String _detail) {
		super(Response.serverError().status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).type(MediaType.APPLICATION_JSON_TYPE).entity(new ProblemDetails(416, _detail)).build());
	}
}
