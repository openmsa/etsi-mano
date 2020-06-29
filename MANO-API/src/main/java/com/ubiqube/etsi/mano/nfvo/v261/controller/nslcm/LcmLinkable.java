package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;

public interface LcmLinkable {

	VnfInstanceLinks getLinks(@Nonnull String id);

}
