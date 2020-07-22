package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class Subscription implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	// Used for rebuilding links.
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private ApiTypesEnum api;

	private AuthentificationInformations authentificationInformations;

	private String callbackUri;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<FilterAttributes> filters;

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

	public ApiTypesEnum getApi() {
		return api;
	}

	public void setApi(final ApiTypesEnum api) {
		this.api = api;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public List<FilterAttributes> getFilters() {
		return filters;
	}

	public void setFilters(final List<FilterAttributes> filters) {
		this.filters = filters;
	}

}
