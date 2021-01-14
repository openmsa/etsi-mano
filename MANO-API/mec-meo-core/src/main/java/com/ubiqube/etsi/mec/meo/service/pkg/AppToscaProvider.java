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
package com.ubiqube.etsi.mec.meo.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.dto.AppPkgDto;
import com.ubiqube.etsi.mano.dao.mec.pkg.DNSRuleDescriptor;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDescriptor;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mano.service.pkg.tosca.SizeConverter;
import com.ubiqube.etsi.mano.service.pkg.tosca.TimeConverter;
import com.ubiqube.etsi.mec.meo.service.AppPackageProvider;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.api.ToscaApi;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import tosca.datatypes.mec.DnsRuleDescriptor;
import tosca.nodes.mec.MEA;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppToscaProvider implements AppPackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(AppToscaProvider.class);

	private final ToscaApi toscaApi;
	private final ToscaContext root;
	private final MapperFacade mapper;

	private final ToscaParser toscaParser;

	public AppToscaProvider(final byte[] data) {
		final File tempFile = fetchData(data);
		toscaParser = new ToscaParser(tempFile.getAbsolutePath());
		root = toscaParser.getContext();
		toscaApi = new ToscaApi();

		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new SizeConverter());
		converterFactory.registerConverter(new TimeConverter());
		mapper = mapperFactory.getMapperFacade();
	}

	private static File fetchData(final byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile("tosca", ".zip");
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		return tempFile;
	}

	@Override
	public AppPkgDto getMea() {
		final List<MEA> meas = toscaApi.getObjects(root, new HashMap<String, String>(), MEA.class);
		if (meas.isEmpty()) {
			LOG.warn("No MEA node found in the package.");
			return new AppPkgDto();
		}
		return mapper.map(meas.get(0), AppPkgDto.class);
	}

	@Override
	public Set<DNSRuleDescriptor> getDnsRuleDescriptors(final Map<String, String> parameters) {
		final List<DnsRuleDescriptor> list = toscaApi.getObjects(root, parameters, DnsRuleDescriptor.class);
		LOG.debug("Found {} DnsRuleDescriptors node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, DNSRuleDescriptor.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<ServiceDependency> getOptionalServiceDependency(final Map<String, String> parameters) {
		// TODO: xxx
		final List<DnsRuleDescriptor> list = new ArrayList<>();
		LOG.debug("Found {} Optional dependency node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, ServiceDependency.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<ServiceDependency> getRequiredServiceDependency(final Map<String, String> parameters) {
		final List<tosca.policies.mec.ServiceDependency> list = toscaApi.getObjects(root, parameters, tosca.policies.mec.ServiceDependency.class);
		LOG.debug("Found {} dependency node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, ServiceDependency.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<ServiceDescriptor> getServiceDescriptors(final Map<String, String> parameters) {
		final List<tosca.policies.mec.ServiceDescriptor> list = toscaApi.getObjects(root, parameters, tosca.policies.mec.ServiceDescriptor.class);
		LOG.debug("Found {} Service descriptor node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, ServiceDescriptor.class))
				.collect(Collectors.toSet());
	}

}
