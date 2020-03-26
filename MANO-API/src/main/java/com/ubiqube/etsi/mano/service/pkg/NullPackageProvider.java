package com.ubiqube.etsi.mano.service.pkg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NullPackageProvider implements PackageManager {

	private static final Logger LOG = LoggerFactory.getLogger(NullPackageProvider.class);

	private final List<RegistryHandler> providers;

	public NullPackageProvider(final List<RegistryHandler> _providers) {
		providers = _providers;
	}

	@Override
	public PackageProvider getProviderFor(final byte[] data) {
		for (final RegistryHandler provider : providers) {
			LOG.info("Testing {} for package support.", provider.getName());
			if (provider.isProcessable(data)) {
				LOG.info("Using {} for package.", provider.getName());
				return provider.getNewInstance(data);
			}
		}
		LOG.info("No package support, using default.");
		return new DefaultPackageProvider();
	}

}
