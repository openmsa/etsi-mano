package com.ubiqube.etsi.mano.service.rest;

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
		ubiRest.get(uri, Object.class);
	}

	@Test
	void testMsaV2() throws Exception {
		final Configuration _conf = new MsaConf("2.0", "10.31.1.127");
		final String url = _conf.get("msa.rest-api.url");
		final UbiRest ubiRest = new UbiRest(_conf);
		final URI uri = UriComponentsBuilder.fromHttpUrl(url).pathSegment("orchestration/v1/max-pool-number").build().toUri();
		ubiRest.get(uri, Object.class);
	}
}
