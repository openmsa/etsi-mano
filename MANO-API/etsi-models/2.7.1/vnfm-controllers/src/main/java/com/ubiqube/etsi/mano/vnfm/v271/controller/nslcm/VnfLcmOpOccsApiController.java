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
package com.ubiqube.etsi.mano.vnfm.v271.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.nslcm.VnfLcmController;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.AffectedVnfc;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfLcmOpOccResourceChanges;
import com.ubiqube.etsi.mano.nfvo.v271.model.Link;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;

import ma.glasnost.orika.MapperFacade;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:14:43.989+01:00")

@Controller
public class VnfLcmOpOccsApiController implements VnfLcmOpOccsApi {
	public static final Set<String> VNFLCM_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	public static final String VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS = "vnfConfigurableProperties,vimConnectionInfo,instantiatedVnfInfo,metadata,extensions";

	private final MapperFacade mapper;

	private final VnfLcmController vnfLcmController;

	private final ManoSearchResponseService searchService;

	public VnfLcmOpOccsApiController(final MapperFacade mapper, final VnfLcmController vnfLcmController, final ManoSearchResponseService _searchService) {
		super();
		this.mapper = mapper;
		this.vnfLcmController = vnfLcmController;
		searchService = _searchService;
	}

	@Override
	public ResponseEntity<String> vnfLcmOpOccsGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String exclude, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		final List<VnfBlueprint> result = vnfLcmController.vnfLcmOpOccsGet(filter);
		final Consumer<VnfLcmOpOcc> setLink = x -> x.setLinks(makeLink(x));
		return searchService.search(fields, exclude, VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFLCM_SEARCH_MANDATORY_FIELDS, result, VnfLcmOpOcc.class, setLink);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(final String vnfLcmOpOccId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdFailPost(final String vnfLcmOpOccId) {
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
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId) {
		throw new GenericException("TODO");
	}

	private static VnfLcmOpOccLinks makeLink(@NotNull final VnfLcmOpOcc vnfLcmOpOcc) {
		@NotNull
		final String id = vnfLcmOpOcc.getId();
		final VnfLcmOpOccLinks link = new VnfLcmOpOccLinks();
		final Link cancel = new Link();
		cancel.setHref(linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdCancelPost(id)).withSelfRel().getHref());
		link.setCancel(cancel);

		final Link fail = new Link();
		fail.setHref(linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdFailPost(id)).withSelfRel().getHref());
		link.setFail(fail);

		// XXX We can't have this grant link directly.
		// grant.setHref(linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(vnfLcmOpOcc.getGrantId(),
		// "")).withSelfRel().getHref());

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		link.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		link.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref());
		link.setSelf(self);

		final Link vnfInstance = new Link();
		vnfInstance.setHref(linkTo(methodOn(VnfInstancesApi.class).vnfInstancesVnfInstanceIdGet(vnfLcmOpOcc.getId())).withSelfRel().getHref());
		link.setVnfInstance(vnfInstance);

		return link;
	}

	public static String getSelfLink(final String id) {
		return linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref();
	}
}
