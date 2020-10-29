/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.common.v261.LcmFactory;
import com.ubiqube.etsi.mano.common.v261.controller.lcm.LcmLinkable;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;

public class Sol003LcmLinkable implements LcmLinkable {

	@Override
	public VnfInstanceLinks getLinks(final String id) {
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id, null)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdScalePost(id, null)).withSelfRel().getHref();
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
