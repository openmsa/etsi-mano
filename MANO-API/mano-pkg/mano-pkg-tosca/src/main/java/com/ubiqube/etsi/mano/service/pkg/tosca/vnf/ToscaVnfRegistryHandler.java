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
package com.ubiqube.etsi.mano.service.pkg.tosca.vnf;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.VirtualFileSystem;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageReader;
import com.ubiqube.etsi.mano.sol004.CsarModeEnum;
import com.ubiqube.etsi.mano.sol004.Sol004Onboarding;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ToscaVnfRegistryHandler implements PackageDescriptor<VnfPackageReader> {

	private final VnfPackageRepository repo;

	public ToscaVnfRegistryHandler(final VnfPackageRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public boolean isProcessable(final ManoResource data) {
		final Sol004PreOnboarding onboarding = new Sol004PreOnboarding(data);
		return onboarding.getMode() != CsarModeEnum.UNKNOWN;
	}

	@Override
	public String getProviderName() {
		return "TOSCA-VNF";
	}

	@Override
	public VnfPackageReader getNewReaderInstance(final InputStream data, final UUID id) {
		return new ToscaVnfPackageReader(data, repo, id);
	}

	@Override
	public VirtualFileSystem getFileSystem(final ManoResource res) {
		final Sol004Onboarding pack = new Sol004Onboarding();
		return pack.getVirtualFileSystem(res);
	}

}
