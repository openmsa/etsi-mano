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

package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.getSingleField;
import static com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm.VnfLcmConstants.VNFLCMOPOCC_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm.VnfLcmConstants.VNFLCMOPOCC_SEARCH_MANDATORY_FIELDS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.nslcm.VnfLcmController;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.AffectedVnfc;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOccResourceChanges;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfLcmOpOccsSol003Api implements VnfLcmOpOccsSol003 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmOpOccsSol003Api.class);

	private final MapperFacade mapper;

	private final VnfLcmController vnfLcmController;

	private final ManoSearchResponseService searchService;

	public VnfLcmOpOccsSol003Api(final MapperFacade _mapper, final VnfLcmController _vnfLcmController, final ManoSearchResponseService _searchService) {
		mapper = _mapper;
		vnfLcmController = _vnfLcmController;
		searchService = _searchService;
		LOG.info("Starting VNF LCM OP OCCS SOL003 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfLcmOpOccsGet(final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		final List<VnfBlueprint> result = vnfLcmController.vnfLcmOpOccsGet(filter);
		final Consumer<VnfLcmOpOcc> setLink = x -> x.setLinks(makeLink(x));
		return searchService.search(requestParams, VnfLcmOpOcc.class, VNFLCMOPOCC_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFLCMOPOCC_SEARCH_MANDATORY_FIELDS, result, VnfLcmOpOcc.class, setLink);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(final String vnfLcmOpOccId, final String version) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdFailPost(final String vnfLcmOpOccId, final String version) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdGet(final String vnfLcmOpOccId) {
		final VnfBlueprint resultDb = vnfLcmController.vnfLcmOpOccsVnfLcmOpOccIdGet(UUID.fromString(vnfLcmOpOccId));
		final VnfLcmOpOcc entity = mapper.map(resultDb, VnfLcmOpOcc.class);
		final VnfLcmOpOccResourceChanges resourceChanged = new VnfLcmOpOccResourceChanges();
		resultDb.getTasks().stream()
				.filter(x -> x.getType() == ResourceTypeEnum.VL)
				.map(x -> mapper.map(x, AffectedVirtualLink.class))
				.forEach(resourceChanged::addAffectedVirtualLinksItem);
		resultDb.getTasks().stream()
				.filter(x -> x.getType() == ResourceTypeEnum.STORAGE)
				.map(x -> mapper.map(x, AffectedVirtualStorage.class))
				.forEach(resourceChanged::addAffectedVirtualStoragesItem);
		resultDb.getTasks().stream()
				.filter(x -> x.getType() == ResourceTypeEnum.COMPUTE)
				.map(x -> mapper.map(x, AffectedVnfc.class))
				.forEach(resourceChanged::addAffectedVnfcsItem);
		entity.setResourceChanges(resourceChanged);
		entity.setLinks(makeLink(entity));
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId, final String version) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, CHANGES) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId, final String version) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, ROLLBACK) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	private static VnfLcmOpOccLinks makeLink(@NotNull final VnfLcmOpOcc vnfLcmOpOcc) {
		@NotNull
		final String id = vnfLcmOpOcc.getId();
		final VnfLcmOpOccLinks link = new VnfLcmOpOccLinks();
		final Link cancel = new Link();
		cancel.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdCancelPost(id, "")).withSelfRel().getHref());
		link.setCancel(cancel);

		final Link fail = new Link();
		fail.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdFailPost(id, "")).withSelfRel().getHref());
		link.setFail(fail);

		// XXX We can't have this grant link directly.
		// grant.setHref(linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(vnfLcmOpOcc.getGrantId(),
		// "")).withSelfRel().getHref());

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdRetryPost(id, "")).withSelfRel().getHref());
		link.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(id, "")).withSelfRel().getHref());
		link.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref());
		link.setSelf(self);

		final Link vnfInstance = new Link();
		vnfInstance.setHref(linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdGet(vnfLcmOpOcc.getId())).withSelfRel().getHref());
		link.setVnfInstance(vnfInstance);

		return link;
	}

	public static String getSelfLink(final String id) {
		return linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref();
	}

}
