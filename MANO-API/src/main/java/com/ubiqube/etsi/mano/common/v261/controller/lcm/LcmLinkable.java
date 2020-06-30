package com.ubiqube.etsi.mano.common.v261.controller.lcm;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;

public interface LcmLinkable {

	VnfInstanceLinks getLinks(@Nonnull String id);

}
