/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.LccnSubscriptionRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/sol005/nslcm/v1/subscriptions")
@Api(value = "/sol005/nslcm/v1/subscriptions")
public interface NsLcmSubscriptionsSol005 {

	/**
	 * Query multiple subscriptions.
	 *
	 * Query Subscription Information. The GET method queries the list of active
	 * subscriptions of the functional block that invokes the method. It can be used
	 * e.g. for resynchronization after error situations.
	 *
	 */
	@ApiOperation(value = "Query multiple subscriptions.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The list of subscriptions was queried successfully. The response body shall contain the representations of all active subscriptions of the functional block that invokes the method. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request. Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<List<LccnSubscription>> subscriptionsGet(@RequestHeader("Accept") String accept);

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
	@ApiOperation(value = "Subscribe to NS lifecycle change notifications.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "201 Created The subscription was created successfully. The response body shall contain a representation of the created subscription resource. The HTTP response shall include a \"Location:\" HTTP header that points to the created subscription resource.             ", response = LccnSubscription.class), @ApiResponse(code = 303, message = "See Other. A subscription with the same callbackURI and the same filter already exits and the policy of the NFVO is to not create redundant subscriptions. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the existing subscription resource. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<LccnSubscription> subscriptionsPost(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, @Valid LccnSubscriptionRequest body);

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Tables 6.4.17.3.5-1 and 6.4.17.3.5-2.
	 *
	 */
	@ApiOperation(value = "Terminate a subscription.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The subscription resource was deleted successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@DeleteMapping(value = "/{subscriptionId}", consumes = { "application/json" }, produces = { "application/json" })
	void subscriptionsSubscriptionIdDelete(@PathVariable("subscriptionId") String subscriptionId);

	/**
	 * Read an individual subscription resource.
	 *
	 * The GET method retrieves information about a subscription by reading an
	 * individual subscription resource. This method shall support the URI query
	 * parameters, request and response data structures, and response codes, as
	 * specified in the Tables 6.4.17.3.2-1 and 6.4.17.3.2-2
	 *
	 */
	@ApiOperation(value = "Read an individual subscription resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The operation has completed successfully. The response body shall contain a representation of the subscription resource.             ", response = LccnSubscription.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  Specifically in case of this task resource, the reason can also be that the task is not supported for the NS LCM operation occurrence represented by the parent resource, and that the task resource consequently does not exist. The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(value = "/{subscriptionId}", consumes = { "application/json" }, produces = { "application/json" })
	ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(@PathVariable("subscriptionId") String subscriptionId, @RequestHeader("Accept") String accept);

}
