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

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.dto.NsLcmOpOccs;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.nfvo.v261.factory.TestFactory;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf.ChangeResultEnum;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf.ChangeTypeEnum;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class NsLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;

	public NsLcmOpOccsTest() {
		final OrikaConfigurationNfvo261 orikaConfiguration = new OrikaConfigurationNfvo261();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsLcmOpOcc nsLcmOpOcc = new NsLcmOpOcc();
		nsLcmOpOcc.setError(TestFactory.createProblemDetails());
		nsLcmOpOcc.setStartTime(OffsetDateTime.now());
		nsLcmOpOcc.setStateEnteredTime(OffsetDateTime.now());
		final AffectedVnf affectedVnf = new AffectedVnf();
		affectedVnf.setChangeResult(ChangeResultEnum.COMPLETED);
		affectedVnf.setChangeType(ChangeTypeEnum.CHANGE_EXTERNAL_VNF_CONNECTIVITY);
		affectedVnf.setVnfdId("idid");
		affectedVnf.setVnfInstanceId("1c3e8d3d-5224-4fa9-8a23-0be83b66b9bf");
		affectedVnf.setVnfName("name");
		affectedVnf.setVnfProfileId("profile_id");
		nsLcmOpOcc.getResourceChanges().getAffectedVnfs().add(affectedVnf);

		final NsLcmOpOccs nloo = mapper.map(nsLcmOpOcc, NsLcmOpOccs.class);
		assertNotNull(nloo.getError());
		assertEquals("detail", nloo.getError().getDetail());
		assertNotNull(nloo.getStartTime());
		assertNotNull(nloo.getStateEnteredTime());
		assertEquals("1c3e8d3d-5224-4fa9-8a23-0be83b66b9bf", nloo.getResourceChanges().getAffectedVnfs().iterator().next().getVnfInstance().toString());
	}
}
