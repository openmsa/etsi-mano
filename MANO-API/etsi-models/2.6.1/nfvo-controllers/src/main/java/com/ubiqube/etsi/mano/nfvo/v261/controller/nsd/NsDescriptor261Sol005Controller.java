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

import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.nsd.NsDescriptorGenericFrontController;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.CreateNsdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfoLinks;

import io.swagger.annotations.Api;

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
public class NsDescriptor261Sol005Controller implements NsDescriptor261Sol005Api {
	private final NsDescriptorGenericFrontController nsDescriptorGenericFrontController;

	public NsDescriptor261Sol005Controller(final NsDescriptorGenericFrontController nsDescriptorGenericFrontController) {
		super();
		this.nsDescriptorGenericFrontController = nsDescriptorGenericFrontController;
	}

	@Override
	public ResponseEntity<String> nsDescriptorsGet(final MultiValueMap<String, String> requestParams) {
		return nsDescriptorGenericFrontController.search(requestParams, NsdInfo.class, NsDescriptor261Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdDelete(final String nsdInfoId) {
		return nsDescriptorGenericFrontController.delete(nsdInfoId);
	}

	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdGet(final String nsdInfoId) {
		return nsDescriptorGenericFrontController.finsById(nsdInfoId, NsdInfo.class, NsDescriptor261Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> nsDescriptorsNsdInfoIdNsdContentGet(final String nsdInfoId, final String accept, final String range) {
		return nsDescriptorGenericFrontController.getNsdContent(nsdInfoId, accept, range);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdContentPut(final String nsdInfoId, final String accept, final MultipartFile file) {
		return nsDescriptorGenericFrontController.putNsdContent(nsdInfoId, accept, file);
	}

	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdPatch(final String nsdInfoId, @Valid final String body, final String ifMatch) {
		return nsDescriptorGenericFrontController.modify(nsdInfoId, body, ifMatch, NsdInfo.class, NsDescriptor261Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsPost(final String contentType, @Valid final CreateNsdInfoRequest body) throws URISyntaxException {
		return nsDescriptorGenericFrontController.create(contentType, body.getUserDefinedData(), NsdInfo.class, NsDescriptor261Sol005Controller::makeLinks, NsDescriptor261Sol005Controller::makeSelfLink);
	}

	private static String makeSelfLink(final NsdInfo nsdInfo) {
		return linkTo(methodOn(NsDescriptor261Sol005Api.class).nsDescriptorsNsdInfoIdGet(nsdInfo.getId())).withSelfRel().getHref();
	}

	private static void makeLinks(@Nonnull final NsdInfo nsdInfo) {
		final String id = nsdInfo.getId();
		final NsdInfoLinks ret = new NsdInfoLinks();
		final Link nsdSelf = new Link();
		final String _self = makeSelfLink(nsdInfo);
		nsdSelf.setHref(_self);
		ret.setSelf(nsdSelf);

		final String _nsdContent = linkTo(methodOn(NsDescriptor261Sol005Api.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final Link nsdContent = new Link();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		nsdInfo.setLinks(ret);
	}

}
