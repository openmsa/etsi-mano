package com.ubiqube.etsi.mano.nfvo.v351.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceGenericFrontController;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.CreateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.HealNsRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.Link;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NsInstance;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NsInstanceLinks;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ScaleNsRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.TerminateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.UpdateNsRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NsInstances351Sol005Controller implements NsInstances351Sol005Api {
	private final NsInstanceGenericFrontController nsInstanceGenericFrontController;

	public NsInstances351Sol005Controller(final NsInstanceGenericFrontController nsInstanceGenericFrontController) {
		super();
		this.nsInstanceGenericFrontController = nsInstanceGenericFrontController;
	}

	@Override
	public ResponseEntity<String> nsInstancesGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return nsInstanceGenericFrontController.search(requestParams, NsInstance.class, nextpageOpaqueMarker, NsInstances351Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdDelete(final String nsInstanceId) {
		return nsInstanceGenericFrontController.delete(nsInstanceId);
	}

	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdGet(final String nsInstanceId) {
		return nsInstanceGenericFrontController.findById(nsInstanceId, NsInstance.class, NsInstances351Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdHealPost(final String nsInstanceId, @Valid final HealNsRequest body) {
		return nsInstanceGenericFrontController.heal(nsInstanceId, body, NsInstances351Sol005Controller::getNsbLink);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdInstantiatePost(final String nsInstanceId, @Valid final InstantiateNsRequest body) {
		return nsInstanceGenericFrontController.instantiate(nsInstanceId, body, NsInstances351Sol005Controller::getNsbLink);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdScalePost(final String nsInstanceId, @Valid final ScaleNsRequest body) {
		return nsInstanceGenericFrontController.scale(nsInstanceId, body, NsInstances351Sol005Controller::getNsbLink);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdTerminatePost(final String nsInstanceId, @Valid final TerminateNsRequest body) {
		return nsInstanceGenericFrontController.terminate(nsInstanceId, body, NsInstances351Sol005Controller::getNsbLink);
	}

	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdUpdatePost(final String nsInstanceId, @Valid final UpdateNsRequest body) {
		return nsInstanceGenericFrontController.update(nsInstanceId, body, NsInstances351Sol005Controller::getNsbLink);
	}

	@Override
	public ResponseEntity<NsInstance> nsInstancesPost(@Valid final CreateNsRequest body) {
		return nsInstanceGenericFrontController.create(body, NsInstance.class, NsInstances351Sol005Controller::makeLinks, NsInstances351Sol005Controller::getLink);
	}

	private static String getLink(final NsInstance nsBlueprint) {
		return linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdHealPost(nsBlueprint.getId(), null)).withSelfRel().getHref();
	}

	private static String getNsbLink(final NsBlueprint nsBlueprint) {
		return linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdHealPost(nsBlueprint.getId().toString(), null)).withSelfRel().getHref();
	}

	private static void makeLinks(@Nonnull final NsInstance nsdInfo) {
		final String id = nsdInfo.getId();
		final NsInstanceLinks nsInstanceLinks = new NsInstanceLinks();
		final Link heal = new Link();
		heal.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdHealPost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setHeal(heal);

		final Link instantiate = new Link();
		instantiate.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdInstantiatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setInstantiate(instantiate);
		// nsInstanceLinks.setNestedNsInstances(nestedNsInstances);
		final Link scale = new Link();
		scale.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdScalePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setScale(scale);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdGet(id)).withSelfRel().getHref());
		nsInstanceLinks.setSelf(self);

		final Link terminate = new Link();
		terminate.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdTerminatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setTerminate(terminate);

		final Link update = new Link();
		update.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdUpdatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setUpdate(update);
		nsdInfo.setLinks(nsInstanceLinks);
	}

}
