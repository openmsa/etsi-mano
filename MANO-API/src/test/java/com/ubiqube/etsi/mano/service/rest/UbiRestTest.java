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
package com.ubiqube.etsi.mano.service.rest;

import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.service.Configuration;

public class UbiRestTest {
	@Test
	void testMsaV17() throws Exception {
		final Configuration _conf = new MsaConf("17.0", "10.31.1.247");
		final String url = _conf.get("msa.rest-api.url");
		final UbiRest ubiRest = new UbiRest(_conf);
		final URI uri = UriComponentsBuilder.fromHttpUrl(url).pathSegment("orchestration/v1/max-pool-number").build().toUri();
		final Object o = ubiRest.get(uri, Object.class);
		assertNotNull(o);
	}

	@Test
	void testMsaV2() throws Exception {
		final Configuration _conf = new MsaConf("2.0", "10.31.1.127");
		final String url = _conf.get("msa.rest-api.url");
		final UbiRest ubiRest = new UbiRest(_conf);
		final URI uri = UriComponentsBuilder.fromHttpUrl(url).pathSegment("orchestration/v1/max-pool-number").build().toUri();
		final Object o = ubiRest.get(uri, Object.class);
		assertNotNull(o);
	}
}
