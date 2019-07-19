package com.ubiqube.etsi.mano.controller.nsd.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.controller.BaseApi;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdPatchQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoOnboardingFailureDetails;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsPostQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdChangeNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdPatchQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdPatchResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPostQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.SubscriptionsPostQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.SubscriptionsPostResponse;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.ZipFileHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * SOL005 - NSD Management Interface
 *
 * <p>
 * SOL005 - NSD Management Interface IMPORTANT: Please note that this file might
 * be not aligned to the current version of the ETSI Group Specification it
 * refers to and has not been approved by the ETSI NFV ISG. In case of
 * discrepancies the published ETSI Group Specification takes precedence. Please
 * report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
//@Path("/sol005/nsd/v1")
@RestController
@RequestMapping("/sol005/nsd/v1")
@Api(value = "/")
public class NsdSol005Api extends BaseApi implements NsdSol005 {
	private static final String APPLICATION_ZIP = "application/zip";
	protected static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_NSD_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/nsd";

	private final NsdRepository nsdRepository;

	private final RepositoryService repositoryService;

	private final ObjectMapper mapper;

	@Inject
	public NsdSol005Api(NsdRepository _nsdRepository, ObjectMapper _mapper, RepositoryService _repositoryService) {
		nsdRepository = _nsdRepository;
		repositoryService = _repositoryService;
		mapper = _mapper;
	}

	/**
	 * Query information about multiple NS descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple NS descriptor
	 * resources. This method shall follow the provisions specified in the Tables
	 * 5.4.2.3.2-1 and 5.4.2.3.2-2 for URI query parameters, request and response
	 * data structures, and response codes.\&quot;
	 *
	 */
	@Override
	@GET
	@Path("/ns_descriptors")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Query information about multiple NS descriptor resources.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about zero or more NS descriptors. The response body shall contain a representation of zero or more NS descriptors, as defined in clause 5.5.2.2 ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@GetMapping(value = "/ns_descriptors", consumes = { "application/json" }, produces = { "application/json" })
	public List<NsDescriptorsNsdInfoIdGetResponse> nsDescriptorsGet(@HeaderParam("Accept") String accept, @QueryParam("filter") String filter, @QueryParam("all_fields") String allFields, @QueryParam("fields") String fields, @QueryParam("exclude_fields") String excludeFields, @QueryParam("exclude_default") String excludeDefault) {
		List<String> listFilesInFolder;
		try {
			listFilesInFolder = repositoryService.doSearch(REPOSITORY_NSD_BASE_PATH, "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		final List<NsDescriptorsNsdInfoIdGetResponse> response = new ArrayList<>();
		for (final String entry : listFilesInFolder) {
			final RepositoryElement repositoryElement = repositoryService.getElement(entry);
			final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement), StandardCharsets.UTF_8);
			try {
				final NsDescriptorsNsdInfo nsdInfo = mapper.readValue(content, NsDescriptorsNsdInfo.class);
				final NsDescriptorsNsdInfoIdGetResponse resp = new NsDescriptorsNsdInfoIdGetResponse();
				resp.setNsdInfo(nsdInfo);
				response.add(resp);
			} catch (final IOException e) {
				throw new GenericException(e);
			}
		}
		return response;
	}

	/**
	 * Delete an individual NS descriptor resource.
	 *
	 * The DELETE method deletes an individual NS descriptor resource. An individual
	 * NS descriptor resource can only be deleted when there is no NS instance using
	 * it (i.e. usageState &#x3D; NOT_IN_USE) and has been disabled already (i.e.
	 * operationalState &#x3D; DISABLED). Otherwise, the DELETE method shall fail.
	 *
	 */
	@Override
	@DELETE
	@Path("/ns_descriptors/{nsdInfoId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Delete an individual NS descriptor resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The operation has completed successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@DeleteMapping(value = "/ns_descriptors/{nsdInfoId}", consumes = { "application/json" }, produces = { "application/json" })
	public void nsDescriptorsNsdInfoIdDelete(@PathParam("nsdInfoId") String nsdInfoId) {
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		if (!nsdInfo.getNsdOperationalState().equals("DISABLED") || nsdInfo.getNsdUsageState().equals("IN_USE")) {
			throw new ConflictException("Nsd in bad state " + nsdInfoId);
		}
	}

	/**
	 * Read information about an individual NS descriptor resource.
	 *
	 * \&quot;The GET method reads information about an individual NS descriptor.
	 * This method shall follow the provisions specified in GS NFV-SOL 005 Tables
	 * 5.4.3.3.2-1 and 5.4.3.3.2-2 of GS NFV-SOL 005 for URI query parameters,
	 * request and response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	@GET
	@Path("/ns_descriptors/{nsdInfoId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Read information about an individual NS descriptor resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information about the individual NS descriptor. The response body shall contain a representation of the individual NS descriptor.             ", response = NsDescriptorsNsdInfoIdGetResponse.class), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@GetMapping(value = "/ns_descriptors/{nsdInfoId}", consumes = { "application/json" }, produces = { "application/json" })
	public NsDescriptorsNsdInfo nsDescriptorsNsdInfoIdGet(@PathParam("nsdInfoId") String nsdInfoId, @HeaderParam("Accept") String accept) {
		final NsDescriptorsNsdInfo nsdIfno = nsdRepository.get(nsdInfoId);

		final NsDescriptorsNsdInfoIdGetResponse nsDescriptorsNsdInfoIdGetResponse = new NsDescriptorsNsdInfoIdGetResponse();
		nsDescriptorsNsdInfoIdGetResponse.setNsdInfo(nsdIfno);
		return nsdIfno;
	}

	/**
	 * Fetch the content of a NSD.
	 *
	 * The GET method fetches the content of the NSD. The NSD can be implemented as
	 * a single file or as a collection of multiple files. If the NSD is implemented
	 * in the form of multiple files, a ZIP file embedding these files shall be
	 * returned. If the NSD is implemented as a single file, either that file or a
	 * ZIP file embedding that file shall be returned. The selection of the format
	 * is controlled by the \&quot;Accept\&quot; HTTP header passed in the GET
	 * request:• If the \&quot;Accept\&quot; header contains only
	 * \&quot;text/plain\&quot; and the NSD is implemented as a single file, the
	 * file shall be returned; otherwise, an error message shall be returned.• If
	 * the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;,
	 * the single file or the multiple files that make up the NSD shall be returned
	 * embedded in a ZIP file.• If the \&quot;Accept\&quot; header contains both
	 * \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to the
	 * NFVO to choose the format to return for a single-file NSD; for a multi-file
	 * NSD, a ZIP file shall be returned.NOTE: The structure of the NSD zip file is
	 * outside the scope of the present document.
	 *
	 * @return
	 *
	 */
	@Override
	@GET
	@Path("/ns_descriptors/{nsdInfoId}/nsd_content")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Fetch the content of a NSD.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK On success, the content of the NSD is returned. The payload body shall contain a copy of the file representing the NSD or a ZIP file that contains the file or multiple files representing the NSD, as specified above. The \"Content-Type\" HTTP header shall be set according to the format of the returned file, i.e. to \"text/plain\" for a YAML file or to \"application/zip\" for a ZIP file. "), @ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the NSD file is returned. The response body shall contain the requested part of the NSD file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233 [23]. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response.        ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact \"nsdOnboardingState\" has a value different from ONBOARDED. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@GetMapping(value = "/ns_descriptors/{nsdInfoId}/nsd_content", consumes = { "application/json" }, produces = { "application/json" })
	public Response nsDescriptorsNsdInfoIdNsdContentGet(@PathParam("nsdInfoId") String nsdInfoId, @HeaderParam("Accept") String accept, @HeaderParam("Range") String range) {
		try {
			final RangeHeader rangeHeader = RangeHeader.fromValue(range);
			final List<String> listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NSD_BASE_PATH).append("/").append(nsdInfoId).toString(), "");
			if (!listvnfPckgFiles.isEmpty()) {
				if (listvnfPckgFiles.size() > 1) {
					return getZipArchive(rangeHeader, listvnfPckgFiles);
				}
				final RepositoryElement repositoryElement = repositoryService.getElement(listvnfPckgFiles.get(0));
				final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
				return Response.ok().type(APPLICATION_ZIP).entity(new ByteArrayInputStream(content)).build();
			}
			throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ").append(nsdInfoId).toString());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Upload the content of a NSD.
	 *
	 * \&quot;The PUT method is used to upload the content of a NSD. The NSD to be
	 * uploaded can be implemented as a single file or as a collection of multiple
	 * files, as defined in clause 5.4.4.3.2 of GS NFV-SOL 005. If the NSD is
	 * implemented in the form of multiple files, a ZIP file embedding these files
	 * shall be uploaded. If the NSD is implemented as a single file, either that
	 * file or a ZIP file embedding that file shall be uploaded. The
	 * \&quot;Content-Type\&quot; HTTP header in the PUT request shall be set
	 * accordingly based on the format selection of the NSD. If the NSD to be
	 * uploaded is a text file, the \&quot;Content-Type\&quot; header is set to
	 * \&quot;text/plain\&quot;. If the NSD to be uploaded is a zip file, the
	 * \&quot;Content-Type\&quot; header is set to \&quot;application/zip\&quot;.
	 * This method shall follow the provisions specified in the Tables 5.4.4.3.3-1
	 * and 5.4.4.3.3-2 of GS-NFV-SOL 005 for URI query parameters, request and
	 * response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	@PUT
	@Path("/ns_descriptors/{nsdInfoId}/nsd_content")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Upload the content of a NSD.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The NSD content was accepted for uploading, but the processing has not been completed. It is expected to take some time for processing (asynchronous mode). The response body shall be empty.  "), @ApiResponse(code = 204, message = "204 No Content The NSD content was successfully uploaded and validated (synchronous mode).  The response body shall be empty. "), @ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the NSD file is returned. The response body shall contain the requested part of the NSD file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233 [23]. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response.        ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact \"nsdOnboardingState\" has a value different from ONBOARDED. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@PutMapping(value = "/ns_descriptors/{nsdInfoId}/nsd_content", consumes = { "application/json" }, produces = { "application/json" })
	public void nsDescriptorsNsdInfoIdNsdContentPut(@PathParam("nsdInfoId") String nsdInfoId, @HeaderParam("Accept") String accept) {
		// Nothing.
	}

	/**
	 * Modify the operational state and/or the user defined data of an individual NS
	 * descriptor resource.
	 *
	 * The PATCH method modifies the operational state and/or user defined data of
	 * an individual NS descriptor resource. This method can be used to: 1) Enable a
	 * previously disabled individual NS descriptor resource, allowing again its use
	 * for instantiation of new network service with this descriptor. The usage
	 * state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not change as result. 2)
	 * Disable a previously enabled individual NS descriptor resource, preventing
	 * any further use for instantiation of new network service(s) with this
	 * descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not
	 * changes a result. 3) Modify the user defined data of an individual NS
	 * descriptor resource.
	 *
	 */
	@Override
	@PATCH
	@Path("/ns_descriptors/{nsdInfoId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Modify the operational state and/or the user defined data of an individual NS descriptor resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The operation was completed successfully. The response body shall contain attribute modifications for an 'Individual NS Descriptor' resource. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@RequestMapping(value = "/ns_descriptors/{nsdInfoId}", consumes = { "application/json" }, produces = { "application/json" }, method = RequestMethod.PATCH)
	public List<Object> nsDescriptorsNsdInfoIdPatch(@PathParam("nsdInfoId") String nsdInfoId, NsDescriptorsNsdInfoIdPatchQuery body, @HeaderParam("Content-Type") String contentType) {
		return new ArrayList<>();
	}

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new
	 * version of an on-boarded NS descriptor.
	 *
	 */
	@Override
	@POST
	@Path("/ns_descriptors")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Create a new NS descriptor resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Status 201 An NS descriptor resource was created successfully, as a new NS descriptor resource.  The response body shall contain a representation of the new NS descriptor resource, as defined in clause 5.5.2.3 of GS NFV-SOL 005. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the new NS descriptor resource.             ", response = NsDescriptorsNsdInfoIdGetResponse.class), @ApiResponse(code = 400, message = "Bad Request Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = NsDescriptorsNsdInfoOnboardingFailureDetails.class) })
	@PostMapping(value = "/ns_descriptors", consumes = { "application/json" }, produces = { "application/json" })
	public NsDescriptorsNsdInfo nsDescriptorsPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, NsDescriptorsPostQuery nsDescriptorsPostQuery) {
		final String id = UUID.randomUUID().toString();
		final StringBuilder sb = new StringBuilder().append(REPOSITORY_NSD_BASE_PATH).append("/").append(id);
		final String uri = sb.toString();
		try {
			if (!repositoryService.exists(uri)) {
				repositoryService.addDirectory(uri, "", "SOL005", "ncroot");
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		final String _self = linkTo(methodOn(NsdSol005Api.class).nsDescriptorsNsdInfoIdGet(id, "")).withSelfRel().getHref();
		final String _nsdContent = linkTo(methodOn(NsdSol005Api.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final NsDescriptorsNsdInfo resp = NsdFactories.createNsDescriptorsNsdInfo(id, _self, _nsdContent);
		final Map<String, Object> userDefinedData = (Map<String, Object>) nsDescriptorsPostQuery.getCreateNsdInfoRequest().getUserDefinedData();
		resp.setUserDefinedData(userDefinedData);
		final List<String> vnfPkgIds = (List<String>) userDefinedData.get("vnfPkgIds");
		resp.setVnfPkgIds(vnfPkgIds);

		nsdRepository.save(resp);
		return resp;
	}

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor
	 * resources.\&quot;
	 *
	 */
	@Override
	public List<Object> pnfDescriptorsGet(String filter, String allFields, String fields, String excludeFields, String excludeDefault) {

		return new ArrayList<>();
	}

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An
	 * individual PNF descriptor resource can only be deleted when there is no NS
	 * instance using it or there is NSD referencing it. To delete all PNFD versions
	 * identified by a particular value of the \&quot;pnfdInvariantId\&quot;
	 * attribute, the procedure is to first use the GET method with filter
	 * \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find
	 * all versions of the PNFD. Then, the client uses the DELETE method described
	 * in this clause to delete each PNFD version individually.
	 *
	 */
	@Override
	public void pnfDescriptorsPnfdInfoIdDelete(String pnfdInfoId) {
		// : Implement...

	}

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This
	 * method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and
	 * 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public PnfDescriptorsPnfdInfoIdGetResponse pnfDescriptorsPnfdInfoIdGet(String pnfdInfoId, String accept) {
		// : Implement...

		return null;
	}

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF
	 * descriptor resource.
	 *
	 */
	@Override
	public PnfDescriptorsPnfdInfoIdPatchResponse pnfDescriptorsPnfdInfoIdPatch(String pnfdInfoId, String accept, String contentType, PnfDescriptorsPnfdInfoIdPatchQuery body) {
		// : Implement...

		return null;
	}

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the
	 * provisions specified in the Table 5.4.7.3.2-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public void pnfDescriptorsPnfdInfoIdPnfdContentGet(String pnfdInfoId, String accept) {
		// : Implement...

	}

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource
	 * represents the content of the individual PNF descriptor, i.e. PNFD content.
	 * The client can use this resource to upload and download the content of the
	 * PNFD.
	 *
	 */
	@Override
	public void pnfDescriptorsPnfdInfoIdPnfdContentPut(String pnfdInfoId, String accept) {
		// : Implement...

	}

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	@Override
	public PnfDescriptorsPnfdInfoIdGetResponse pnfDescriptorsPost(String accept, String contentType, PnfDescriptorsPostQuery body) {
		// : Implement...

		return null;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional
	 * block that invokes the method. It can be used e.g. for resynchronization
	 * after error situations. This method shall support the URI query parameters,
	 * request and response data structures, and response codes. This resource
	 * represents subscriptions. The client can use this resource to subscribe to
	 * notifications related to NSD management and to query its subscriptions.
	 *
	 */
	@Override
	public List<Object> subscriptionsGet(String accept, String filter) {
		// : Implement...

		return new ArrayList<>();
	}

	/**
	 * Subscribe to NSD and PNFD change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 5.4.8.3.1-1 and 5.4.8.3.1-2 of GS-NFV SOL 005.
	 * Creation of two subscription resources with the same callbackURI and the same
	 * filter can result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri). This resource represents subscriptions. The client can use this
	 * resource to subscribe to notifications related to NSD management and to query
	 * its subscriptions.
	 *
	 */
	@Override
	public SubscriptionsPostResponse subscriptionsPost(String accept, String contentType, SubscriptionsPostQuery body) {
		// : Implement...

		return null;
	}

	/**
	 * Terminate Subscription
	 *
	 * This resource represents an individual subscription. It can be used by the
	 * client to read and to terminate a subscription to notifications related to
	 * NSD management. The DELETE method terminates an individual subscription. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Table 5.4.9.3.3-2.
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(String subscriptionId) {
		// : Implement...

	}

	/**
	 * Read an individual subscription resource.
	 *
	 * This resource represents an individual subscription. It can be used by the
	 * client to read and to terminate a subscription to notifications related to
	 * NSD management. The GET method retrieves information about a subscription by
	 * reading an individual subscription resource. This resource represents an
	 * individual subscription. It can be used by the client to read and to
	 * terminate a subscription to notifications related to NSD management.
	 *
	 */
	@Override
	public SubscriptionsPostResponse subscriptionsSubscriptionIdGet(String subscriptionId, String accept) {
		// : Implement...

		return null;
	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdChangeNotificationPost(NsdChangeNotification nsdChangeNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdDeletionNotificationPost(NsdDeletionNotification nsdDeletionNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdOnBoardingFailureNotificationPost(NsdOnBoardingFailureNotification nsdOnBoardingFailureNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdOnBoardingNotificationPost(NsdOnBoardingNotification nsdOnBoardingNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Test the notification endpoint
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The GET method allows
	 * the server to test the notification endpoint that is provided by the client,
	 * e.g. during subscription. This method shall follow the provisions specified
	 * in the Table 5.4.10.3.2-2 for URI query parameters, request and response data
	 * structures, and response codes.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdDeletionNotificationGet(String accept) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdDeletionNotificationPost(PnfdDeletionNotification pnfdDeletionNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdOnBoardingFailureNotificationPost(PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdOnBoardingNotificationPost(PnfdOnBoardingNotification pnfdOnBoardingNotification, String accept, String contentType) {
		// : Implement...

	}

	/**
	 * Method allows to archive VNF Package contents and artifacts.
	 *
	 * @param range
	 * @param listvnfPckgFiles
	 * @return
	 *
	 */
	private Response getZipArchive(RangeHeader rangeHeader, List<String> listvnfPckgFiles) {

		final ZipFileHandler zip = new ZipFileHandler(repositoryService, listvnfPckgFiles);
		ByteArrayOutputStream bos;

		try {
			if (rangeHeader == null) {
				bos = zip.getZipFile();
				final Response.ResponseBuilder response = Response.status(Response.Status.OK).type(APPLICATION_ZIP).entity(new ByteArrayInputStream(bos.toByteArray()));
				return response.build();

			}
			bos = zip.getByteRangeZipFile((int) rangeHeader.getFrom(), (int) rangeHeader.getTo());
			final String contentRange = new StringBuilder().append("bytes").append(rangeHeader.getFrom()).append("-")
					.append(rangeHeader.getTo()).append("/").append(zip.zipFileByteArrayLength()).toString();
			final Response.ResponseBuilder response = Response.status(Response.Status.PARTIAL_CONTENT).type(APPLICATION_ZIP).entity(new ByteArrayInputStream(bos.toByteArray())).header("Content-Range", contentRange);
			return response.build();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

}
