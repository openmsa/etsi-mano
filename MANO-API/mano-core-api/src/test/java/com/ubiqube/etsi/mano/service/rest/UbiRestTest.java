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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.config.properties.ManoMsaProperties;

@Tag("Remote")
public class UbiRestTest {
	@Test
	static void testMsaV17() throws Exception {
		final ManoMsaProperties msaProperties = new ManoMsaProperties();
		final String url = "";
		msaProperties.setUrl(url);
		final UbiRest ubiRest = new UbiRest(msaProperties);
		final URI uri = UriComponentsBuilder.fromHttpUrl(url).pathSegment("orchestration/v1/max-pool-number").build().toUri();
		final Object o = ubiRest.get(uri, Object.class);
		assertNotNull(o);
	}
}
