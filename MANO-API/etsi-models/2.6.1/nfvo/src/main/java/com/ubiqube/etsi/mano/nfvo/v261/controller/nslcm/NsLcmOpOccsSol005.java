/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/sol005/nslcm/v1/ns_lcm_op_occs")
@Api(value = "/sol005/nslcm/v1/ns_lcm_op_occs")
public interface NsLcmOpOccsSol005 {

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
	@ApiOperation(value = "Query multiple NS LCM operation occurrences.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Status information for zero or more NS lifecycle management operation occurrences was queried successfully. The response body shall contain representations of zero or more NS instances, as defined in clause 5.5.2.13. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<String> nsLcmOpOccsGet(@RequestParam(value = "filter", required = false) String filter, @RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "exclude_fields", required = false) String excludeFields, @RequestParam(value = "exclude_default", required = false) String excludeDefault);

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
	@ApiOperation(value = "Continue a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. The general cause for this error and its handling is specified in clause 4.3.5.4, including rules for the presence of the response body. Specifically, in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent source, and that the task resource consequently does not exist. In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(value = "/{nsLcmOpOccId}/continue", consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdContinuePost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId);

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
	@ApiOperation(value = "Read an individual NS LCM operation occurrence resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about an individual NS instance was queried successfully. The response body shall contain status information about a NS lifecycle management operation occurrence (see clause 6.5.2.3). ", response = NsLcmOpOcc.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(value = "/{nsLcmOpOccId}", consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<NsLcmOpOcc> nsLcmOpOccsNsLcmOpOccIdGet(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId, @RequestHeader("Content-Type") String contentType);

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
	@ApiOperation(value = "Retry a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also be returned if the task is not supported for the NS LCM operation occurrence represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(value = "/{nsLcmOpOccId}/retry", consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRetryPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId);

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
	@ApiOperation(value = "Rollback a NS lifecycle management operation occurrence.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted The request was accepted for processing, but processing has not been completed. The response shall have an empty payload body. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. The general cause for this error and its handling is specified in clause 4.3.5.4, including rules for the presence of the response body. Specifically, in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent source, and that the task resource consequently does not exist. In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is not in FAILED_TEMP state, or another error handling action is starting, such as rollback or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(value = "/{nsLcmOpOccId}/rollback", consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRollbackPost(@PathVariable("nsLcmOpOccId") String nsLcmOpOccId);

}
