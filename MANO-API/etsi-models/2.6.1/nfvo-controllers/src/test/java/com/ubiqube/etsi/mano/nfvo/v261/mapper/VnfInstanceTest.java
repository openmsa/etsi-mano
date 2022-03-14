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
package com.ubiqube.etsi.mano.nfvo.v261.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;

public class VnfInstanceTest {
	private final DefaultMapperFactory mapperFactory;
	private final OrikaConfigurationNfvo261 orikaMapperFactoryConfigurer;

	public VnfInstanceTest() {
		System.setProperty(OrikaSystemProperties.COMPILER_STRATEGY, EclipseJdtCompilerStrategy.class.getName());
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES, "true");
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES_TO_PATH, "/tmp/orika-test");
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaMapperFactoryConfigurer = new OrikaConfigurationNfvo261();
		orikaMapperFactoryConfigurer.configure(mapperFactory);
	}

	@Test
	void testName() throws Exception {
		try (InputStream mappting = Thread.currentThread().getContextClassLoader().getResourceAsStream("vnfinstance.json")) {
			final ObjectMapper mapper = new ObjectMapper();
			final VnfInstance obj = mapper.readValue(mappting, VnfInstance.class);
			final MapperFacade orika = mapperFactory.getMapperFacade();
			final com.ubiqube.etsi.mano.dao.mano.VnfInstance db = orika.map(obj, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class);
			assertNotNull(db.getInstantiatedVnfInfo());
			final BlueprintParameters bp = db.getInstantiatedVnfInfo();
			assertNotNull(bp.getExtVirtualLinkInfo());
			final Set<ExtVirtualLinkDataEntity> exVl = bp.getExtVirtualLinkInfo();
			exVl.forEach(x -> {
				assertNotNull(x.getResourceId());
				assertNotNull(x.getVimConnectionId());
			});
		}
	}
}
