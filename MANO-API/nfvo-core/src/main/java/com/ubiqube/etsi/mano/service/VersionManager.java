package com.ubiqube.etsi.mano.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VersionManager {

	private List<VersionService> services;

	public VersionService getSubscriptionService(final String event) {
		//
		return services.get(0);
	}
}
