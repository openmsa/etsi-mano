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
package com.ubiqube.etsi.mano.filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.mapper.OrikaFilterMapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class OrikaFilterMapperTest {

	private final PodamFactoryImpl podam;

	public OrikaFilterMapperTest() {
		podam = new PodamFactoryImpl();
	}

	@Test
	void testName() throws Exception {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter("filterConverter", new OrikaFilterMapper());
		mapperFactory.classMap(PkgmSubscriptionRequest.class, Subscription.class)
				.fieldMap("filter", "filters").converter("filterConverter").add()
				.field("authentication.paramsBasic", "authentificationInformations.authParamBasic")
				.field("authentication.paramsOauth2ClientCredentials", "authentificationInformations.authParamOath2")
				.field("authentication.authType[0]", "authentificationInformations.authType")
				.byDefault()
				.register();
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final PkgmSubscriptionRequest sourceObject = podam.manufacturePojo(PkgmSubscriptionRequest.class);
		final Subscription res = mapper.map(sourceObject, Subscription.class);

		final PkgmSubscriptionRequest dest = mapper.map(res, PkgmSubscriptionRequest.class);
		assertNotNull(dest);
	}
}
