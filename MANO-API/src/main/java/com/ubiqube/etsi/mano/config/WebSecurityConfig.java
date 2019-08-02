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

	public WebSecurityConfig(Http403EntryPoint _http403EntryPoint) {
		super();
		http403EntryPoint = _http403EntryPoint;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder _auth) throws Exception {
		final PassthroughUserProvider passthroughUserProvider = new PassthroughUserProvider();
		_auth.authenticationProvider(passthroughUserProvider);
	}

	/**
	 * All request must be authenticated, No login page.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/openApi*").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic().authenticationEntryPoint(http403EntryPoint)
				.and().csrf().disable();
	}
}
