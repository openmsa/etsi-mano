package com.ubiqube.etsi.mano.service.pkg;

import org.springframework.stereotype.Service;

@Service
public class NullRegistry implements RegistryHandler {

	@Override
	public boolean isProcessable(final byte[] data) {
		return false;
	}

	@Override
	public String getName() {
		return "Ubiqube default package provder.";
	}

	@Override
	public PackageProvider getNewInstance(final byte[] data) {
		return new DefaultPackageProvider();
	}

}
