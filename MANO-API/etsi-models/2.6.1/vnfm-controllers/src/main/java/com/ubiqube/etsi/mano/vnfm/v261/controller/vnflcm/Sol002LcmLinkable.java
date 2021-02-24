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

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.common.v261.LcmFactory;
import com.ubiqube.etsi.mano.common.v261.controller.lcm.LcmLinkable;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;

public class Sol002LcmLinkable implements LcmLinkable {

	@Override
	public VnfInstanceLinks getLinks(final String id) {
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdScalePost(id)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdOperatePost(id)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdChangeExtConnPost(id)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		final String hrefTerminate = linkTo(methodOn(VnfLcm261Sol002Api.class).vnfInstancesVnfInstanceIdTerminatePost(id, null)).withSelfRel().getHref();
		return LcmFactory.createVnfInstancesLink(hrefSelf, hrefChangeExtConn, hrefChangeFlavor, hrefHeal, hrefIndicators, hrefInstanciate, hrefOperate, hrefScale, hrefScaleToLevel, hrefTerminate);
	}

}
