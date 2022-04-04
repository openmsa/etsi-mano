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
package com.ubiqube.etsi.mano.service.pkg.tosca.mec;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.dto.AppPkgDto;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppExternalCpd;
import com.ubiqube.etsi.mano.dao.mec.pkg.DNSRuleDescriptor;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDescriptor;
import com.ubiqube.etsi.mano.service.pkg.mec.AppPackageProvider;
import com.ubiqube.etsi.mano.service.pkg.tosca.AbstractPackageReader;

import ma.glasnost.orika.MapperFactory;
import tosca.capabilities.nfv.VirtualCompute;
import tosca.nodes.mec.CP;
import tosca.nodes.mec.MEA;
import tosca.nodes.mec.VL;
import tosca.nodes.mec.vl.ELAN;
import tosca.nodes.mec.vl.ELine;
import tosca.nodes.mec.vl.ETree;
import tosca.policies.mec.AppServiceOptional;
import tosca.policies.mec.AppServiceProduced;
import tosca.policies.mec.AppServiceRequired;
import tosca.policies.mec.DnsRuleDescriptor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppToscaProvider extends AbstractPackageReader implements AppPackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(AppToscaProvider.class);

	public AppToscaProvider(final InputStream data) {
		super(data);
	}

	@Override
	protected void additionalMapping(final MapperFactory mapperFactory) {
		mapperFactory.classMap(MEA.class, AppPkgDto.class)
				.field("appdId", "appDId")
				.field("appdVersion", "appDVersion")
				.field("appLatency", "appLatency.maxLatency")
				.field("appSoftVersion", "appSoftwareVersion")
				.field("virtualCompute", "virtualComputeDescriptor")
				.field("optionalFeatureDependencies", "appFeatureOptional")
				.field("requiredFeatureDependencies", "appFeatureRequired")
				.field("virtualComputeDescriptor.swImageData", "virtualComputeDescriptor.softwareImage")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualCompute.class, VnfCompute.class)
				.field("virtualCpu.numVirtualCpu", "numVcpu")
				.field("virtualCpu.cpuArchitecture", "cpuArchitecture")
				.field("virtualMemory.virtualMemSize", "virtualMemorySize")
				.field("virtualLocalStorage[0].sizeOfStorage", "diskSize")
				.byDefault()
				.register();
	}

	@Override
	public AppPkgDto getMea() {
		final List<AppPkgDto> meas = getListOf(MEA.class, AppPkgDto.class, new HashMap<String, String>());
		if (meas.isEmpty()) {
			LOG.warn("No MEA node found in the package.");
			return new AppPkgDto();
		}
		return meas.get(0);
	}

	@Override
	public Set<DNSRuleDescriptor> getDnsRuleDescriptors(final Map<String, String> parameters) {
		return getSetOf(DnsRuleDescriptor.class, DNSRuleDescriptor.class, parameters);
	}

	@Override
	public Set<ServiceDependency> getOptionalServiceDependency(final Map<String, String> parameters) {
		return getSetOf(AppServiceOptional.class, ServiceDependency.class, parameters);
	}

	@Override
	public Set<ServiceDependency> getRequiredServiceDependency(final Map<String, String> parameters) {
		return getSetOf(AppServiceRequired.class, ServiceDependency.class, parameters);
	}

	@Override
	public Set<ServiceDescriptor> getServiceProduced(final Map<String, String> parameters) {
		return getSetOf(AppServiceProduced.class, ServiceDescriptor.class, parameters);
	}

	@Override
	public Set<VnfVl> getVl(final Map<String, String> parameters) {
		return getSetOf(VnfVl.class, parameters, VL.class, ELine.class, ELAN.class, ETree.class);
	}

	@Override
	public Set<AppExternalCpd> getExtCp(final Map<String, String> parameters) {
		final Set<VnfExtCp> tmp = getSetOf(CP.class, VnfExtCp.class, parameters);
		final AppExternalCpd appextCp = new AppExternalCpd();
		appextCp.setExtCps(tmp);
		return Collections.singleton(appextCp);
	}

}
