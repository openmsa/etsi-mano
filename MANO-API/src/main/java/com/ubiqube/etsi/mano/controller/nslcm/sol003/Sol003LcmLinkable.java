package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;

public class Sol003LcmLinkable implements LcmLinkable {

	@Override
	public VnfInstanceLinks getLinks(final String id) {
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdScalePost(id)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdOperatePost(id, null)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdChangeExtConnPost(id, null)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		final String hrefTerminate = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdTerminatePost(id, null)).withSelfRel().getHref();
		return LcmFactory.createVnfInstancesLink(hrefSelf, hrefChangeExtConn, hrefChangeFlavor, hrefHeal, hrefIndicators, hrefInstanciate, hrefOperate, hrefScale, hrefScaleToLevel, hrefTerminate);
	}

}
