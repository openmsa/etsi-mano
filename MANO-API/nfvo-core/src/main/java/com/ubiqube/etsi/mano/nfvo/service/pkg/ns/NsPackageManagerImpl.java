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
package com.ubiqube.etsi.mano.nfvo.service.pkg.ns;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsPackageManagerImpl implements NsPackageManager {

	private static final Logger LOG = LoggerFactory.getLogger(NsPackageManagerImpl.class);

	private final List<PackageDescriptor<NsPackageProvider>> providers;

	public NsPackageManagerImpl(final List<PackageDescriptor<NsPackageProvider>> providers) {
		this.providers = providers;
	}

	@Override
	public PackageDescriptor<NsPackageProvider> getProviderFor(final ManoResource data) {
		for (final PackageDescriptor<NsPackageProvider> provider : providers) {
			LOG.info("Testing {} for package support.", provider.getProviderName());
			try (InputStream is = data.getInputStream()) {
				if (provider.isProcessable(is)) {
					LOG.info("Using {} for package.", provider.getProviderName());
					return provider;
				}
			} catch (final IOException e) {
				throw new GenericException(e);
			}
		}
		LOG.info("No package support, using default.");
		return new DefaultNsPackageDescriptor();
	}

}
