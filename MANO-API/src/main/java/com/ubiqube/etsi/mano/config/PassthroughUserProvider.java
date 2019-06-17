package com.ubiqube.etsi.mano.config;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class PassthroughUserProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger LOG = LoggerFactory.getLogger(PassthroughUserProvider.class);

	@Override
	protected void additionalAuthenticationChecks(UserDetails _userDetails, UsernamePasswordAuthenticationToken _authentication) throws AuthenticationException {
		LOG.debug("Additional check called.");
	}

	@Override
	protected UserDetails retrieveUser(String _username, UsernamePasswordAuthenticationToken _authentication) throws AuthenticationException {
		LOG.debug("retreiving user: {}", _username);
		final Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		return new User(_username, _authentication.getCredentials().toString(), authorities);
	}

}
