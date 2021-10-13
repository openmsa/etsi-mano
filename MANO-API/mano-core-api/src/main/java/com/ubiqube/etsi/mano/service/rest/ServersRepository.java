package com.ubiqube.etsi.mano.service.rest;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;

import reactor.core.publisher.Mono;

public class ServersRepository implements ReactiveClientRegistrationRepository {

	@Override
	public Mono<ClientRegistration> findByRegistrationId(String registrationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
