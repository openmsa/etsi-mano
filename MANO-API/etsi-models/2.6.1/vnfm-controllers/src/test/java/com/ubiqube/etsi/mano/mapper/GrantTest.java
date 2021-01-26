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

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class GrantTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public GrantTest() {
		final OrikaMapperVnfm261 orikaConfiguration = new OrikaMapperVnfm261();
		final OrikaMapperVnfm261 orikaVnfm = new OrikaMapperVnfm261();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
		orikaVnfm.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testName() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		// final GrantRequest grantRequest = jsonMapper.readValue(bytes,
		// GrantRequest.class);
		final GrantRequest grantRequest = podam.manufacturePojo(GrantRequest.class);
		final GrantResponse grants = mapper.map(grantRequest, GrantResponse.class);
		System.out.println("" + grants);
		assertEquals(grantRequest.getAdditionalParams().size(), grants.getAdditionalParams().size());
		assertEquals(grantRequest.getAddResources().size(), grants.getAddResources().size());
		// assertEquals(grantRequest.isAutomaticInvocation(),
		// grants.isAutomaticInvocation());
		assertEquals(grantRequest.getFlavourId(), grants.getFlavourId());
		assertEquals(grantRequest.getOperation().toString(), grants.getOperation().toString());
		assertEquals(grantRequest.getRemoveResources().size(), grants.getRemoveResources().size());
		assertEquals(grantRequest.getTempResources().size(), grants.getTempResources().size());
		assertEquals(grantRequest.getUpdateResources().size(), grants.getUpdateResources().size());
		assertEquals(grantRequest.getVnfdId(), grants.getVnfdId());
		assertEquals(grantRequest.getVnfInstanceId(), grants.getVnfInstanceId());
		assertEquals(grantRequest.getVnfLcmOpOccId(), grants.getVnfLcmOpOccId());
	}

	@Test
	void testGrantResponseToGrantResponse() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final GrantResponse gr = new GrantResponse();
		gr.setVimAssets(podam.manufacturePojo(GrantVimAssetsEntity.class));
		final ExtManagedVirtualLinkDataEntity extVl = new ExtManagedVirtualLinkDataEntity();
		extVl.setId(UUID.randomUUID());
		extVl.setResourceId("resId");
		extVl.setResourceProviderId("provId");
		extVl.setVimConnectionId("vimId");
		extVl.setVnfVirtualLinkDescId("name");
		gr.addExtManagedVl(extVl);
		final Grant resp = mapper.map(gr, Grant.class);
		System.out.println("" + resp);
		final List<ExtManagedVirtualLinkData> respExtVls = resp.getExtManagedVirtualLinks();
		assertNotNull(respExtVls);
		assertEquals(1, respExtVls.size());
		final ExtManagedVirtualLinkData respExtVl = respExtVls.get(0);
		assertEquals("resId", respExtVl.getResourceId());
		assertEquals("provId", respExtVl.getResourceProviderId());
		assertEquals("vimId", respExtVl.getVimId());
		assertEquals("name", respExtVl.getVmfVirtualLinkDescId());
	}

	@Test
	void testGratRequest() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final GrantRequest reqJson = podam.manufacturePojo(GrantRequest.class);
		final VnfGrantsRequest req = mapper.map(reqJson, VnfGrantsRequest.class);
		assertNotNull(req.getVimConstraints());
	}
}
