package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse200;
import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse400;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesCreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdHealPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdInstantiatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdScalePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdTerminatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdUpdatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesPostQuery;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sol005/nslcm/v1/ns_instances")
@Api(value = "/sol005/nslcm/v1/ns_instances")
public class NsInstancesApi {
	private static final Logger LOG = LoggerFactory.getLogger(NsLcmSol005Api.class);
	private static final String REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/ns_instances";

	private final DeviceService deviceService;
	private final NsdRepository nsdRepository;
	private final NsInstanceRepository nsInstanceRepository;

	private final OrchestrationService orchestrationService;
	private final RepositoryService repositoryService;

	@Autowired
	public NsInstancesApi(DeviceService _deviceService, NsdRepository _nsdRepository, NsInstanceRepository _nsInstanceRepository, OrchestrationService _orchestrationService, RepositoryService _repRepositoryService) {
		deviceService = _deviceService;
		nsdRepository = _nsdRepository;
		nsInstanceRepository = _nsInstanceRepository;
		orchestrationService = _orchestrationService;
		repositoryService = _repRepositoryService;
	}

	/**
	 * Query multiple NS instances.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@ApiOperation(value = "Query multiple NS instances.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about zero or more NS instances was queried successfully. The response body shall contain representations of zero or more NS instances, as defined in clause 6.5.2.8. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<List<NsInstancesNsInstance>> nsInstancesGet(@RequestHeader("Accept") final String accept, @RequestParam("filter") String filter, @RequestParam("all_fields") String allFields, @RequestParam("fields") String fields, @RequestParam("exclude_fields") String excludeFields, @RequestParam("exclude_default") String excludeDefault) {
		return new ResponseEntity<>(nsInstanceRepository.query(), HttpStatus.OK);
	}

	/**
	 * Delete NS instance resource.
	 *
	 * Delete NS Identifier This method deletes an individual NS instance resource.
	 *
	 */
	@ApiOperation(value = "Delete NS instance resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The NS instance resource and the associated NS identifier were deleted successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the NS LCM operation occurrence resource. Typically, this is due to the fact that the NS LCM operation occurrence is not in FAILED_TEMP state, or another error handling action is starting, such as retry or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 412, message = "Precondition Failed A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity. The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@DeleteMapping(value = "/{nsInstanceId}", consumes = { "application/json" }, produces = { "application/json" })
	public void nsInstancesNsInstanceIdDelete(@PathVariable("nsInstanceId") String nsInstanceId) {
		try {
			final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			deviceService.deleteDevice(deviceService.getDeviceId(nsInstanceId), principal.getUsername());
		} catch (final ServiceException e) {
			throw new NotFoundException("Object not found.", e);
		}

	}

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an
	 * individual NS instance resource.
	 *
	 */
	@ApiOperation(value = "Read an individual NS instance resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about an individual NS instance was queried successfully. The response body shall contain a representation of the NS instance. ", response = InlineResponse200.class), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@GetMapping(value = "/{nsInstanceId}", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<InlineResponse200> nsInstancesNsInstanceIdGet(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType) {

		final InlineResponse200 resp = new InlineResponse200();
		final NsInstancesNsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		resp.setNsInstance(nsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Heal a NS instance.
	 *
	 * The POST method requests to heal a NS instance resource. This method shall
	 * follow the provisions specified in the Tables 6.4.7.3.1-1 and 6.4.7.3.1-2 for
	 * URI query parameters, request and response data structures, and response
	 * codes.
	 *
	 */
	@ApiOperation(value = "Heal a NS instance.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The request was accepted for processing, but the processing has not  been completed.  The response body shall be empty. The HTTP response shall include a \"Location\" HTTP header that contains the URI of the newly-created \"NS lifecycle operation occurrence\" resource corresponding to the operation. ", response = NsInstancesNsInstance.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also returned if the task is not supported for the NS instance represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is in NOT-INSTANTIATED state, or that another lifecycle management operation is ongoing. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/{nsInstanceId}/heal", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdHealPost(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesNsInstanceIdHealPostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	@ApiOperation(value = "Instantiate a NS.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The request was accepted for processing, but the processing has not  been completed.  The response body shall be empty. The HTTP response shall include a \"Location\" HTTP header that contains the URI of the newly-created \"NS lifecycle operation occurrence\" resource corresponding to the operation. ", response = NsInstancesNsInstance.class),
			@ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the NS LCM operation occurrence resource. Typically, this is due to the fact that the NS LCM operation occurrence is not in FAILED_TEMP state, or another error handling action is starting, such as retry or fail. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/{nsInstanceId}/instantiate", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdInstantiatePost(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesNsInstanceIdInstantiatePostQuery body) {
		final Map<String, String> varsMap = new HashMap<>();
		final NsInstancesNsInstance nsInstancesNsInstance = nsInstanceRepository.get(nsInstanceId);
		final String nsdId = nsInstancesNsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		final Map<String, String> userData = (Map<String, String>) nsdInfo.getUserDefinedData();
		varsMap.put("deviceid", userData.get("vimId"));
		varsMap.put("nsPkgId", nsdId);
		final String processName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String serviceName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";
		final long serviceId = 0;
		final String ubiqubeId = userData.get("customerId");
		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(ubiqubeId, serviceId, serviceName, processName, varsMap);
			nsInstancesNsInstance.setNsState(NsStateEnum.INSTANTIATED);
			nsInstanceRepository.save(nsInstancesNsInstance);
			userData.put("msaProcessId", String.valueOf(resp.processId.id));
			nsdRepository.save(nsdInfo);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		return new ResponseEntity<>(nsInstancesNsInstance, HttpStatus.OK);
	}

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	@ApiOperation(value = "Scale a NS instance.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The request was accepted for processing, but the processing has not  been completed.  The response body shall be empty. The HTTP response shall include a \"Location\" HTTP header that contains the URI of the newly-created \"NS lifecycle operation occurrence\" resource corresponding to the operation. ", response = NsInstancesNsInstance.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also returned if the task is not supported for the NS instance represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is in NOT-INSTANTIATED state, or that another lifecycle management operation is ongoing. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/{nsInstanceId}/scale", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdScalePost(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesNsInstanceIdScalePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Terminate a NS instance.
	 *
	 * Terminate NS task. The POST method terminates a NS instance. This method can
	 * only be used with a NS instance in the INSTANTIATED state. Terminating a NS
	 * instance does not delete the NS instance identifier, but rather transitions
	 * the NS into the NOT_INSTANTIATED state. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 6.4.8.3.1-1 and 6.8.8.3.1-2.
	 *
	 */
	@ApiOperation(value = "Terminate a NS instance.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The request was accepted for processing, but the processing has not  been completed.  The response body shall be empty. The HTTP response shall include a \"Location\" HTTP header that contains the URI of the newly-created \"NS lifecycle operation occurrence\" resource corresponding to the operation. ", response = NsInstancesNsInstance.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also returned if the task is not supported for the NS instance represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is in NOT-INSTANTIATED state, or that another lifecycle management operation is ongoing. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/{nsInstanceId}/terminate", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdTerminatePost(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesNsInstanceIdTerminatePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	@ApiOperation(value = "Updates a NS instance.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The request was accepted for processing, but the processing has not  been completed.  The response body shall be empty. The HTTP response shall include a \"Location\" HTTP header that contains the URI of the newly-created \"NS lifecycle operation occurrence\" resource corresponding to the operation. ", response = NsInstancesNsInstance.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found Error: The API producer did not find a current representation for the target resource or is not willing to disclose that one exists. Specifically in case of this task resource, the  response code 404 shall also returned if the task is not supported for the NS instance represented by the parent resource, which means that the task resource consequently does not exist.  In this case, the response body shall be present, and shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class), @ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict The operation cannot be executed currently, due to a conflict with the state of the NS instance resource. Typically, this is due to the fact that the NS instance resource is in NOT-INSTANTIATED state, or that another lifecycle management operation is ongoing. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(value = "/{nsInstanceId}/update", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdUpdatePost(@PathVariable("nsInstanceId") String nsInstanceId, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesNsInstanceIdUpdatePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	@ApiOperation(value = "Create a NS instance resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "201 Created A NS Instance identifier was created successfully. The response body shall contain a representation of the created NS instance, as defined in clause 6.5.2.8. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the created NS instance. ", response = InlineResponse200.class), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = InlineResponse400.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = InlineResponse400.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = InlineResponse400.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = InlineResponse400.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = InlineResponse400.class),
			@ApiResponse(code = 406, message = "Not Acceptable If the Accept HTTP header does not contain at least one name of a content type that is acceptable to the API producer, the API producer shall respond with this response code. The ProblemDetails structure may be omitted in that case.         ", response = InlineResponse400.class), @ApiResponse(code = 409, message = "Conflict Another request is in progress that prohibits the fulfilment of the current request, or the current resource state is inconsistent with the request. ", response = InlineResponse400.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable This code is returned if the requested byte range in the Range HTTP header is not present in the requested resource. ", response = InlineResponse400.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = InlineResponse400.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = InlineResponse400.class) })
	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<InlineResponse200> nsInstancesPost(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType, NsInstancesPostQuery body) {
		final NsInstancesCreateNsRequest req = body.getCreateNsRequest();
		final NsDescriptorsNsdInfo nsd = nsdRepository.get(req.getNsdId());

		final NsInstancesNsInstance nsInstancesNsInstance = new NsInstancesNsInstance();
		nsInstancesNsInstance.setNsdId(req.getNsdId());
		nsInstancesNsInstance.setNsInstanceDescription(req.getNsDescription());
		nsInstancesNsInstance.setNsInstanceName(req.getNsName());
		nsInstancesNsInstance.setNestedNsInstanceId(nsd.getNestedNsdInfoIds());
		nsInstancesNsInstance.setNsState(NsStateEnum.NOT_INSTANTIATED);
		final String id = UUID.randomUUID().toString();
		nsInstancesNsInstance.setId(id);

		nsInstanceRepository.save(nsInstancesNsInstance);

		final InlineResponse200 resp = new InlineResponse200();
		resp.setNsInstance(nsInstancesNsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
