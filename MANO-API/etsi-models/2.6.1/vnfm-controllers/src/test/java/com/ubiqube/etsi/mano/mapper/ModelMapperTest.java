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

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public class ModelMapperTest {

	@Test
	void testName() throws Exception {
		final ModelMapper modelMapper = new ModelMapper();
		// modelMapper.map(com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, VnfInstance.class);
		final TypeMap<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance, VnfInstance> tm = modelMapper.createTypeMap(com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, VnfInstance.class);
		tm.addMappings(mapper -> {
			mapper.map(src -> src.getInstantiatedVnfInfo().getExtCpInfo(), VnfInstance::setId);
		});
		// modelMapper.addMappings(tm);
		// modelMapper.validate();
		// map(VnfInstance::getAudit);
	}
}
