package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf.ChangeResultEnum;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf.ChangeTypeEnum;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class NsLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;

	public NsLcmOpOccsTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
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
