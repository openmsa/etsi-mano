package com.ubiqube.etsi.mano.service.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class UbiRest extends AbstractRest {
	private final String url;

	private final MultiValueMap<String, String> auth = new HttpHeaders();

	public UbiRest(final Configuration _conf) {
		url = _conf.build("msa.rest-api.url").notNull().build();
		final String user = _conf.get("msa.rest-api.user");
		if (null != user) {
			final String password = _conf.build("msa.rest-api.password").withDefault("").build();
			auth.add("Authorization", authBasic(user, password));
		}
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	protected MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
