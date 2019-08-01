package com.ubiqube.etsi.mano.controller.nslcm.sol002;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;

public class Sol002LcmLinkable implements LcmLinkable {

	@Override
	public VnfInstanceLinks getLinks(String id) {
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdScalePost(id)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdOperatePost(id)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdChangeExtConnPost(id)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(VnfLcmSol002Api.class).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		return LcmFactory.createVnfInstancesLink(hrefSelf, hrefChangeExtConn, hrefChangeFlavor, hrefHeal, hrefIndicators, hrefInstanciate, hrefOperate, hrefScale, hrefScaleToLevel);
	}

}
