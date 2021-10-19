package com.ubiqube.etsi.mano.vnfm.v261;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequestLinks;
import com.ubiqube.etsi.mano.service.VnfmFactory;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003.VnfLcm261Sol003Api;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003.VnfLcm261Sol003Controller;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmFactory261Sol003 implements VnfmFactory {
	@Override
	public void makeGrantRequestLink(final GrantRequest manoGrant) {
		final GrantRequestLinks links = new GrantRequestLinks();
		Link link = new Link();
		link.setHref(VnfLcm261Sol003Controller.getSelfLink(manoGrant.getVnfInstanceId()));
		links.setVnfInstance(link);

		link = new Link();
		link.setHref(linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdGet(manoGrant.getVnfLcmOpOccId())).withSelfRel().getHref());
		links.setVnfLcmOpOcc(link);
	}

}
