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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Indexed
public class Subscription implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@FullTextField
	private UUID id;

	// Used for rebuilding links.
	@Enumerated(EnumType.STRING)
	@FullTextField
	private ApiTypesEnum api;

	private AuthentificationInformations authentificationInformations;

	private String callbackUri;

	@Enumerated(EnumType.STRING)
	@NotNull
	@FullTextField
	private SubscriptionType subscriptionType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
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

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(final SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

}
