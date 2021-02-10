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
package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;

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
@Api(value = "onboarded_vnf_packages", description = "the onboarded_vnf_packages API")
@RequestMapping("/sol003/vnfpkgm/v1/onboarded_vnf_packages")
public interface OnboardedVnfPackages261ApiSol003 {

	@ApiOperation(value = "", nickname = "onboardedVnfPackagesGet", notes = "Query VNF Package Info. The GET method queries the information of the VNF packages matching the filter. This method shall follow the provisions specified in the tables 10.4.2.3.2-1 and 10.4.2.3.2-2 for URI query parameters, request and response data structures, and response codes. ", response = VnfPkgInfo.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when information about zero or more VNF packages has been queried successfully. The response body shall contain in an array the VNF package info representations that match the attribute filter, i.e. zero or more VNF package info representations as defined in clause 10.5.2.2. If the \"filter\" URI parameter or one of the \"all_fields\", \"fields\" (if supported), \"exclude_fields\" (if supported) or \"exclude_default\" URI parameters was supplied in the request, the data in the response body shall have been transformed according to the rules specified in clauses 5.2.2 and 5.3.2 of ETSI GS NFV-SOL 013, respectively. If the VNFM supports alternative 2 (paging) according to clause 5.4.2.1 of ETSI GS NFV-SOL 013 for this resource, inclusion of the Link HTTP header in this response shall follow the provisions in clause 5.4.2.3 of ETSI GS NFV-SOL 013. ", response = VnfPkgInfo.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = ""),
			@ApiResponse(code = 401, message = ""),
			@ApiResponse(code = 403, message = ""),
			@ApiResponse(code = 404, message = ""),
			@ApiResponse(code = 405, message = ""),
			@ApiResponse(code = 406, message = ""),
			@ApiResponse(code = 422, message = ""),
			@ApiResponse(code = 500, message = ""),
			@ApiResponse(code = 503, message = ""),
			@ApiResponse(code = 504, message = "") })
	@GetMapping(produces = { "application/json" })
	ResponseEntity<List<VnfPkgInfo>> onboardedVnfPackagesGet(
			@ApiParam(value = "Attribute-based filtering expression according to clause 5.2 of ETSI GS NFV SOL 013. The VNFM shall support receiving this parameter as part of the  URI query string. The NFVO may supply this parameter.  All attribute names that appear in the VnfPkgInfo and in data  types referenced from it shall be supported by the VNFM in the  filter expression. ") @Valid @RequestParam(value = "filter", required = false) final String filter,
			@ApiParam(value = "Include all complex attributes in the response. See clause 5.3 of ETSI GS NFV SOL 013 for details. The VNFM shall support this parameter. ") @Valid @RequestParam(value = "all_fields", required = false) final String allFields,
			@ApiParam(value = "Complex attributes to be included into the response. See clause 5.3 of ETSI GS NFV SOL 013 for details. The VNFM should support this parameter. ") @Valid @RequestParam(value = "fields", required = false) final String fields,
			@ApiParam(value = "Complex attributes to be excluded from the response. See clause 5.3 of ETSI GS NFV SOL 013 for details. The VNFM should support this parameter. ") @Valid @RequestParam(value = "exclude_fields", required = false) final String excludeFields,
			@ApiParam(value = "Indicates to exclude the following complex attributes from the response. See clause 5.3 of ETSI GS NFV SOL 013 for details. The VNFM shall support this parameter. The following attributes shall be excluded from the VnfPkgInfo structure in the response body if this parameter is provided, or none of the parameters \"all_fields,\" \"fields\", \"exclude_fields\", \"exclude_default\" are provided: - softwareImages - additionalArtifacts - userDefinedData. ") @Valid @RequestParam(value = "exclude_default", required = false) final String excludeDefault,
			@ApiParam(value = "Marker to obtain the next page of a paged response. Shall be supported by the  NFVO if the NFVO supports alternative 2 (paging) according to clause 5.4.2.1 of ETSI GS NFV-SOL 013 for this resource. ") @Valid @RequestParam(value = "nextpage_opaque_marker", required = false) final String nextpageOpaqueMarker);

	@ApiOperation(value = "", nickname = "onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet", notes = "Fetch VNF Package Artifacts. The GET method fetches the content of an artifact within a VNF package. This method shall follow the provisions specified in the tables 10.4.6.3.2-1 and 10.4.6.3.2-2 for URI query parameters, request and response data structures, and response codes. ", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when the whole content of the artifact file has been read successfully. The payload body shall contain a copy of the artifact file from the VNF package, as defined by ETSI GS NFV-SOL 004. The \"Content-Type\" HTTP header shall be set according to the content type of the artifact file. If the content type cannot be determined, the header shall be set to the value \"application/octet-stream\". "),
			@ApiResponse(code = 206, message = "206 PARTIAL CONTENT If the NFVO supports range requests, this response shall be returned when a single consecutive byte range from the content of the artifact file, if the NFVO supports range requests has been read successfully according to the request. The response body shall contain the requested part of the VNF package file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response. "),
			@ApiResponse(code = 400, message = ""),
			@ApiResponse(code = 401, message = ""),
			@ApiResponse(code = 403, message = ""),
			@ApiResponse(code = 404, message = ""),
			@ApiResponse(code = 405, message = ""),
			@ApiResponse(code = 406, message = ""),
			@ApiResponse(code = 409, message = ""),
			@ApiResponse(code = 416, message = ""),
			@ApiResponse(code = 500, message = ""),
			@ApiResponse(code = 503, message = ""),
			@ApiResponse(code = 504, message = "") })
	@GetMapping(value = "/{vnfdId}/artifacts/**", produces = { "application/json", "application/zip" })
	ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(
			@ApiParam(value = "Sequence of one or more path segments representing the path of the artifact within the VNF package, relative to the root of the package. EXAMPLE: foo/bar/run.sh This identifier can be retrieved from the \"artifactPath\" attribute of the applicable \"additionalArtifacts\" entry in the body of the response to a GET request querying the \"Individual VNF package\" or the \"VNF packages\" resource. ", required = true) final HttpServletRequest request,
			@ApiParam(value = "Identifier of the VNFD and the VNF package. The identifier is allocated by the VNF provider. This identifier can be retrieved from the \"vnfdId\" attribute in the VnfPackageOnboardingNotification or VnfPackageChangeNotification. ", required = true) @PathVariable("vnfdId") final String vnfdId,
			@ApiParam(value = "The request may contain a \"Range\" HTTP header to obtain single range of bytes from the VNF package file. This can be used to continue an aborted transmission. If the NFVO does not support range requests, it should return the whole file with a 200 OK response instead. ") @RequestHeader(value = "Range", required = false) final String range,
			@Valid final String includeSignatures);

	@ApiOperation(value = "", nickname = "onboardedVnfPackagesVnfdIdGet", notes = "Query VNF Package Info. The GET method reads the information of an individual VNF package. This method shall follow the provisions specified in the tables 10.4.3.3.2-1 and 10.4.3.3.2-2 for URI query parameters, request and response data structures, and response codes. ", response = VnfPkgInfo.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when information of the VNF package has been read successfully. The response body shall contain the VNF package info representation defined in clause 10.5.2.2. ", response = VnfPkgInfo.class),
			@ApiResponse(code = 400, message = ""),
			@ApiResponse(code = 401, message = ""),
			@ApiResponse(code = 403, message = ""),
			@ApiResponse(code = 404, message = ""),
			@ApiResponse(code = 405, message = ""),
			@ApiResponse(code = 406, message = ""),
			@ApiResponse(code = 416, message = ""),
			@ApiResponse(code = 500, message = ""),
			@ApiResponse(code = 503, message = ""),
			@ApiResponse(code = 504, message = "") })
	@GetMapping(value = "/{vnfdId}", produces = { "application/json" })
	ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(
			@ApiParam(value = "Identifier of the VNFD and the VNF package. The identifier is allocated by the VNF provider. This identifier can be retrieved from the \"vnfdId\" attribute in the VnfPackageOnboardingNotification or VnfPackageChangeNotification. ", required = true) @PathVariable("vnfdId") final String vnfdId);

	@ApiOperation(value = "", nickname = "onboardedVnfPackagesVnfdIdPackageContentGet", notes = "Fetch VNF Package. The GET method fetches the content of a VNF package identified by the VNF package identifier allocated by the NFVO. This method shall follow the provisions specified in the tables 10.4.5.3.2-1 and 10.4.5.3.2-2 for URI query parameters, request and response data structures, and response codes. ", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when the whole content of the VNF package file has been read successfully. The response body shall include a copy of the VNF package file. The \"Content-Type HTTP\" header shall be set according to the type of the file, i.e. to \"application/zip\" for a VNF Package as defined in ETSI GS  NFV SOL  004. "),
			@ApiResponse(code = 206, message = "206 PARTIAL CONTENT If the NFVO supports range requests, this response shall be returned when a single consecutive byte range from the content of the VNF package file has been read successfully according to the request. The response body shall contain the requested part of the VNF package file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response. "),
			@ApiResponse(code = 400, message = ""),
			@ApiResponse(code = 401, message = ""),
			@ApiResponse(code = 403, message = ""),
			@ApiResponse(code = 404, message = ""),
			@ApiResponse(code = 405, message = ""),
			@ApiResponse(code = 406, message = ""),
			@ApiResponse(code = 409, message = ""),
			@ApiResponse(code = 416, message = ""),
			@ApiResponse(code = 500, message = ""),
			@ApiResponse(code = 503, message = ""),
			@ApiResponse(code = 504, message = "") })
	@GetMapping(value = "/{vnfdId}/package_content")
	ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(
			@ApiParam(value = "Identifier of the VNFD and the VNF package. The identifier is allocated by the VNF provider. This identifier can be retrieved from the \"vnfdId\" attribute in the VnfPackageOnboardingNotification or VnfPackageChangeNotification. ", required = true) @PathVariable("vnfdId") final String vnfdId,
			@ApiParam(value = "The request may contain a \"Range\" HTTP header to obtain single range of bytes from the VNF package file. This can be used to continue an aborted transmission. If the NFVO does not support range requests, it should return the whole file with a 200 OK response instead. ") @RequestHeader(value = "Range", required = false) final String range);

	@ApiOperation(value = "", nickname = "onboardedVnfPackagesVnfdIdVnfdGet", notes = "Query VNF Package Info  The GET method reads the content of the VNFD within a VNF package. The VNFD can be implemented as a single file or as a collection of multiple files. If the VNFD is implemented in the form of multiple files, a ZIP file embedding these files shall be returned. If the VNFD is implemented as a single file, either that file or a ZIP file embedding that file shall be returned. The selection of the format is controlled by the \"Accept\" HTTP header passed in the GET request. * If the \"Accept\" header contains only \"text/plain\" and the VNFD is   implemented as a single file, the file shall be returned;   otherwise, an error message shall be returned. * If the \"Accept\" header contains only \"application/zip\", the single   file or the multiple files that make up the VNFD shall be returned   embedded in a ZIP file. * If the \"Accept\" header contains both \"text/plain\" and   \"application/zip\", it is up to the NFVO to choose the format to   return for a single-file VNFD; for a multi-file VNFD, a ZIP file   shall be returned. The default format of the ZIP file shall be the one specified in ETSI GS NFV-SOL 004 where only the YAML files representing the VNFD, and information needed to navigate the ZIP file and to identify the file that is the entry point for parsing the VNFD (such as TOSCA-meta or manifest files or naming conventions) are included. ", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK Shall be returned when the content of the VNFD has been read successfully. The payload body shall contain a copy of the file representing the VNFD or a ZIP file that contains the file or multiple files representing the VNFD, as specified above. The \"Content-Type\" HTTP header shall be set according to the format of the returned file, i.e. to \"text/plain\" for a YAML file or to \"application/zip\" for a ZIP file. "),
			@ApiResponse(code = 400, message = ""),
			@ApiResponse(code = 401, message = ""),
			@ApiResponse(code = 403, message = ""),
			@ApiResponse(code = 404, message = ""),
			@ApiResponse(code = 405, message = ""),
			@ApiResponse(code = 406, message = ""),
			@ApiResponse(code = 409, message = ""),
			@ApiResponse(code = 416, message = ""),
			@ApiResponse(code = 500, message = ""),
			@ApiResponse(code = 503, message = ""),
			@ApiResponse(code = 504, message = "") })
	@GetMapping(value = "/onboarded_vnf_packages/{vnfdId}/vnfd")
	ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(@ApiParam(value = "Identifier of the VNFD and the VNF package. The identifier is allocated by the VNF provider. This identifier can be retrieved from the \"vnfdId\" attribute in the VnfPackageOnboardingNotification or VnfPackageChangeNotification. ", required = true) @PathVariable("vnfdId") final String vnfdId,
			@Valid final String includeSignatures);

}
