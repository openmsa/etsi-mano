package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.controller.BaseApi;
import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.exception.BadRequestException;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagePostQuery;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinksSelf;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.ManufacturerModel;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.utils.RangeHeader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.json.JSONArray;

/**
 * SOL005 - VNF Package Management Interface
 *
 * <p>
 * SOL005 - VNF Package Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * NOTE: Normaly we should receive object in methods but Genson seems to be on
 * the classpath and is unable to unserialize objects. So we use a string2Object
 * to do So. Note same problems occurred when returning object some times genson
 * could be here and not Jackson, in this case you can use object2String.
 *
 */
@RestController
//@Path("/sol005/vnfpkgm/v1")
@Api(value = "/sol005/vnfpkgm/v1")
public class VnfPackageSol005Api extends BaseApi implements VnfPackageSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageSol005Api.class);

	private static final String SOL005 = "SOL005";
	private final VnfManagement vnfManagement;
	private final ManufacturerModel manufacturerModel;
	private final DeviceService deviceService;

	private final RepositoryService repositoryService;

	private final VnfPackageRepository vnfPackageRepository;

	private final Patcher patcher;

	@Inject
	public VnfPackageSol005Api(VnfManagement _vnfManagement, Patcher _patcher, ObjectMapper _mapper, VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService, ManufacturerModel _manufacturerModel, DeviceService _deviceService) {
		super(_mapper);
		vnfManagement = _vnfManagement;
		manufacturerModel = _manufacturerModel;
		deviceService = _deviceService;
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
	}

	@GET
	@Path("/subs")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Query multiple subscriptions.", tags = {})
	public List<SubscriptionsPkgmSubscription> subscriptionsGet2(@Context UriInfo info) {
		final MultivaluedMap<String, String> queryParameter = info.getQueryParameters();
		LOG.info("qp => {}", queryParameter);
		return new ArrayList<>();
	}

	@Override
	@GET
	@Path("/vnf_packages")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Query VNF packages information.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information of the selected VNF packages. ", response = Object.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesGet(@RequestParam Map<String, String> requestParams) throws ServiceException {
		final JSONArray resp = vnfManagement.vnfPackagesGet(requestParams);
		return Response.ok(resp).build();
	}

	@Override
	@GET
	@Path("/vnf_packages/{vnfPkgId}/artifacts/{artifactPath:.*}")
	@Produces({ MediaType.APPLICATION_JSON, "application/zip" })
	@ApiOperation(value = "Fetch individual VNF package artifact.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK  On success, the content of the artifact is returned. The payload body shall contain a copy of the artifact file from the VNF package, as defined by ETSI GS NFV-SOL 004. The \"Content-Type\" HTTP header shall be set according to the content type of the artifact file. If the content type cannot be determined, the header shall be set to the value \"application/octet-stream\". "), @ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the VNF package file is returned. The response body shall contain the requested part of the VNF package file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to any of the following scenarios: - Disable a VNF package resource of hich the operational state is not ENABLED - Enable a VNF package resource of which the operational state is not DISABLED The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@PathParam("vnfPkgId") String vnfPkgId, @PathParam("artifactPath") String artifactPath, @HeaderParam("Accept") String accept, @HeaderParam("Range") String range) throws ServiceException {
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPkgId, artifactPath, RangeHeader.fromValue(range));
	}

	@Override
	@GET
	@Path("/vnf_packages/{vnfPkgId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Read information about an individual VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK Information of the VNF package.             ", response = VnfPackagesVnfPkgIdGetResponse.class), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdGet(@PathParam("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(vnfPkgId);
		final VnfPackagesVnfPkgIdGetResponse resp = new VnfPackagesVnfPkgIdGetResponse();
		resp.setVnfPkgInfo(vnfPkgInfo);
		return Response.ok(resp).build();
	}

	@Override
	@GET
	@Path("/vnf_packages/{vnfPkgId}/package_content")
	@Produces({ MediaType.APPLICATION_JSON, "application/zip" })
	@ApiOperation(value = "Fetch an on-boarded VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK On success, a copy of the VNF package file is returned. The response body shall include a copy of the VNF package file. The \"Content-Type\" HTTP header shall be set according to the type of the file, i.e. to \"application/zip\" for a VNF Package as defined in ETSI GS NFV-SOL 004 [5]. "), @ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the NSD file is returned. The response body shall contain the requested part of the NSD file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233 [23]. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response.       ", response = ProblemDetails.class), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact that \"onboardingState\" of the VNF package has a value different from \"ONBOARDED\". The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdPackageContentGet(@PathParam("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept, @HeaderParam("Range") String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, range);
	}

	@Override
	@GET
	@Path("/vnf_packages/{vnfPkgId}/vnfd")
	@Produces({ MediaType.TEXT_PLAIN, "application/zip" })
	@ApiOperation(value = "Read VNFD of an on-boarded VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK On success, the content of the VNFD is returned. The payload body shall contain a copy of the file representing the VNFD or a ZIP file that contains the file or multiple files representing the VNFD, as specified above. The \"Content-Type\" HTTP header shall be set according to the format of the returned file, i.e. to \"text/plain\" for a YAML file or to \"application/zip\" for a ZIP file. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class), @ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class), @ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact that \"onboardingState\" of the VNF package has a value different from \"ONBOARDED\". The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error.         ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdVnfdGet(@PathParam("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept) throws ServiceException {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, accept);
	}

	/**
	 * Create a new individual VNF package resource. 9.5.2.4
	 *
	 * The POST method creates a new individual VNF package resource.
	 *
	 */
	@Override
	@POST
	@Path("/vnf_packages")
	@Consumes({ "*/*" })
	@Produces({ "*/*" })
	@ApiOperation(value = "Create a new individual VNF package resource.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "201 Created             An individual VNF package resource has been created successfully. The response body shall contain a representation of the new individual VNF package resource, as defined in clause 9.5.2.4. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the individual VNF package resource. ", response = VnfPackagesVnfPkgIdGetResponse.class) })
	public Response vnfPackagesPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, VnfPackagePostQuery vnfPackagePostQuery) {

		final String vnfPkgId = UUID.randomUUID().toString();
		final Object jsonString = vnfPackagePostQuery.getCreateVnfPkgInfoRequest().getUserDefinedData();
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		vnfPkgInfo.setId(vnfPkgId);
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.CREATED);

		try {
			final StringBuilder sb = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId);
			String uri = sb.toString();
			if (!repositoryService.exists(uri)) {
				repositoryService.addDirectory(uri, "", SOL005, NCROOT);
			}

			uri = sb.append("/").append("Metadata.yaml").toString();
			try {
				repositoryService.addFile(uri, "", "Added: SOL005", mapper.writeValueAsString(jsonString), NCROOT);

			} catch (final JsonProcessingException e) {
				throw new GenericException(e);
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		vnfPkgInfo.setUserDefinedData(jsonString);

		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		final VnfPackagesVnfPkgInfoLinks links = new VnfPackagesVnfPkgInfoLinks();
		final VnfPackagesVnfPkgInfoLinksSelf self = new VnfPackagesVnfPkgInfoLinksSelf();
		self.setHref(linkTo(methodOn(VnfPackageSol005Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref());
		links.self(self);
		vnfPkgInfo.setLinks(links);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.DISABLED);
		vnfPkgInfo.setUsageState(UsageStateEnum.NOT_IN_USE);
		final Map<String, Object> userData = (Map<String, Object>) vnfPkgInfo.getUserDefinedData();
		String content;
		try {
			content = mapper.writeValueAsString(userData.get("heat"));
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
		checkUserData(userData);
		vnfPackageRepository.save(vnfPkgInfo);
		// Return.
		URI uri;
		try {
			uri = new URI(self.getHref());
			repositoryService.addFile(REPOSITORY_NVFO_DATAFILE_BASE_PATH + '/' + vnfPkgId + "/vnfd.json", SOL005, "", content, NCROOT);
		} catch (final URISyntaxException | ServiceException e) {
			throw new GenericException(e);
		}
		return Response.status(201).contentLocation(uri).entity(vnfPackagesVnfPkgIdGetResponse).build();
	}

	private void checkUserData(Map<String, Object> userData) {
		final String vimId = (String) userData.get("vimId");
		if (null == vimId) {
			throw new BadRequestException("vimId could not be null");
		}
		try {
			deviceService.getDeviceId(vimId);
		} catch (final ServiceException e) {
			throw new BadRequestException("vimId is not found in MSA.", e);
		}
		final String manufacturerId = (String) userData.get("manufacturerId");
		assert (null != manufacturerId);
		final String modelId = (String) userData.get("modelId");
		assert (null != modelId);
		// Probably not the best place to do that.
		final String manufacturer = manufacturerModel.getManufacturerById(manufacturerId);
		final String model = manufacturerModel.getModelById(manufacturerId, modelId);
		userData.put("manufacturer", manufacturer);
		userData.put("model", model);
	}

	/**
	 * Delete an individual VNF package.
	 *
	 * The DELETE method deletes an individual VNF Package resource.
	 *
	 */
	@Override
	@DELETE
	@Path("/vnf_packages/{vnfPkgId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Delete an individual VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The VNF package was deleted successfully. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to any of the following scenarios: - Disable a VNF package resource of hich the operational state is not ENABLED - Enable a VNF package resource of which the operational state is not DISABLED The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdDelete(@PathParam("vnfPkgId") String vnfPkgId) {
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(sanitize(vnfPkgId)).toString();
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if (!"DISABLED".equals(vnfPkgInfo.getOperationalState())) {
			throw new ConflictException("Packaged is enabled.");
		}
		boolean isVnfdMetafile = false;
		try {
			isVnfdMetafile = repositoryService.exists(uri);
		} catch (final ServiceException e) {
			throw new NotFoundException("No such object: " + vnfPkgId, e);
		}
		if (isVnfdMetafile) {
			final RepositoryElement repositoryElement = repositoryService.getElement(uri);
			repositoryService.deleteRepositoryElement(repositoryElement, NCROOT);
			return null;
		}
		throw new NotFoundException("No such object: " + vnfPkgId);
	}

	/**
	 * Update information about an individual VNF package.
	 *
	 * \&quot;The PATCH method updates the information of a VNF package.\&quot;
	 * \&quot;This method shall follow the provisions specified in the Tables
	 * 9.4.3.3.4-1 and 9.4.3.3.4-2 for URI query parameters, request and response
	 * data structures, and response codes.\&quot;
	 *
	 */
	@Override
	@javax.ws.rs.PATCH
	@Path("/vnf_packages/{vnfPkgId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Update information about an individual VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK The operation was completed successfully. The response body shall contain attribute modifications for an \"Individual VNF package\" resource ", response = Object.class), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to any of the following scenarios: - Disable a VNF package resource of hich the operational state is not ENABLED - Enable a VNF package resource of which the operational state is not DISABLED The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 416, message = "Requested Range Not Satisfiable The byte range passed in the \"Range\" header did not match any available byte range in the VNF package file (e.g. \"access after end of file\"). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class), @ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Object vnfPackagesVnfPkgIdPatch(@PathParam("vnfPkgId") String vnfPkgId, String body, @HeaderParam("Content-Type") String contentType) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if ("DISABLED".equals(vnfPkgInfo.getOperationalState())) {
			throw new ConflictException("Could not patch a disabled VNF Package.");
		}
		patcher.patch(body, vnfPkgInfo);
		vnfPackageRepository.save(vnfPkgInfo);

		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPackagesVnfPkgIdGetResponse;
	}

	/**
	 * Upload a VNF package by providing the content of the VNF package.
	 *
	 * The PUT method uploads the content of a VNF package. This method shall follow
	 * the provisions specified in the Tables 9.4.5.3.3-1 and 9.4.5.3.3-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	@PUT
	@Path("/vnf_packages/{vnfPkgId}/package_content")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Upload a VNF package by providing the content of the VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The VNF package was accepted for uploading, but the processing has not been completed. It is expected to take some time for processing. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact that the on boarding state of the VNF package resource is not CREATED . The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdPackageContentPut(@PathParam("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept,
			/* @Multipart(value = "file", required = false) FileInputStream fileDetail, */
			// OK:FormDataMultiPart multipart
			@FormDataParam("file") InputStream fileDetail,
			// OK:@FormDataParam("file") FormDataBodyPart part
			@FormDataParam("file") FormDataContentDisposition part) {
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).toString();
		try {
			if (!repositoryService.exists(uri)) {
				throw new NotFoundException("No such object: " + vnfPkgId);
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		if (isZip(part.getType())) {
			// Normally we should do an asynchrone call
			try {
				unzip(vnfPkgId, fileDetail);
			} catch (final Exception e) {
				throw new GenericException(e);
			}
		}
		return Response.ok().build();
	}

	/**
	 * Upload a VNF package by providing the address information of the VNF package.
	 *
	 * The POST method provides the information for the NFVO to get the content of a
	 * VNF package. This method shall follow the provisions specified in the Tables
	 * 9.4.6.3.1-1 and 9.4.6.3.1-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	@POST
	@Path("/vnf_packages/{vnfPkgId}/package_content/upload_from_uri")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "Upload a VNF package by providing the address information of the VNF package.", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 202, message = "202 Accepted The information about the VNF package was received successfully, but the on-boarding has not been completed. It is expected to take some time for processing. The response body shall be empty. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class), @ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the VNFD, the NFVO shall respond with this response code.         ", response = ProblemDetails.class), @ApiResponse(code = 409, message = "Conflict. Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact that the on boarding state of the VNF package resource is not CREATED . The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	public Response vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @PathParam("vnfPkgId") String vnfPkgId, VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest vnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		if (!"CREATED".equals(vnfPkgInfo.getOnboardingState())) {
			throw new ConflictException("Onboarding state is not correct.");
		}

		final LinkedHashMap<String, String> uddList = (LinkedHashMap<String, String>) vnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest.getUploadVnfPkgFromUriRequest().getUserDefinedData();
		final String uri = uddList.get("url");
		final InputStream content = getUrlContent(uri);
		try {
			unzip(vnfPkgId, content);
		} catch (final Exception e) {
			throw new GenericException(e);
		}

		return Response.status(202).build();
	}

	private InputStream getUrlContent(String uri) {
		URL url;
		try {
			url = new URL(uri);
			return (InputStream) url.getContent();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private void unzip(String vnfPkgId, InputStream fileDetail) throws IOException, ServiceException {
		final ZipInputStream zis = new ZipInputStream(fileDetail);
		ZipEntry ze;
		while ((ze = zis.getNextEntry()) != null) {
			String fileName = ze.getName();
			fileName = sanitize(fileName);
			if (ze.isDirectory()) {
				// XXX/ Fix Path ending by '/'
				if (fileName.endsWith("/")) {
					fileName = fileName.substring(0, fileName.length() - 1);
				}
				final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(fileName).toString();
				repositoryService.addDirectory(uri, "", SOL005, NCROOT);
				continue;
			}

			final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(fileName).toString();
			final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
			final byte[] buffer = new byte[1024];
			int read;
			while ((read = zis.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
			repositoryService.addFile(uri, SOL005, "", baos.toByteArray(), NCROOT);
		}
	}

	private boolean isZip(String httpAccept) {
		if ("application/zip".equals(httpAccept)) {
			return true;
		} else if ("application/x-zip-compressed".equals(httpAccept)) {
			return true;
		}
		return false;
	}

}
