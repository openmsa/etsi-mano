package com.ubiqube.etsi.mano.service.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;


@Service
public class NfvoRest extends AbstractRest {

	@Value("${nfvo.url}")
	private String url;

	@PostConstruct
	private void checkConfig() {
		Assert.notNull(url, "nfvo.url is not declared in property file.");
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
