package com.ubiqube.etsi.mano.service.rest;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class NfvoRest extends AbstractRest {
	private final String url;
	private final MultiValueMap<String, String> auth = new HttpHeaders();

	public NfvoRest(final Configuration _conf) {
		url = _conf.build("nfvo.url").notNull().build();
		final String user = _conf.get("nfvo.user");
		if (null != user) {
			final String password = _conf.build("nfvo.password").withDefault("").build();
			final String toEncode = user + ':' + password;
			auth.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(toEncode.getBytes()));
		}
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
