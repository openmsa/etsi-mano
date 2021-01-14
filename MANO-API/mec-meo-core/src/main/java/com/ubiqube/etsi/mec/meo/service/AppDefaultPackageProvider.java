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
package com.ubiqube.etsi.mec.meo.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.dto.AppPkgDto;
import com.ubiqube.etsi.mano.dao.mec.pkg.DNSRuleDescriptor;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDescriptor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppDefaultPackageProvider implements AppPackageProvider {

	@Override
	public AppPkgDto getMea() {
		return new AppPkgDto();
	}

	@Override
	public Set<DNSRuleDescriptor> getDnsRuleDescriptors(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<ServiceDependency> getOptionalServiceDependency(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<ServiceDependency> getRequiredServiceDependency(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<ServiceDescriptor> getServiceDescriptors(final Map<String, String> parameters) {
		return new HashSet<>();
	}

}
