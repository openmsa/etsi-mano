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
package com.ubiqube.etsi.mano.service.pkg.tosca.ns;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ToscaNsRegistryHandler implements PackageDescriptor<NsPackageProvider> {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaNsRegistryHandler.class);

	@Override
	public boolean isProcessable(final InputStream data) {
		// P K x03 x04
		try {
			if (data.read() != 'P' || data.read() != 'K') {
				return false;
			}
		} catch (final IOException e) {
			LOG.trace("", e);
			return false;
		}
		return true;
	}

	@Override
	public String getProviderName() {
		return "TOSCA-NS";
	}

	@Override
	public NsPackageProvider getNewReaderInstance(final InputStream data) {
		return new ToscaNsPackageProvider(data);
	}

}
