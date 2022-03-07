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

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.vnfm.fc.vnflcm.VnfLcmOpOccGenericFrontController;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOccLinks;

@RestController
public class VnfLcmOpOccs261Sol003Controller implements VnfLcmOpOccs261Sol003Api {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmOpOccs261Sol003Controller.class);

	private final VnfLcmOpOccGenericFrontController frontController;

	public VnfLcmOpOccs261Sol003Controller(final VnfLcmOpOccGenericFrontController frontController) {
		this.frontController = frontController;
		LOG.info("Starting VNF LCM OP OCCS SOL003/2.6.1 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfLcmOpOccsGet(final MultiValueMap<String, String> requestParams) {
		return frontController.search(requestParams, VnfLcmOpOcc.class, VnfLcmOpOccs261Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccCancel(UUID.fromString(vnfLcmOpOccId));
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdFailPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccFail(UUID.fromString(vnfLcmOpOccId));
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdGet(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccFindById(new VnfLcmClassMaping261(), UUID.fromString(vnfLcmOpOccId), VnfLcmOpOcc.class,
				VnfLcmOpOccs261Sol003Controller::makeLinks, VnfLcmOpOccs261Sol003Controller::setOperationParams);
	}

	private static void setOperationParams(final VnfLcmOpOcc lcmOpOcc, final Object obj) {
		lcmOpOcc.setOperationParams(obj);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccRetry(UUID.fromString(vnfLcmOpOccId));
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccRollback(UUID.fromString(vnfLcmOpOccId));
	}

	private static void makeLinks(@NotNull final VnfLcmOpOcc vnfLcmOpOcc) {
		@NotNull
		final String id = vnfLcmOpOcc.getId();
		final VnfLcmOpOccLinks links = new VnfLcmOpOccLinks();
		final Link cancel = new Link();
		cancel.setHref(linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdCancelPost(id)).withSelfRel().getHref());
		links.setCancel(cancel);

		final Link fail = new Link();
		fail.setHref(linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdFailPost(id)).withSelfRel().getHref());
		links.setFail(fail);

		// XXX We can't have this grant link directly, because of classpath on
		// interface.
		// grant.setHref(linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(vnfLcmOpOcc.getGrantId(),"")).withSelfRel().getHref());

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		links.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		links.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref());
		links.setSelf(self);

		final Link vnfInstance = new Link();
		vnfInstance.setHref(linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdGet(vnfLcmOpOcc.getId())).withSelfRel().getHref());
		links.setVnfInstance(vnfInstance);

		vnfLcmOpOcc.setLinks(links);
	}

}
