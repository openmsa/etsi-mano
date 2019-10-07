package com.ubiqube.etsi.mano.service.rest;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class UbiRest extends AbstractRest {

	private final String url;

	private final MultiValueMap<String, String> httpHeaders = new HttpHeaders();

	public UbiRest(final Configuration _conf) {
		url = _conf.build("msa.rest-api.url").notNull().build();
		final String user = _conf.get("msa.rest-api.user");
		if (null != user) {
			final String password = _conf.build("msa.rest-api.password").withDefault("").build();
			final String toEncode = user + ':' + password;
			httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(toEncode.getBytes()));
		}
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	protected MultiValueMap<String, String> getAutorization() {
		return httpHeaders;
	}

}
