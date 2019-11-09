package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.controller.vnf.ApiTypesEnum;

@Entity
@Indexed
public class Subscription implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	// Used for rebuilding links.
	private ApiTypesEnum api;

	private AuthentificationInformations authentificationInformations;

	@Embedded
	private SubscriptionQuery subscriptionQuery;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public AuthentificationInformations getAuthentificationInformations() {
		return authentificationInformations;
	}

	public void setAuthentificationInformations(final AuthentificationInformations authentificationInformations) {
		this.authentificationInformations = authentificationInformations;
	}

	public SubscriptionQuery getSubscriptionQuery() {
		return subscriptionQuery;
	}

	public void setSubscriptionQuery(final SubscriptionQuery subscriptionQuery) {
		this.subscriptionQuery = subscriptionQuery;
	}

	public ApiTypesEnum getApi() {
		return api;
	}

	public void setApi(final ApiTypesEnum api) {
		this.api = api;
	}

}
