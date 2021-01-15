/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscription;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionInfo;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionLinkList;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.ProblemDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "subscriptions", description = "the subscriptions API")
@RequestMapping("/meo/app_pkgm/v1/subscriptions")
public interface PkgSubscriptions211MepmApi {

	@ApiOperation(value = "Deletes the individual subscription to notifications about application package changes in MEO.", nickname = "individualSubscriptionDELETE", notes = "Deletes the individual subscription to notifications about application package changes in MEO.", tags = { "app-pkgm", })
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> subscriptionDelete(@ApiParam(value = "Identifier of an individual subscription to notifications about application package changes", required = true) @PathVariable("subscriptionId") String subscriptionId);

	@ApiOperation(value = "Used to represent an individual subscription to notifications about application package changes.", nickname = "individualSubscriptionGET", notes = "Used to represent an individual subscription to notifications about application package changes.", response = AppPkgSubscriptionInfo.class, tags = { "app-pkgm", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Representation of the resource.", response = AppPkgSubscriptionInfo.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/{subscriptionId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<AppPkgSubscriptionInfo> subscriptionGetById(@ApiParam(value = "Identifier of an individual subscription to notifications about application package changes", required = true) @PathVariable("subscriptionId") String subscriptionId);

	@ApiOperation(value = "used to retrieve the information of subscriptions to individual application package resource in MEO", nickname = "subscriptionsGET", notes = "used to retrieve the information of subscriptions to individual application package resource in MEO package", response = AppPkgSubscriptionLinkList.class, tags = { "app-pkgm", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of zero or more subscriptions", response = AppPkgSubscriptionLinkList.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<AppPkgSubscriptionInfo>> subscriptionQuery(String filter);

	@ApiOperation(value = "Subscribe to notifications about on-boarding an application package", nickname = "subscriptionsPOST", notes = "Subscribe to notifications about on-boarding an application package", response = AppPkgSubscriptionInfo.class, tags = { "app-pkgm", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful response for created subscription", response = AppPkgSubscriptionInfo.class),
			@ApiResponse(code = 400, message = "Bad Request : used to indicate that incorrect parameters were passed to the request.", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized :  used when the client did not submit credentials.", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden :  operation is not allowed given the current status of the resource.", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found :  used when a client provided a URI that cannot be mapped to a valid resource URI.", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "Not Acceptable : used to indicate that the server cannot provide the any of the content formats supported by the client.", response = ProblemDetails.class),
			@ApiResponse(code = 429, message = "Too Many Requests : used when a rate limiter has triggered.", response = ProblemDetails.class) })
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<AppPkgSubscriptionInfo> subscriptionsPost(@ApiParam(value = "The input parameters of subscribe operation to notifications", required = true) @Valid @RequestBody AppPkgSubscription body);

}
