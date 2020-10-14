package com.ubiqube.etsi.mano.service.pkg.tosca;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.pkg.PackageProvider;
import com.ubiqube.etsi.mano.service.pkg.RegistryHandler;

@Service
public class ToscaRegistry implements RegistryHandler {

	@Override
	public boolean isProcessable(final byte[] data) {
		// P K x03 x04
		return ((data.length > 10) && ((data[0] == 'P') && (data[1] == 'K')));
	}

	@Override
	public String getName() {
		return "Ubiqube Tosca parser";
	}

	@Override
	public PackageProvider getNewInstance(final byte[] data) {
		return new ToscaPackageProvider(data);
	}

}
