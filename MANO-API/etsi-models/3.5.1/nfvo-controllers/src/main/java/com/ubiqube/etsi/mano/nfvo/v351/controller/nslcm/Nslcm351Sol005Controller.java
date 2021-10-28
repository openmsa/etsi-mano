package com.ubiqube.etsi.mano.nfvo.v351.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.NsLcmGenericFrontController;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.CancelMode;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.Link;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NsLcmOpOccLinks;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Nslcm351Sol005Controller implements Nslcm351Sol005Api {
	private final NsLcmGenericFrontController nsLcmGenericFrontController;

	public Nslcm351Sol005Controller(final NsLcmGenericFrontController nsLcmGenericFrontController) {
		super();
		this.nsLcmGenericFrontController = nsLcmGenericFrontController;
	}

	@Override
	public ResponseEntity<Void> nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(final String nsLcmOpOccId, @Valid final CancelMode body) {
		return nsLcmGenericFrontController.cancel(nsLcmOpOccId, body.getCancelMode().toString());
	}

	@Override
	public ResponseEntity<NsLcmOpOcc> nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(final String nsLcmOpOccId) {
		return nsLcmGenericFrontController.fail(nsLcmOpOccId, NsLcmOpOcc.class, Nslcm351Sol005Controller::makeLinks);
	}

	public static void makeLinks(@NotNull final NsLcmOpOcc nsLcmOpOccs) {
		@NotNull
		final String id = nsLcmOpOccs.getId().toString();
		final NsLcmOpOccLinks nsLcmOpOccLinks = new NsLcmOpOccLinks();

		final Link _continue = new Link();
		_continue.setHref(linkTo(methodOn(NsLcmOpOccs351Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdContinuePost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setContinue(_continue);

		final Link nsInstance = new Link();
		nsInstance.setHref(linkTo(methodOn(NsInstances351Sol005Api.class).nsInstancesNsInstanceIdGet(nsLcmOpOccs.getNsInstanceId())).withSelfRel().getHref());
		nsLcmOpOccLinks.setNsInstance(nsInstance);

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(NsLcmOpOccs351Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(NsLcmOpOccs351Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsLcmOpOccs351Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdGet(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setSelf(self);
		nsLcmOpOccs.setLinks(nsLcmOpOccLinks);
	}

}
