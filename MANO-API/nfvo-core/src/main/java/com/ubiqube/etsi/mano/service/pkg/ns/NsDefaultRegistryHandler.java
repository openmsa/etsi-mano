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
package com.ubiqube.etsi.mano.service.pkg.ns;

import com.ubiqube.etsi.mano.service.pkg.RegistryHandler;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsDefaultRegistryHandler implements RegistryHandler<NsPackageProvider> {

	@Override
	public boolean isProcessable(final byte[] data) {
		return false;
	}

	@Override
	public String getName() {
		return "NSD default package processor.";
	}

	@Override
	public NsPackageProvider getNewInstance(final byte[] data) {
		return new DefaultNsPackageProvider();
	}

}
