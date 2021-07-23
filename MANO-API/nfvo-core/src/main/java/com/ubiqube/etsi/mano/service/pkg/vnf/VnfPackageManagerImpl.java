/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.pkg.vnf;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPackageManagerImpl implements VnfPackageManager {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageManagerImpl.class);

	private final List<PackageDescriptor<VnfPackageReader>> providers;

	public VnfPackageManagerImpl(final List<PackageDescriptor<VnfPackageReader>> _providers) {
		providers = _providers;
	}

	@Override
	public PackageDescriptor<VnfPackageReader> getProviderFor(final byte[] data) {

		for (final PackageDescriptor<VnfPackageReader> provider : providers) {
			LOG.info("Testing {} for package support.", provider.getProviderName());
			if (provider.isProcessable(data)) {
				LOG.info("Using {} for package.", provider.getProviderName());
				return provider;
			}
		}
		LOG.info("No package support, using default.");
		return new DefaultVnfPackageProvider();
	}

}
