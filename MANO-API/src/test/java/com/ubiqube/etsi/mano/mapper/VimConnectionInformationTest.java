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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.VimConnectionInfo;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VimConnectionInformationTest {
	private final DefaultMapperFactory mapperFactory;

	private final PodamFactoryImpl podam;

	public VimConnectionInformationTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testJson2Orm() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VimConnectionInfo avcDb = podam.manufacturePojo(VimConnectionInfo.class);
		final VimConnectionInformation avc = mapper.map(avcDb, VimConnectionInformation.class);
		assertEquals(avcDb.getId(), avc.getVimId());
	}

	@Test
	void testListJson2ListOrm() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final List<VimConnectionInfo> vims = new ArrayList<>();
		vims.add(podam.manufacturePojo(VimConnectionInfo.class));
		vims.add(podam.manufacturePojo(VimConnectionInfo.class));
		vims.add(podam.manufacturePojo(VimConnectionInfo.class));
		vims.add(podam.manufacturePojo(VimConnectionInfo.class));
		final List<VimConnectionInformation> avc = mapper.mapAsList(vims, VimConnectionInformation.class);
		for (int i = 0; i < vims.size(); i++) {
			assertEquals(vims.get(i).getId(), avc.get(i).getVimId());
		}
	}
}
