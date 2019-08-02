package com.ubiqube.etsi.mano.controller.vnf.sol003;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfPackageSol003 {

	/**
	 * Query VNF packages information.
	 *
	 * The GET method queries the information of the VNF packages matching the
	 * filter. This method shall follow the provisions specified in the Tables
	 * 9.4.2.3.2-1 and 9.4.2.3.2-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@GetMapping(produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<String> vnfPackagesGet(@Nonnull @RequestParam Map<String, String> requestParams) throws ServiceException;

	/**
	 * Fetch individual VNF package artifact.
	 *
	 * The GET method fetches the content of an artifact within a VNF package. This
	 * method shall follow the provisions specified in the Tables 9.4.7.3.2-1 and
	 * 9.4.7.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@GetMapping(value = "/{vnfPkgId}/artifacts/{artifactPath}", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull @PathVariable("vnfPkgId") String vnfPkgId, @Nonnull @PathVariable("artifactPath") String artifactPath, @Nullable @RequestHeader("Accept") String accept, @Nullable @RequestHeader(value = "Range", required = false) String range) throws ServiceException;

	/**
	 * Read information about an individual VNF package.
	 *
	 * The GET method reads the information of a VNF package.
	 *
	 */
	@GetMapping(value = "/{vnfPkgId}", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(@Nonnull @PathVariable("vnfPkgId") String vnfPkgId, @Nullable @RequestHeader("Accept") String accept);

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
	@GetMapping(value = "/{vnfPkgId}/package_content", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull @PathVariable("vnfPkgId") String vnfPkgId, @RequestHeader("Accept") @Nullable String accept, @Nullable @RequestHeader(value = "Range", required = false) String range);

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
	@GetMapping(value = "/{vnfPkgId}/vnfd", produces = { "text/plain", "application/json", "application/octet-stream", "application/zip" }, consumes = { "application/json" })
	ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull @PathVariable("vnfPkgId") String vnfPkgId, @Nullable @RequestHeader("Accept") String accept) throws ServiceException;

}