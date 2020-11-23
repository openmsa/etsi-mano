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

/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ubiqube.etsi.mano.nfvo.v271.controller.nsfault;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v271.model.nsfault.FmSubscription;
import com.ubiqube.etsi.mano.nfvo.v271.model.nsfault.FmSubscriptionRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-04T21:37:40.971+02:00")

@Api(value = "subscriptions", description = "the subscriptions API")
@RequestMapping(value = "/sol005/nsfm/v1/subsciptions", headers = "Version=2.7.1")
public interface FaultSubscriptionsApi271 {

	Logger log = LoggerFactory.getLogger(FaultSubscriptionsApi271.class);

	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	@ApiOperation(value = "Query multiple subscriptions.", nickname = "subscriptionsGet", notes = "Query Subscription Information The API consumer can use this method to retrieve the list of active subscriptions for alarms related to an NS subscribed by the API consumer. It can be used e.g. for resynchronization after error situations. This method shall follow the provisions specified in the Tables 8.4.4.3.2-1 and 8.4.4.3.2-2 for URI query parameters, request and response data structures, and response codes. ", response = FmSubscription.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when the list of subscriptions has been queried successfully. The response body shall contain in an array the representations of all active subscriptions of the functional block that invokes the method, i.e. zero or more representations of FM subscriptions, as defined in clause 8.5.2.3. If the \"filter\" URI parameter was supplied in the request, the data in the response body shall have been transformed according to the rules specified in clause 5.2.2 of ETSI GS NFV-SOL 013 [16]. If the NFVO supports alternative 2 (paging) according to clause 5.4.2.1 of ETSI GS NFV-SOL 013 [16] for this resource, inclusion of the Link HTTP header in this response shall follow the provisions in clause 5.4.2.3 of ETSI GS NFV-SOL 013 [16]. ", response = FmSubscription.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "400 BAD REQUEST 400 code can be returned in the following specified cases, the specific cause has to be proper specified in the \"ProblemDetails\" structure to be returned. If the request is malformed or syntactically incorrect (e.g. if the request URI contains incorrect query parameters or the payload body contains a syntactically incorrect data structure), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If the response to a GET request which queries a container resource would be so big that the performance of the API producer is adversely affected, and the API producer does not support paging for the affected resource, it shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If there is an application error related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. If the request contains a malformed access token, the API producer should respond with this response. The details of the error shall be returned in the WWW Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. The use of this HTTP error response code described above is applicable to the use of the OAuth 2.0 for the authorization of API requests and notifications, as defined in clauses 4.5.3.3 and 4.5.3.4. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "401 UNAUTHORIZED If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "403 FORBIDDEN If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided. It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "405 METHOD NOT ALLOWED If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 NOT ACCEPTABLE If the \"Accept\" header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 412, message = "412 PRECONDITION FAILED Error: A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity. The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "503 SERVICE UNAVAILABLE If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 for the use of the \"Retry-After\" HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<FmSubscription>> subscriptionsGet(@ApiParam(value = "Version of the API requested to use when responding to this request. ", required = true) @RequestHeader(value = "Version", required = true) final String version, @ApiParam(value = "Content-Types that are acceptable for the response. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Accept", required = true) final String accept, @ApiParam(value = "The MIME type of the body of the request. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Content-Type", required = true) final String contentType, @ApiParam(value = "The authorization token for the request. Reference: IETF RFC 7235. ") @RequestHeader(value = "Authorization", required = false) final String authorization,
			@ApiParam(value = "Attribute-based filtering expression according to clause 5.2 of ETSI GS NFV SOL 013. The NFVO shall support receiving this parameter as part of the URI query string. The OSS/BSS may supply this parameter. All attribute names that appear in the FmSubscription and in data types referenced from it shall be supported by the NFVO in the filter expression. ") @Valid @RequestParam(value = "filter", required = false) final String filter, @ApiParam(value = "Marker to obtain the next page of a paged response. Shall be supported by the NFVO if the NFVO supports alternative 2 (paging) according to clause 5.4.2.1 of ETSI GS NFV-SOL 013 for this resource. ") @Valid @RequestParam(value = "nextpage_opaque_marker", required = false) final String nextpageOpaqueMarker) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<>(getObjectMapper().get().readValue(
							"[ {  \"filter\" : {    \"perceivedSeverities\" : [ { }, { } ],    \"nsInstanceSubscriptionFilter\" : {      \"nsdIds\" : [ null, null ],      \"vnfdIds\" : [ null, null ],      \"pnfdIds\" : [ null, null ],      \"nsInstanceIds\" : [ null, null ],      \"nsInstanceNames\" : [ { }, { } ]    },    \"faultyResourceTypes\" : [ { }, { } ],    \"probableCauses\" : [ \"probableCauses\", \"probableCauses\" ],    \"notificationTypes\" : [ \"AlarmNotification\", \"AlarmNotification\" ],    \"eventTypes\" : [ { }, { } ]  },  \"_links\" : {    \"self\" : {      \"href\" : \"http://example.com/aeiou\"    }  },  \"callbackUri\" : { },  \"id\" : { }}, {  \"filter\" : {    \"perceivedSeverities\" : [ { }, { } ],    \"nsInstanceSubscriptionFilter\" : {      \"nsdIds\" : [ null, null ],      \"vnfdIds\" : [ null, null ],      \"pnfdIds\" : [ null, null ],      \"nsInstanceIds\" : [ null, null ],      \"nsInstanceNames\" : [ { }, { } ]    },    \"faultyResourceTypes\" : [ { }, { } ],    \"probableCauses\" : [ \"probableCauses\", \"probableCauses\" ],    \"notificationTypes\" : [ \"AlarmNotification\", \"AlarmNotification\" ],    \"eventTypes\" : [ { }, { } ]  },  \"_links\" : {    \"self\" : {      \"href\" : \"http://example.com/aeiou\"    }  },  \"callbackUri\" : { },  \"id\" : { }} ]",
							List.class), HttpStatus.NOT_IMPLEMENTED);
				} catch (final IOException e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn("ObjectMapper or HttpServletRequest not configured in default SubscriptionsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Subscribe to alarms related to NSs.", nickname = "subscriptionsPost", notes = "The POST method creates a new subscription. This method shall follow the provisions specified in the Tables 8.4.4.3.1-1 and 8.4.4.3.1-2 for URI query parameters, request and response data structures, and response codes. As the result of successfully executing this method, a new \"Individual subscription\" resource shall exist as defined in clause 8.4.5. This method shall not trigger any notification. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow creating a subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \"201 Created\" response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \"303 See Other\" response code referencing the existing subscription resource with the same filter and callbackUri). ", response = FmSubscription.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "201 Created Shall be returned when the subscription has been created successfully. The response body shall contain a representation of the created subscription resource. The HTTP response shall include a \"Location:\" HTTP header that points to the created subscription resource. ", response = FmSubscription.class),
			@ApiResponse(code = 303, message = "303 SEE OTHER "),
			@ApiResponse(code = 400, message = "400 BAD REQUEST 400 code can be returned in the following specified cases, the specific cause has to be proper specified in the \"ProblemDetails\" structure to be returned. If the request is malformed or syntactically incorrect (e.g. if the request URI contains incorrect query parameters or the payload body contains a syntactically incorrect data structure), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If the response to a GET request which queries a container resource would be so big that the performance of the API producer is adversely affected, and the API producer does not support paging for the affected resource, it shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If there is an application error related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. If the request contains a malformed access token, the API producer should respond with this response. The details of the error shall be returned in the WWW Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. The use of this HTTP error response code described above is applicable to the use of the OAuth 2.0 for the authorization of API requests and notifications, as defined in clauses 4.5.3.3 and 4.5.3.4. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "401 UNAUTHORIZED If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "403 FORBIDDEN If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided. It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "405 METHOD NOT ALLOWED If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 NOT ACCEPTABLE If the \"Accept\" header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 422, message = "422 UNPROCESSABLE ENTITY If the payload body of a request contains syntactically correct data (e.g. well-formed JSON) but the data cannot be processed (e.g. because it fails validation against a schema), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. This error response code is only applicable for methods that have a request body. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "503 SERVICE UNAVAILABLE If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 for the use of the \"Retry-After\" HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	default ResponseEntity<FmSubscription> subscriptionsPost(@ApiParam(value = "Version of the API requested to use when responding to this request. ", required = true) @RequestHeader(value = "Version", required = true) final String version, @ApiParam(value = "Content-Types that are acceptable for the response. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Accept", required = true) final String accept, @ApiParam(value = "The MIME type of the body of the request. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Content-Type", required = true) final String contentType, @ApiParam(value = "Details of the subscription to be created, as defined in clause 8.5.2.2. ", required = true) @Valid @RequestBody final FmSubscriptionRequest body, @ApiParam(value = "The authorization token for the request. Reference: IETF RFC 7235. ") @RequestHeader(value = "Authorization", required = false) final String authorization) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"filter\" : {    \"perceivedSeverities\" : [ { }, { } ],    \"nsInstanceSubscriptionFilter\" : {      \"nsdIds\" : [ null, null ],      \"vnfdIds\" : [ null, null ],      \"pnfdIds\" : [ null, null ],      \"nsInstanceIds\" : [ null, null ],      \"nsInstanceNames\" : [ { }, { } ]    },    \"faultyResourceTypes\" : [ { }, { } ],    \"probableCauses\" : [ \"probableCauses\", \"probableCauses\" ],    \"notificationTypes\" : [ \"AlarmNotification\", \"AlarmNotification\" ],    \"eventTypes\" : [ { }, { } ]  },  \"_links\" : {    \"self\" : {      \"href\" : \"http://example.com/aeiou\"    }  },  \"callbackUri\" : { },  \"id\" : { }}", FmSubscription.class), HttpStatus.NOT_IMPLEMENTED);
				} catch (final IOException e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn("ObjectMapper or HttpServletRequest not configured in default SubscriptionsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Terminate a subscription.", nickname = "subscriptionsSubscriptionIdDelete", notes = "Terminate Subscription This method terminates an individual subscription. As the result of successfully executing this method, the \"Individual subscription\" resource shall not exist any longer. This means that no notifications for that subscription shall be sent to the formerly-subscribed API consumer. NOTE: Due to race conditions, some notifications might still be received by the formerly-subscribed API consumer for a certain time period after the deletion. ", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "204 - No Content Shall be returned when the subscription resource has been deleted successfully. The response body shall be empty. "),
			@ApiResponse(code = 400, message = "400 BAD REQUEST 400 code can be returned in the following specified cases, the specific cause has to be proper specified in the \"ProblemDetails\" structure to be returned. If the request is malformed or syntactically incorrect (e.g. if the request URI contains incorrect query parameters or the payload body contains a syntactically incorrect data structure), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If the response to a GET request which queries a container resource would be so big that the performance of the API producer is adversely affected, and the API producer does not support paging for the affected resource, it shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If there is an application error related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. If the request contains a malformed access token, the API producer should respond with this response. The details of the error shall be returned in the WWW Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. The use of this HTTP error response code described above is applicable to the use of the OAuth 2.0 for the authorization of API requests and notifications, as defined in clauses 4.5.3.3 and 4.5.3.4. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "401 UNAUTHORIZED If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "403 FORBIDDEN If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided. It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "405 METHOD NOT ALLOWED If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 NOT ACCEPTABLE If the \"Accept\" header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "503 SERVICE UNAVAILABLE If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 for the use of the \"Retry-After\" HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.DELETE)
	default ResponseEntity<Void> subscriptionsSubscriptionIdDelete(@ApiParam(value = "Identifier of this subscription. This identifier can be retrieved from the resource referenced by the \"Location\" HTTP header in the response to a POST request creating a new subscription resource. It can also be retrieved from the \"id\" attribute in the payload body of that response. ", required = true) @PathVariable("subscriptionId") final String subscriptionId, @ApiParam(value = "Version of the API requested to use when responding to this request. ", required = true) @RequestHeader(value = "Version", required = true) final String version, @ApiParam(value = "The authorization token for the request. Reference: IETF RFC 7235. ") @RequestHeader(value = "Authorization", required = false) final String authorization) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn("ObjectMapper or HttpServletRequest not configured in default SubscriptionsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Read an individual subscription.", nickname = "subscriptionsSubscriptionIdGet", notes = "Query Subscription Information The API consumer can use this method for reading an individual subscription for alarms related to NSs subscribed by the API consumer. This method shall follow the provisions specified in the Tables 8.4.5.3.2-1 and 8.4.5.3.2-2 for URI query parameters, request and response data structures, and response codes. ", response = FmSubscription.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when information about an individual subscription has been read successfully. The response body shall contain a representation of the subscription resource. ", response = FmSubscription.class),
			@ApiResponse(code = 400, message = "400 BAD REQUEST 400 code can be returned in the following specified cases, the specific cause has to be proper specified in the \"ProblemDetails\" structure to be returned. If the request is malformed or syntactically incorrect (e.g. if the request URI contains incorrect query parameters or the payload body contains a syntactically incorrect data structure), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If the response to a GET request which queries a container resource would be so big that the performance of the API producer is adversely affected, and the API producer does not support paging for the affected resource, it shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and should include in the \"detail\" attribute more information about the source of the problem. If there is an application error related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. If the request contains a malformed access token, the API producer should respond with this response. The details of the error shall be returned in the WWW Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. The use of this HTTP error response code described above is applicable to the use of the OAuth 2.0 for the authorization of API requests and notifications, as defined in clauses 4.5.3.3 and 4.5.3.4. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "401 UNAUTHORIZED If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "403 FORBIDDEN If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided. It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "405 METHOD NOT ALLOWED If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 NOT ACCEPTABLE If the \"Accept\" header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "503 SERVICE UNAVAILABLE If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 for the use of the \"Retry-After\" HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.GET)
	default ResponseEntity<FmSubscription> subscriptionsSubscriptionIdGet(@ApiParam(value = "Identifier of this subscription. This identifier can be retrieved from the resource referenced by the \"Location\" HTTP header in the response to a POST request creating a new subscription resource. It can also be retrieved from the \"id\" attribute in the payload body of that response. ", required = true) @PathVariable("subscriptionId") final String subscriptionId, @ApiParam(value = "Version of the API requested to use when responding to this request. ", required = true) @RequestHeader(value = "Version", required = true) final String version, @ApiParam(value = "Content-Types that are acceptable for the response. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Accept", required = true) final String accept, @ApiParam(value = "The MIME type of the body of the request. Reference: IETF RFC 7231 ", required = true) @RequestHeader(value = "Content-Type", required = true) final String contentType, @ApiParam(value = "The authorization token for the request. Reference: IETF RFC 7235. ") @RequestHeader(value = "Authorization", required = false) final String authorization) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"filter\" : {    \"perceivedSeverities\" : [ { }, { } ],    \"nsInstanceSubscriptionFilter\" : {      \"nsdIds\" : [ null, null ],      \"vnfdIds\" : [ null, null ],      \"pnfdIds\" : [ null, null ],      \"nsInstanceIds\" : [ null, null ],      \"nsInstanceNames\" : [ { }, { } ]    },    \"faultyResourceTypes\" : [ { }, { } ],    \"probableCauses\" : [ \"probableCauses\", \"probableCauses\" ],    \"notificationTypes\" : [ \"AlarmNotification\", \"AlarmNotification\" ],    \"eventTypes\" : [ { }, { } ]  },  \"_links\" : {    \"self\" : {      \"href\" : \"http://example.com/aeiou\"    }  },  \"callbackUri\" : { },  \"id\" : { }}", FmSubscription.class), HttpStatus.NOT_IMPLEMENTED);
				} catch (final IOException e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn("ObjectMapper or HttpServletRequest not configured in default SubscriptionsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
