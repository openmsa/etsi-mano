package com.ubiqube.etsi.mano.vnfm.v261.services;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.VnfmGateway;

@Service
public class VnfmGateway261 implements VnfmGateway {

	@Override
	public Class<?> getVnfPackageClass() {
		return VnfPkgInfo.class;
	}

}
