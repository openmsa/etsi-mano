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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfLcmOpOccsTest() {
		final OrikaMapperVnfm261 orikaConfigurationVnfm = new OrikaMapperVnfm261();
		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaConfigurationVnfm.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfLcmOpOcc lcm = podam.manufacturePojo(VnfLcmOpOcc.class);
		final VnfLcmOpOccs lcmP = mapper.map(lcm, VnfLcmOpOccs.class);
		assertNotNull(lcmP.getVnfInstance());
		assertNotNull(lcmP.getVnfInstance().getId());
		assertEquals(lcm.getVnfInstanceId(), lcmP.getVnfInstance().getId().toString());
		assertEquals(lcm.getCancelMode().toString(), lcmP.getCancelMode().toString());
		assertEquals(lcm.getGrantId(), lcmP.getGrantId());
		assertEquals(lcm.getId(), lcmP.getId().toString());
		assertEquals(lcm.getOperation().toString(), lcmP.getOperation().toString());
		assertEquals(lcm.getOperationState().toString(), lcmP.getOperationState().toString());
		assertNotNull(lcmP.getError());
		assertEquals(lcm.getStartTime(), lcmP.getStartTime());
		assertEquals(lcm.getStateEnteredTime(), lcmP.getStateEnteredTime());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualLinks().size(), lcmP.getResourceChanges().getAffectedVirtualLinks().size());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualStorages().size(), lcmP.getResourceChanges().getAffectedVirtualStorages().size());
		assertEquals(lcm.getResourceChanges().getAffectedVnfcs().size(), lcmP.getResourceChanges().getAffectedVnfcs().size());
	}

	/**
	 * Infinite loop in extCp
	 *
	 * @throws Exception
	 */
	void testDaoToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfLcmOpOccs lcmP = podam.manufacturePojo(VnfLcmOpOccs.class);
		final VnfLcmOpOcc lcm = mapper.map(lcmP, VnfLcmOpOcc.class);
		assertNotNull(lcmP.getVnfInstance());
		assertNotNull(lcmP.getVnfInstance().getId());
		assertEquals(lcm.getVnfInstanceId(), lcmP.getVnfInstance().getId().toString());
		assertEquals(lcm.getCancelMode(), lcmP.getCancelMode());
		assertEquals(lcm.getGrantId(), lcmP.getGrantId());
		assertEquals(lcm.getId(), lcmP.getId().toString());
		assertEquals(lcm.getOperation(), lcmP.getOperation());
		assertEquals(lcm.getOperationState(), lcmP.getOperationState());
		assertNotNull(lcmP.getError());
		assertEquals(lcm.getStartTime(), lcmP.getStartTime());
		assertEquals(lcm.getStateEnteredTime(), lcmP.getStateEnteredTime());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualLinks().size(), lcmP.getResourceChanges().getAffectedVirtualLinks().size());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualStorages().size(), lcmP.getResourceChanges().getAffectedVirtualStorages().size());
		assertEquals(lcm.getResourceChanges().getAffectedVnfcs().size(), lcmP.getResourceChanges().getAffectedVnfcs().size());
	}

}
