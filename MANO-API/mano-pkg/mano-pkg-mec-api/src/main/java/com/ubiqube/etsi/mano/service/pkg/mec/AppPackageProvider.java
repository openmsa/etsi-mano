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
package com.ubiqube.etsi.mano.service.pkg.mec;

import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mec.dto.AppPkgDto;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppExternalCpd;
import com.ubiqube.etsi.mano.dao.mec.pkg.DNSRuleDescriptor;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDescriptor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface AppPackageProvider {

	AppPkgDto getMea();

	Set<DNSRuleDescriptor> getDnsRuleDescriptors(final Map<String, String> parameters);

	Set<ServiceDependency> getOptionalServiceDependency(final Map<String, String> parameters);

	Set<ServiceDependency> getRequiredServiceDependency(final Map<String, String> parameters);

	Set<ServiceDescriptor> getServiceProduced(final Map<String, String> parameters);

	Set<VnfVl> getVl(Map<String, String> parameters);

	Set<AppExternalCpd> getExtCp(Map<String, String> parameters);

}
