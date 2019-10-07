package com.ubiqube.etsi.mano.service.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class NfvoRest extends AbstractRest {
	private final String url;

	public NfvoRest(final Configuration _conf) {
		url = _conf.build("nfvo.url").notNull().build();
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	UserPass getAutorization() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final UserDetails user = (UserDetails) authentication.getPrincipal();
		return new UserPass(user.getUsername(), user.getPassword());
	}

}
