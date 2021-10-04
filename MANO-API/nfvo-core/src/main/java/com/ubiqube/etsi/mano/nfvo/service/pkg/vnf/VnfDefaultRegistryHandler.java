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
package com.ubiqube.etsi.mano.nfvo.service.pkg.vnf;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageReader;
import com.ubiqube.etsi.mano.service.pkg.wfe.ExecutionGraph;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfDefaultRegistryHandler implements PackageDescriptor<VnfPackageReader> {

	@Override
	public boolean isProcessable(final byte[] data) {
		return false;
	}

	@Override
	public String getProviderName() {
		return "UBI-DEFAULT";
	}

	@Override
	public VnfPackageReader getNewReaderInstance(final byte[] data) {
		return new DefaultVnfPackageReader();
	}

	@Override
	public ExecutionGraph getBlueprint() {
		// TODO Auto-generated method stub
		return null;
	}

}
