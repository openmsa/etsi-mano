package com.ubiqube.etsi.mano.vnfm.v331.controller.vnflcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceGenericFrontController;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.AffectedVnfc;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.Link;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfLcmOpOccResourceChanges;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfLcmOpOccs331Sol003Controller implements VnfLcmOpOccs331Sol003Api {

	private final VnfInstanceGenericFrontController frontController;

	private final MapperFacade mapper;

	public VnfLcmOpOccs331Sol003Controller(final VnfInstanceGenericFrontController frontController, final MapperFacade _mapper) {
		super();
		this.frontController = frontController;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<String> vnfLcmOpOccsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return frontController.search(requestParams, VnfLcmOpOcc.class, VnfLcmOpOccs331Sol003Controller::makeLinks);
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
		final VnfBlueprint resultDb = frontController.lcmOpOccFindById(UUID.fromString(vnfLcmOpOccId));
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
		makeLinks(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccRetry(UUID.fromString(vnfLcmOpOccId));
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId) {
		return frontController.lcmOpOccRollback(UUID.fromString(vnfLcmOpOccId));
	}

	private static void makeLinks(final VnfLcmOpOcc vnfLcmOpOcc) {
		final String id = vnfLcmOpOcc.getId();
		final VnfLcmOpOccLinks links = new VnfLcmOpOccLinks();
		final Link cancel = new Link();
		cancel.setHref(linkTo(methodOn(VnfLcmOpOccs331Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdCancelPost(id)).withSelfRel().getHref());
		links.setCancel(cancel);

		final Link fail = new Link();
		fail.setHref(linkTo(methodOn(VnfLcmOpOccs331Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdFailPost(id)).withSelfRel().getHref());
		links.setFail(fail);

		// XXX We can't have this grant link directly, because of classpath on interface.
		// grant.setHref(linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(vnfLcmOpOcc.getGrantId(),"")).withSelfRel().getHref());

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(VnfLcmOpOccs331Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		links.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(VnfLcmOpOccs331Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		links.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmOpOccs331Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref());
		links.setSelf(self);

		final Link vnfInstance = new Link();
		vnfInstance.setHref(linkTo(methodOn(VnfInstances331Sol003Api.class).vnfInstancesVnfInstanceIdGet(vnfLcmOpOcc.getId())).withSelfRel().getHref());
		links.setVnfInstance(vnfInstance);

		vnfLcmOpOcc.setLinks(links);
	}
}
