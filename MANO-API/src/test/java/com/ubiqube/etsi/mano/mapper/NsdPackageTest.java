package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageUsageStateType;

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
		final NsdInfo nsd = TestFactory.createNsDescriptorsNsdInfo();
		final NsdPackage nsdDao = mapper.map(nsd, NsdPackage.class);

		final Set<NsdPackageNsdPackage> infoId = nsdDao.getNestedNsdInfoIds();
		assertEquals(2, infoId.size());
		final NsdPackageNsdPackage nsdP[] = infoId.toArray(new NsdPackageNsdPackage[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", nsdP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", nsdP[1].getId().toString());

		final FailureDetails nsdDaofailure = nsdDao.getOnboardingFailureDetails();
		assertEquals("detail", nsdDaofailure.getDetail());

		final Set<PnfDescriptor> pnfs = nsdDao.getPnfdInfoIds();
		assertEquals(2, pnfs.size());
		final PnfDescriptor pnfP[] = pnfs.toArray(new PnfDescriptor[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", pnfP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", pnfP[1].getId().toString());

		final Set<NsdPackageVnfPackage> vnf = nsdDao.getVnfPkgIds();
		assertEquals(2, vnf.size());
		final NsdPackageVnfPackage vnfP[] = vnf.toArray(new NsdPackageVnfPackage[0]);
		assertEquals("25dca365-ff1b-4204-a9ca-c3745e6d3244", vnfP[0].getId().toString());
		assertEquals("52d993dc-7a50-46da-b30c-e8fb344ef140", vnfP[1].getId().toString());

		// Check enum
		assertEquals(PackageOperationalStateType.ENABLED, nsdDao.getNsdOperationalState());
		assertEquals(PackageUsageStateType.IN_USE, nsdDao.getNsdUsageState());
		assertEquals(NsdOnboardingStateType.ONBOARDED, nsdDao.getNsdOnboardingState());
	}

	@Test
	void testMapDaoJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsdPackage nsd = TestFactory.createNsdPackage();
		final NsdInfo nsdInfo = mapper.map(nsd, NsdInfo.class);
		assertEquals(NsdOperationalStateType.ENABLED, nsdInfo.getNsdOperationalState());
		assertEquals(NsdUsageStateType.IN_USE, nsdInfo.getNsdUsageState());
		assertEquals(NsdOnboardingStateType.ONBOARDED, nsdInfo.getNsdOnboardingState());
	}
}
