/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
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
	protected void additionalAuthenticationChecks(UserDetails _userDetails, UsernamePasswordAuthenticationToken _authentication) {
		LOG.debug("Additional check called.");
	}

	@Override
	protected UserDetails retrieveUser(String _username, UsernamePasswordAuthenticationToken _authentication) {
		LOG.debug("retreiving user: {}", _username);
		final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
		return new User(_username, _authentication.getCredentials().toString(), authorities);
	}

}
