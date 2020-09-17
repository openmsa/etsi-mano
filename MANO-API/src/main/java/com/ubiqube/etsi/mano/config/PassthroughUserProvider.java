package com.ubiqube.etsi.mano.config;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Valid any user. Because later we will use the current user against the MSA
 * REST API.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PassthroughUserProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger LOG = LoggerFactory.getLogger(PassthroughUserProvider.class);

	@Override
	protected UserDetails retrieveUser(final String _username, final UsernamePasswordAuthenticationToken _authentication) {
		LOG.trace("retreiving user: {}", _username);
		final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
		return new User(_username, _authentication.getCredentials().toString(), authorities);
	}

}
