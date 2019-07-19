package com.ubiqube.etsi.mano.controller.vnf.sol003;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.utils.RangeHeader;

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
 */
@RestController
@RequestMapping("/sol003/vnfpkgm/v1")
public class VnfPackageSol003Api implements VnfPackageSol003 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageSol003Api.class);
	private final VnfManagement vnfManagement;

	@Inject
	public VnfPackageSol003Api(VnfManagement _vnfManagement) {
		vnfManagement = _vnfManagement;
	}

	/**
	 * Query VNF packages information.
	 *
	 * The GET method queries the information of the VNF packages matching the
	 * filter. This method shall follow the provisions specified in the Tables
	 * 9.4.2.3.2-1 and 9.4.2.3.2-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<?> vnfPackagesGet(@RequestParam Map<String, String> requestParams) throws ServiceException {
		final JSONArray resp = vnfManagement.vnfPackagesGet(requestParams);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Fetch individual VNF package artifact.
	 *
	 * The GET method fetches the content of an artifact within a VNF package. This
	 * method shall follow the provisions specified in the Tables 9.4.7.3.2-1 and
	 * 9.4.7.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public Response vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@PathVariable("vnfPkgId") String vnfPkgId, @PathVariable("artifactPath") String artifactPath, @HeaderParam("Accept") String accept, @HeaderParam("Range") String range) throws ServiceException {
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPkgId, artifactPath, RangeHeader.fromValue(range));
	}

	/**
	 * Read information about an individual VNF package.
	 *
	 * The GET method reads the information of a VNF package.
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(@PathVariable("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(vnfPkgId);
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	/**
	 * Fetch an on-boarded VNF package.
	 *
	 * The GET method fetches the content of a VNF package identified by the VNF
	 * package identifier allocated by the NFVO. This method shall follow the
	 * provisions specified in the Tables 9.4.5.3.2-1 and 9.4.5.3.2-2 for URI query
	 * parameters, request and response data structures, and response codes.
	 *
	 * @throws IOException
	 * @throws ServiceException
	 *
	 */
	@Override
	public Response vnfPackagesVnfPkgIdPackageContentGet(@PathVariable("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept, @HeaderParam("Range") String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, range);
	}

	/**
	 * Read VNFD of an on-boarded VNF package.
	 *
	 * The GET method reads the content of the VNFD within a VNF package. The VNFD
	 * can be implemented as a single file or as a collection of multiple files. If
	 * the VNFD is implemented in the form of multiple files, a ZIP file embedding
	 * these files shall be returned. If the VNFD is implemented as a single file,
	 * either that file or a ZIP file embedding that file shall be returned. The
	 * selection of the format is controlled by the \&quot;Accept\&quot; HTTP header
	 * passed in the GET request. • If the \&quot;Accept\&quot; header contains only
	 * \&quot;text/plain\&quot; and the VNFD is implemented as a single file, the
	 * file shall be returned; otherwise, an error message shall be returned. • If
	 * the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;,
	 * the single file or the multiple files that make up the VNFD shall be returned
	 * embedded in a ZIP file. • If the \&quot;Accept\&quot; header contains both
	 * \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to the
	 * NFVO to choose the format to return for a single-file VNFD; for a multi-file
	 * VNFD, a ZIP file shall be returned. The default format of the ZIP file shall
	 * be the one specified in ETSI GS NFV-SOL 004 [5] where only the YAML files
	 * representing the VNFD, and information necessary to navigate the ZIP file and
	 * to identify the file that is the entry point for parsing the VNFD (such as
	 * TOSCA-meta or manifest files or naming conventions) are included. This method
	 * shall follow the provisions specified in the Tables 9.4.4.3.2-1 and
	 * 9.4.4.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public Response vnfPackagesVnfPkgIdVnfdGet(@PathVariable("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept) throws ServiceException {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, accept);
	}

}
