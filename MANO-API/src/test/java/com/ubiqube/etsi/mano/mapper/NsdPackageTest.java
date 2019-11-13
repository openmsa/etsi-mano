package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class NsdPackageTest {
	private final DefaultMapperFactory mapperFactory;

	public NsdPackageTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testMapJsonDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsDescriptorsNsdInfo nsd = TestFactory.createNsDescriptorsNsdInfo();
		final NsdPackage nsdDao = mapper.map(nsd, NsdPackage.class);

		final Set<NsdPackage> infoId = nsdDao.getNestedNsdInfoIds();
		assertEquals(2, infoId.size());
		final NsdPackage nsdP[] = infoId.toArray(new NsdPackage[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", nsdP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", nsdP[1].getId().toString());

		final FailureDetails nsdDaofailure = nsdDao.getOnboardingFailureDetails();
		assertEquals("detail", nsdDaofailure.getDetail());

		final Set<PnfDescriptor> pnfs = nsdDao.getPnfdInfoIds();
		assertEquals(2, pnfs.size());
		final PnfDescriptor pnfP[] = pnfs.toArray(new PnfDescriptor[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", pnfP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", pnfP[1].getId().toString());

		final Set<VnfPackage> vnf = nsdDao.getVnfPkgIds();
		assertEquals(2, vnf.size());
		final VnfPackage vnfP[] = vnf.toArray(new VnfPackage[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", vnfP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", vnfP[1].getId().toString());

		// Check enum
		assertEquals(OperationalStateEnum.ENABLED, nsdDao.getNsdOperationalState());
		assertEquals(UsageStateEnum.IN_USE, nsdDao.getNsdUsageState());
		assertEquals(NsdOnboardingStateEnum.ONBOARDED, nsdDao.getNsdOnboardingState());
	}

	@Test
	void testMapDaoJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsdPackage nsd = TestFactory.createNsdPackage();
		final NsDescriptorsNsdInfo nsdInfo = mapper.map(nsd, NsDescriptorsNsdInfo.class);
		assertEquals(NsdOperationalStateEnum.ENABLED, nsdInfo.getNsdOperationalState());
		assertEquals(NsdUsageStateEnum.IN_USE, nsdInfo.getNsdUsageState());
		assertEquals(NsdOnboardingStateEnum.ONBOARDED, nsdInfo.getNsdOnboardingState());
	}
}
