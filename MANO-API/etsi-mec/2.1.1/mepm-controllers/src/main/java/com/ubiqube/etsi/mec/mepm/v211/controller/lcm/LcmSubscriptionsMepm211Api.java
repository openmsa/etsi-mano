/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ubiqube.etsi.mec.mepm.v211.controller.lcm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.Body;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InlineResponse201;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.ProblemDetails;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionLinkList;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Api(value = "subscriptions", description = "the subscriptions API")
@RequestMapping("/mepm/app_lcm/v1/subscriptions")
public interface LcmSubscriptionsMepm211Api {

	@ApiOperation(value = "Retrieves the information of multiple subscriptions to notifications related to an application instance.", nickname = "appLcmSubscriptionsGET", notes = "Retrieves the information of multiple subscriptions to notifications related to an application instance.", response = SubscriptionLinkList.class, tags = { "", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = " List of all subscriptions is returned.", response = SubscriptionLinkList.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<SubscriptionLinkList> appLcmSubscriptionsGET(@ApiParam(value = "Permitted values: AppInstanceStateChange or  AppLcmOpOccStateChange") @Valid @RequestParam(value = "subscriptionType", required = false) String subscriptionType);

	@ApiOperation(value = "subscribe to the notification of application instance operational state change", nickname = "appLcmSubscriptionsPOST", notes = "subscribe to the notification of application instance operational state change", response = InlineResponse201.class, tags = { "", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "a representation of the created SubscriptionInfo.", response = InlineResponse201.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<InlineResponse201> appLcmSubscriptionsPOST(@ApiParam(value = "", required = true) @Valid @RequestBody Body body, @NotNull @ApiParam(value = "\"Query parameter to filter on a specific subscription type. Permitted values:   • \"AppInstanceStateChange\"   • \"AppLcmOpOccStateChange\" \"", required = true) @Valid @RequestParam(value = "subscriptionType", required = true) SubscriptionType subscriptionType);

	@ApiOperation(value = "Deletes the individual subscription to notifications about application package changes in MEO.", nickname = "individualSubscriptionDELETE", notes = "Deletes the individual subscription to notifications about application package changes in MEO.", tags = { "", })
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> individualSubscriptionDELETE(@ApiParam(value = "Represents an individual subscription to notification related to an application instance", required = true) @PathVariable("subscriptionId") String subscriptionId);

	@ApiOperation(value = "Used to represent an individual subscription to notifications about application package changes.", nickname = "individualSubscriptionGET", notes = "Used to represent an individual subscription to notifications about application package changes.", response = InlineResponse201.class, tags = { "", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Representation of the resource.", response = InlineResponse201.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<InlineResponse201> individualSubscriptionGET(@ApiParam(value = "Represents an individual subscription to notification related to an application instance", required = true) @PathVariable("subscriptionId") String subscriptionId, @NotNull @ApiParam(value = "Permitted values: AppInstanceStateChange or  AppLcmOpOccStateChange", required = true) @Valid @RequestParam(value = "subscriptionType", required = true) String subscriptionType);

}