package com.ubiqube.etsi.mano.service;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.event.PackageManager;
import com.ubiqube.etsi.mano.service.event.PackageProvider;

@Service
public class NullPackageProvider implements PackageManager {

	@Override
	public PackageProvider getProviderFor(final byte[] data) {
		return null;
	}

}
