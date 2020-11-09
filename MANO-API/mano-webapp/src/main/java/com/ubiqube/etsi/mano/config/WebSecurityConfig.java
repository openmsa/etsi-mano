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
