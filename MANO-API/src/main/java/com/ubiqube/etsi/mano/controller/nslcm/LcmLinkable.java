package com.ubiqube.etsi.mano.controller.nslcm;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInstanceLinks;

public interface LcmLinkable {

	VnfInstanceLinks getLinks(@Nonnull String id);

}
