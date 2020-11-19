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

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Http403EntryPoint http403EntryPoint;

	public WebSecurityConfig(final Http403EntryPoint _http403EntryPoint) {
		super();
		http403EntryPoint = _http403EntryPoint;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder _auth) throws Exception {
		final PassthroughUserProvider passthroughUserProvider = new PassthroughUserProvider();
		_auth.eraseCredentials(false);
		_auth.authenticationProvider(passthroughUserProvider);
	}

	/**
	 * All request must be authenticated, No login page.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/openApi*").permitAll()
				.antMatchers("/download/**").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic().authenticationEntryPoint(http403EntryPoint)
				.and().csrf().disable();
	}
}
