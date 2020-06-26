package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInstanceLinks;

public class Sol002LcmLinkable implements LcmLinkable {

	@Override
	public VnfInstanceLinks getLinks(final String id) {
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdScalePost(id)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdOperatePost(id)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdChangeExtConnPost(id)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		final String hrefTerminate = linkTo(methodOn(VnfLcmSol002.class).vnfInstancesVnfInstanceIdTerminatePost(id, null)).withSelfRel().getHref();
		return LcmFactory.createVnfInstancesLink(hrefSelf, hrefChangeExtConn, hrefChangeFlavor, hrefHeal, hrefIndicators, hrefInstanciate, hrefOperate, hrefScale, hrefScaleToLevel, hrefTerminate);
	}

}
