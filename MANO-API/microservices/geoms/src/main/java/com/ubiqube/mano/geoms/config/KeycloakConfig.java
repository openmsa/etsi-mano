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
package com.ubiqube.mano.geoms.config;

import java.security.Principal;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import de.codecentric.boot.admin.client.config.ClientProperties;
import de.codecentric.boot.admin.client.registration.BlockingRegistrationClient;

@KeycloakConfiguration
@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
public class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		super.configure(http);
		super.configure(http);
		http.csrf().disable()
				.authorizeRequests()
				.requestMatchers(EndpointRequest.to(
						InfoEndpoint.class,
						HealthEndpoint.class))
				.permitAll()
				.requestMatchers(EndpointRequest.toAnyEndpoint())
				.hasRole("ACTUATOR")
				.anyRequest().permitAll();
	}

	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		final SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
		grantedAuthorityMapper.setPrefix("ROLE_");
		grantedAuthorityMapper.setConvertToUpperCase(true);

		final KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
	}

	@Bean
	protected SessionRegistry buildSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public KeycloakSecurityContext provideKeycloakSecurityContext() {

		final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		Principal principal = attributes.getRequest().getUserPrincipal();
		if (principal == null) {
			return null;
		}

		if (principal instanceof KeycloakAuthenticationToken) {
			principal = Principal.class.cast(KeycloakAuthenticationToken.class.cast(principal).getPrincipal());
		}

		if (principal instanceof KeycloakPrincipal) {
			return KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext();
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public BlockingRegistrationClient registrationClient(final ClientProperties client, final KeycloakClientRequestFactory factory, final KeycloakSpringBootProperties keycloakProperties) {
		final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setGrantType(OAuth2Constants.CLIENT_CREDENTIALS);
		resourceDetails.setAccessTokenUri(keycloakProperties.getAuthServerUrl());
		resourceDetails.setClientId("actuator");
		resourceDetails.setClientSecret("ubiqube");

		final OAuth2RestTemplate rest = new OAuth2RestTemplate(resourceDetails);
		return new BlockingRegistrationClient(rest);
	}

}
