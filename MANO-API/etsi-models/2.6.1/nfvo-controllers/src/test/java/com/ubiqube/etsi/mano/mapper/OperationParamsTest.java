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
package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.test.UUIDManufacturer;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OperationParamsTest {

	private final DefaultMapperFactory mapperFactory;
	private final OrikaConfigurationNfvo261 orikaMapperFactoryConfigurer;
	private final PodamFactoryImpl podam;

	public OperationParamsTest() {
		System.setProperty(OrikaSystemProperties.COMPILER_STRATEGY, EclipseJdtCompilerStrategy.class.getName());
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES, "true");
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES_TO_PATH, "/tmp/orika-test");
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaMapperFactoryConfigurer = new OrikaConfigurationNfvo261();
		orikaMapperFactoryConfigurer.configure(mapperFactory);
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testName() throws Exception {
		final VnfLcmOpOcc vp = podam.manufacturePojo(VnfLcmOpOcc.class);
		final InstantiateVnfRequest operationParams = podam.manufacturePojo(InstantiateVnfRequest.class);
		vp.setOperationParams(operationParams);
		final VnfBlueprint o = mapperFactory.getMapperFacade().map(vp, VnfBlueprint.class);
		assertNotNull(o);
	}

	@Test
	void testNameRev() throws Exception {
		final BlueprintParameters p = new BlueprintParameters();
		p.setFlavourId("flavour");
		final VnfBlueprint vp = new VnfBlueprint();
		vp.setParameters(p);
		final VnfLcmOpOcc o = mapperFactory.getMapperFacade().map(vp, VnfLcmOpOcc.class);
		assertNotNull(o);
	}

	@Test
	void testJackson() throws StreamReadException, DatabindException, IOException {
		final ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		final InputStream src = this.getClass().getResourceAsStream("/vnflcmopocc.json");
		final Object res = om.readValue(src, VnfLcmOpOcc.class);
	}
}
