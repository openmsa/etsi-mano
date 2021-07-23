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

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;

import java.util.List;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.common.v261.services.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageFrontController;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol003Linkable;

/**
 * SOL005 - VNF Package Management Interface
 *
 * <p>
 * SOL005 - VNF Package Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
@RestController
public class VnfPackage261Sol003Controller implements VnfPackage261Sol003Api {

	private final VnfPackageFrontController vnfPackageFrontController;
	@Nonnull
	private final Linkable links = new Sol003Linkable();

	public VnfPackage261Sol003Controller(final VnfPackageFrontController vnfPackageFrontController) {
		this.vnfPackageFrontController = vnfPackageFrontController;
	}

	/**
	 * Query VNF packages information.
	 *
	 * The GET method queries the information of the VNF packages matching the filter. This method shall follow the provisions specified in the Tables 9.4.2.3.2-1 and 9.4.2.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<String> vnfPackagesGet(final MultiValueMap<String, String> requestParams) {
		return vnfPackageFrontController.search(requestParams, VnfPkgInfo.class, links::makeLinks);
	}

	/**
	 * Fetch individual VNF package artifact.
	 *
	 * The GET method fetches the content of an artifact within a VNF package. This method shall follow the provisions specified in the Tables 9.4.7.3.2-1 and 9.4.7.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final HttpServletRequest request, @RequestHeader(value = "Range", required = false) final String range) {
		return vnfPackageFrontController.getArtifact(request, getSafeUUID(vnfPkgId), range, null);
	}

	/**
	 * Read information about an individual VNF package.
	 *
	 * The GET method reads the information of a VNF package.
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId) {
		return vnfPackageFrontController.findById(getSafeUUID(vnfPkgId), VnfPkgInfo.class, links::makeLinks);
	}

	/**
	 * Fetch an on-boarded VNF package.
	 *
	 * The GET method fetches the content of a VNF package identified by the VNF package identifier allocated by the NFVO. This method shall follow the provisions specified in the Tables 9.4.5.3.2-1 and 9.4.5.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 *
	 */
	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String range) {
		return vnfPackageFrontController.getContent(getSafeUUID(vnfPkgId), range);
	}

	/**
	 * Read VNFD of an on-boarded VNF package.
	 *
	 * The GET method reads the content of the VNFD within a VNF package. The VNFD can be implemented as a single file or as a collection of multiple files. If the VNFD is implemented in the form of multiple files, a ZIP file embedding these files shall be returned. If the VNFD is implemented as a single file, either that file or a ZIP file embedding that file shall be returned. The selection of the format is controlled by the \&quot;Accept\&quot; HTTP header passed in the GET request. • If the
	 * \&quot;Accept\&quot; header contains only \&quot;text/plain\&quot; and the VNFD is implemented as a single file, the file shall be returned; otherwise, an error message shall be returned. • If the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;, the single file or the multiple files that make up the VNFD shall be returned embedded in a ZIP file. • If the \&quot;Accept\&quot; header contains both \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to
	 * the NFVO to choose the format to return for a single-file VNFD; for a multi-file VNFD, a ZIP file shall be returned. The default format of the ZIP file shall be the one specified in ETSI GS NFV-SOL 004 [5] where only the YAML files representing the VNFD, and information necessary to navigate the ZIP file and to identify the file that is the entry point for parsing the VNFD (such as TOSCA-meta or manifest files or naming conventions) are included. This method shall follow the provisions
	 * specified in the Tables 9.4.4.3.2-1 and 9.4.4.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept) {
		return vnfPackageFrontController.getVfnd(getSafeUUID(vnfPkgId), null);
	}

}
