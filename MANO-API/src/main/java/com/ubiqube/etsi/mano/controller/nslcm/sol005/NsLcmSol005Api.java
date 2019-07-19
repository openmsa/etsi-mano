package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse400;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierCreationNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierDeletionNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccIdGetResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOperationOccurrenceNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.SubscriptionsPost;
import com.ubiqube.etsi.mano.model.nslcm.sol005.SubscriptionsPostQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * SOL005 - NS Lifecycle Management Interface
 *
 * <p>
 * SOL005 - NS Lifecycle Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
//@Path("/sol005/nslcm/v1")
@RestController
@RequestMapping("/sol005/nslcm/v1")
@Api(value = "/")
public class NsLcmSol005Api implements NsLcmSol005 {

	/**
	 * Query multiple NS LCM operation occurrences.
	 *
	 * Get Operation Status. The client can use this method to query status
	 * information about multiple NS lifecycle management operation occurrences.
	 * This method shall follow the provisions specified in the Tables 6.4.9.3.2-1
	 * and 6.4.9.3.2-2 for URI query parameters, request and response data
	 * structures, and response codes.
	 *
	 */
	@Override
	@GET
	@Path("/ns_lcm_op_occs")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Query multiple NS LCM operation occurrences.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Status information for zero or more NS lifecycle management operation occurrences was queried successfully. The response body shall contain representations of zero or more NS instances, as defined in clause 5.5.2.13. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/ns_lcm_op_occs", consumes = { "application/json" }, produces = { "application/json" })
	public List<Object> nsLcmOpOccsGet(@HeaderParam("Accept") String accept, @QueryParam("filter") String filter, @QueryParam("fields") String fields, @QueryParam("exclude_fields") String excludeFields, @QueryParam("exclude_default") String excludeDefault) {
		return new ArrayList<>();
	}

	/**
	 * Continue a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates continuing an NS lifecycle operation if that
	 * operation has experienced a temporary failure, i.e. the related \&quot;NS LCM
	 * operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This
	 * method shall follow the provisions specified in the Tables 6.4.13.3.1-1 and
	 * 6.4.13.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	@POST
	@Path("/ns_lcm_op_occs/{nsLcmOpOccId}/continue")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Continue a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. The general cause for this error and its handling is specified in clause 4.3.5.4, including rules for the presence of the response body. Specifically, in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent source, and that the task resource consequently does not exist. In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}/continue", consumes = { "application/json" }, produces = { "application/json" })
	public void nsLcmOpOccsNsLcmOpOccIdContinuePost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Read an individual NS LCM operation occurrence resource.
	 *
	 * The client can use this method to retrieve status information about a NS
	 * lifecycle management operation occurrence by reading an individual \&quot;NS
	 * LCM operation occurrence\&quot; resource. This method shall follow the
	 * provisions specified in the Tables 6.4.10.3.2-1 and 6.4.10.3.2-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	@GET
	@Path("/ns_lcm_op_occs/{nsLcmOpOccId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Read an individual NS LCM operation occurrence resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about an individual NS instance was queried successfully. The response body shall contain status information about a NS lifecycle management operation occurrence (see clause 6.5.2.3). ", response = NsLcmOpOccsNsLcmOpOccIdGetResponse.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}", consumes = { "application/json" }, produces = { "application/json" })
	public NsLcmOpOccsNsLcmOpOccIdGetResponse nsLcmOpOccsNsLcmOpOccIdGet(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Retry a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates retrying a NS lifecycle management operation if
	 * that operation has experienced a temporary failure, i.e. the related
	 * \&quot;NS LCM operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot;
	 * state. This method shall follow the provisions specified in the Tables
	 * 6.4.11.3.1-1 and 6.4.11.3.1-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	@POST
	@Path("/ns_lcm_op_occs/{nsLcmOpOccId}/retry")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Retry a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also be returned if the task is not supported for the NS LCM operation occurrence represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}/retry", consumes = { "application/json" }, produces = { "application/json" })
	public void nsLcmOpOccsNsLcmOpOccIdRetryPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Rollback a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates rolling back a NS lifecycle operation if that
	 * operation has experienced a temporary failure, i.e. the related \&quot;NS LCM
	 * operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This
	 * method shall follow the provisions specified in the Tables 6.4.12.3.1-1 and
	 * 6.4.12.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	@POST
	@Path("/ns_lcm_op_occs/{nsLcmOpOccId}/rollback")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Rollback a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. The general cause for this error and its handling is specified in clause 4.3.5.4, including rules for the presence of the response body. Specifically, in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent source, and that the task resource consequently does not exist. In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}/rollback", consumes = { "application/json" }, produces = { "application/json" })
	public void nsLcmOpOccsNsLcmOpOccIdRollbackPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Cancel a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates canceling an ongoing NS lifecycle management
	 * operation while it is being executed or rolled back, i.e. the related
	 * \&quot;NS LCM operation occurrence\&quot; is either in
	 * \&quot;PROCESSING\&quot; or \&quot;ROLLING_BACK\&quot; state. This method
	 * shall follow the provisions specified in the Tables 6.4.15.3.1-1 and
	 * 6.4.15.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	@POST
	@Path("/nslcm/v1/ns_lcm_op_occs/{nsLcmOpOccId}/cancel")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Cancel a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also be returned if the task is not supported for the NS LCM operation occurrence represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "409 Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the NS LCM operation occurrence resource. Typically, this is due to the fact that the operation occurrence is not in STARTING, PROCESSING or ROLLING_BACK state. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}/cancel", consumes = { "application/json" }, produces = { "application/json" })
	public void nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery body) {
		// TODO: Implement...

	}

	/**
	 * Mark a NS lifecycle management operation occurrence as failed.
	 *
	 * The POST method marks a NS lifecycle management operation occurrence as
	 * \&quot;finally failed\&quot; if that operation occurrence is in
	 * \&quot;FAILED_TEMP\&quot; state.
	 *
	 */
	@Override
	@POST
	@Path("/nslcm/v1/ns_lcm_op_occs/{nsLcmOpOccId}/fail")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Mark a NS lifecycle management operation occurrence as failed.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. The general cause for this error and its handling is specified in clause 4.3.5.4, including rules for the presence of the response body. Specifically, in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent source, and that the task resource consequently does not exist. In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/ns_lcm_op_occs/{nsLcmOpOccId}/fail", consumes = { "application/json" }, produces = { "application/json" })
	public NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * Query Subscription Information. The GET method queries the list of active
	 * subscriptions of the functional block that invokes the method. It can be used
	 * e.g. for resynchronization after error situations.
	 *
	 */
	@Override
	@GET
	@Path("/subscriptions")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Query multiple subscriptions.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The list of subscriptions was queried successfully. The response body shall contain the representations of all active subscriptions of the functional block that invokes the method. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request. Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/subscriptions", consumes = { "application/json" }, produces = { "application/json" })
	public List<Object> subscriptionsGet(@HeaderParam("Accept") String accept) {
		return new ArrayList<>();
	}

	/**
	 * Subscribe to NS lifecycle change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 6.4.16.3.1-1 and 6.4.16.3.1-2. Creation of two
	 * subscription resources with the same callbackURI and the same filter can
	 * result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri).
	 *
	 */
	@Override
	@POST
	@Path("/subscriptions")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Subscribe to NS lifecycle change notifications.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "201 Created The subscription was created successfully. The response body shall contain a representation of the created subscription resource. The HTTP response shall include a \"Location:\" HTTP header that points to the created subscription resource.             ", response = SubscriptionsPost.class), @ApiResponse(code = 303, message = "See Other. A subscription with the same callbackURI and the same filter already exits and the policy of the NFVO is to not create redundant subscriptions. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the existing subscription resource. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/subscriptions", consumes = { "application/json" }, produces = { "application/json" })
	public SubscriptionsPost subscriptionsPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, SubscriptionsPostQuery body) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Tables 6.4.17.3.5-1 and 6.4.17.3.5-2.
	 *
	 */
	@Override
	@DELETE
	@Path("/subscriptions/{subscriptionId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Terminate a subscription.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The subscription resource was deleted successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@DeleteMapping(value = "/subscriptions/{subscriptionId}", consumes = { "application/json" }, produces = { "application/json" })
	public void subscriptionsSubscriptionIdDelete(@PathVariable("subscriptionId") String subscriptionId) {
		// TODO: Implement...

	}

	/**
	 * Read an individual subscription resource.
	 *
	 * The GET method retrieves information about a subscription by reading an
	 * individual subscription resource. This method shall support the URI query
	 * parameters, request and response data structures, and response codes, as
	 * specified in the Tables 6.4.17.3.2-1 and 6.4.17.3.2-2
	 *
	 */
	@Override
	@GET
	@Path("/subscriptions/{subscriptionId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Read an individual subscription resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The operation has completed successfully. The response body shall contain a representation of the subscription resource.             ", response = SubscriptionsPost.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/subscriptions/{subscriptionId}", consumes = { "application/json" }, produces = { "application/json" })
	public SubscriptionsPost subscriptionsSubscriptionIdGet(@PathVariable("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	@POST
	@Path("/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierCreationNotification")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Notify about NS lifecycle change", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The notification was delivered successfully. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierCreationNotification", consumes = { "application/json" }, produces = { "application/json" })
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierCreationNotificationPost(NsIdentifierCreationNotification nsIdentifierCreationNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType) {
		// TODO: Implement...

	}

	/**
	 * Test the notification endpoint.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@Override
	@GET
	@Path("/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierDeletionNotification")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Test the notification endpoint.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The notification endpoint was tested successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierDeletionNotification", consumes = { "application/json" }, produces = { "application/json" })
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationGet(@HeaderParam("Accept") String accept) {
		// TODO: Implement...

	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	@POST
	@Path("/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierDeletionNotification")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Notify about NS lifecycle change", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The notification was delivered successfully. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsIdentifierDeletionNotification", consumes = { "application/json" }, produces = { "application/json" })
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationPost(NsIdentifierDeletionNotification nsIdentifierDeletionNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType) {
		// TODO: Implement...

	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	@POST
	@Path("/URI_is_provided_by_the_client_when_creating_the_subscription-NsLcmOperationOccurrenceNotification")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Notify about NS lifecycle change", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content           The notification was delivered successfully. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsLcmOperationOccurrenceNotification", consumes = { "application/json" }, produces = { "application/json" })
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationPost(NsLcmOperationOccurrenceNotification nsLcmOperationOccurrenceNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType) {
		// TODO: Implement...

	}

}
