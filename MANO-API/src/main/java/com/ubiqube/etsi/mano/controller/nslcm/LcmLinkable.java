package com.ubiqube.etsi.mano.controller.nslcm;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;

public interface LcmLinkable {

	VnfInstanceLinks getLinks(@Nonnull String id);
}
