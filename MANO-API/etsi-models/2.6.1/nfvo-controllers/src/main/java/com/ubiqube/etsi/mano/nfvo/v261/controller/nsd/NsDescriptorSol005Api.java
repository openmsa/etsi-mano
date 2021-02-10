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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nsd;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.nsd.NsdController;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.CreateNsdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfoLinks;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.utils.SpringUtil;

import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;

/**
 * SOL005 - NSD Management Interface
 *
 * <p>
 * SOL005 - NSD Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
@RolesAllowed({ "ROLE_OSSBSS" })
@RestController
@Api(value = "/sol005/nsd/v1/ns_descriptors")
public class NsDescriptorSol005Api implements NsDescriptorSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(NsDescriptorSol005Api.class);

	private static final String NSD_SEARCH_DEFAULT_EXCLUDE_FIELDS = "userDefinedData";

	private static final Set<String> NSD_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private final MapperFacade mapper;

	private final NsdController nsdController;

	private final ManoSearchResponseService searchService;

	public NsDescriptorSol005Api(final MapperFacade _mapper, final NsdController _nsdController, final ManoSearchResponseService _searchService) {
		mapper = _mapper;
		nsdController = _nsdController;
		searchService = _searchService;
		LOG.info("Starting NSD Management SOL005 Controller.");
	}

	/**
	 * Query information about multiple NS descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple NS descriptor resources. This method shall follow the provisions specified in the Tables 5.4.2.3.2-1 and 5.4.2.3.2-2 for URI query parameters, request and response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> nsDescriptorsGet(final String accept, final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		final List<NsdPackage> result = nsdController.nsDescriptorsGet(filter);
		final Consumer<NsdInfo> setLink = x -> x.setLinks(makeLinks(x.getId()));
		return searchService.search(fields, excludeFields, NSD_SEARCH_DEFAULT_EXCLUDE_FIELDS, NSD_SEARCH_MANDATORY_FIELDS, result, NsdInfo.class, setLink);
	}

	/**
	 * Delete an individual NS descriptor resource.
	 *
	 * The DELETE method deletes an individual NS descriptor resource. An individual NS descriptor resource can only be deleted when there is no NS instance using it (i.e. usageState &#x3D; NOT_IN_USE) and has been disabled already (i.e. operationalState &#x3D; DISABLED). Otherwise, the DELETE method shall fail.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdDelete(final String nsdInfoId) {
		final UUID nsdInfoUuid = UUID.fromString(nsdInfoId);
		nsdController.nsDescriptorsNsdInfoIdDelete(nsdInfoUuid);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read information about an individual NS descriptor resource.
	 *
	 * \&quot;The GET method reads information about an individual NS descriptor. This method shall follow the provisions specified in GS NFV-SOL 005 Tables 5.4.3.3.2-1 and 5.4.3.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdGet(final String nsdInfoId) {
		final NsdPackage nsdPackage = nsdController.nsDescriptorsNsdInfoIdGet(UUID.fromString(nsdInfoId));
		final NsdInfo nsdInfo = mapper.map(nsdPackage, NsdInfo.class);
		nsdInfo.setLinks(makeLinks(nsdInfoId));
		return new ResponseEntity<>(nsdInfo, HttpStatus.OK);
	}

	/**
	 * Fetch the content of a NSD.
	 *
	 * The GET method fetches the content of the NSD. The NSD can be implemented as a single file or as a collection of multiple files. If the NSD is implemented in the form of multiple files, a ZIP file embedding these files shall be returned. If the NSD is implemented as a single file, either that file or a ZIP file embedding that file shall be returned. The selection of the format is controlled by the \&quot;Accept\&quot; HTTP header passed in the GET request:• If the \&quot;Accept\&quot; header
	 * contains only \&quot;text/plain\&quot; and the NSD is implemented as a single file, the file shall be returned; otherwise, an error message shall be returned.• If the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;, the single file or the multiple files that make up the NSD shall be returned embedded in a ZIP file.• If the \&quot;Accept\&quot; header contains both \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to the NFVO to choose the format to
	 * return for a single-file NSD; for a multi-file NSD, a ZIP file shall be returned.NOTE: The structure of the NSD zip file is outside the scope of the present document.
	 *
	 * @return
	 *
	 */
	@Override
	public ResponseEntity<List<ResourceRegion>> nsDescriptorsNsdInfoIdNsdContentGet(final String nsdInfoId, final String accept, final String range) {
		final byte[] bytes = nsdController.nsDescriptorsNsdInfoIdNsdContentGet(UUID.fromString(nsdInfoId));
		return SpringUtil.handleBytes(bytes, range);
	}

	/**
	 * Upload the content of a NSD.
	 *
	 * \&quot;The PUT method is used to upload the content of a NSD. The NSD to be uploaded can be implemented as a single file or as a collection of multiple files, as defined in clause 5.4.4.3.2 of GS NFV-SOL 005. If the NSD is implemented in the form of multiple files, a ZIP file embedding these files shall be uploaded. If the NSD is implemented as a single file, either that file or a ZIP file embedding that file shall be uploaded. The \&quot;Content-Type\&quot; HTTP header in the PUT request
	 * shall be set accordingly based on the format selection of the NSD. If the NSD to be uploaded is a text file, the \&quot;Content-Type\&quot; header is set to \&quot;text/plain\&quot;. If the NSD to be uploaded is a zip file, the \&quot;Content-Type\&quot; header is set to \&quot;application/zip\&quot;. This method shall follow the provisions specified in the Tables 5.4.4.3.3-1 and 5.4.4.3.3-2 of GS-NFV-SOL 005 for URI query parameters, request and response data structures, and response
	 * codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdContentPut(final String nsdInfoId, final String accept, final MultipartFile file) {
		try (InputStream is = file.getInputStream()) {
			nsdController.nsDescriptorsNsdInfoIdNsdContentPut(UUID.fromString(nsdInfoId), is);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.accepted().build();
	}

	/**
	 * Modify the operational state and/or the user defined data of an individual NS descriptor resource.
	 *
	 * The PATCH method modifies the operational state and/or user defined data of an individual NS descriptor resource. This method can be used to: 1) Enable a previously disabled individual NS descriptor resource, allowing again its use for instantiation of new network service with this descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not change as result. 2) Disable a previously enabled individual NS descriptor resource, preventing any further use for instantiation of new
	 * network service(s) with this descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not changes a result. 3) Modify the user defined data of an individual NS descriptor resource.
	 *
	 */
	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdPatch(final String nsdInfoId, final String body, final String contentType) {
		final NsdPackage nsdPkgInfo = nsdController.nsDescriptorsNsdInfoIdPatch(UUID.fromString(nsdInfoId), body);
		final NsdInfo ret = mapper.map(nsdPkgInfo, NsdInfo.class);
		ret.setLinks(makeLinks(nsdInfoId));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new version of an on-boarded NS descriptor.
	 *
	 */
	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsPost(final String contentType, final CreateNsdInfoRequest nsDescriptorsPostQuery) {
		final Map<String, String> userDefinedData = nsDescriptorsPostQuery.getUserDefinedData();
		final NsdPackage nsdPackage = nsdController.nsDescriptorsPost(userDefinedData);

		final NsdInfo nsdDescriptor = mapper.map(nsdPackage, NsdInfo.class);
		nsdDescriptor.setLinks(makeLinks(nsdDescriptor.getId()));
		return new ResponseEntity<>(nsdDescriptor, HttpStatus.OK);
	}

	private static NsdInfoLinks makeLinks(@Nonnull final String id) {
		final NsdInfoLinks ret = new NsdInfoLinks();
		final Link nsdSelf = new Link();
		final String _self = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdGet(id)).withSelfRel().getHref();
		nsdSelf.setHref(_self);
		ret.setSelf(nsdSelf);

		final String _nsdContent = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final Link nsdContent = new Link();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		return ret;
	}

}
