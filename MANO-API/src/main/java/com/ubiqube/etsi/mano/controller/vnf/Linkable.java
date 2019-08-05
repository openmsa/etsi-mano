package com.ubiqube.etsi.mano.controller.vnf;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinks;

public interface Linkable {

	VnfPackagesVnfPkgInfoLinks getVnfLinks(@Nonnull String vnfPkgId);
}
