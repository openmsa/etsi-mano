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

package com.ubiqube.etsi.mano.config;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	protected void additionalAuthenticationChecks(final UserDetails _userDetails, final UsernamePasswordAuthenticationToken _authentication) {
		LOG.debug("Additional check called.");
	}

	@Override
	protected UserDetails retrieveUser(final String _username, final UsernamePasswordAuthenticationToken _authentication) {
		LOG.debug("retreiving user: {}", _username);
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_NFVO"));
		authorities.add(new SimpleGrantedAuthority("ROLE_VNFM"));
		return new User(_username, _authentication.getCredentials().toString(), authorities);
	}

}
