package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.model.VimConnectionInfo;

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
