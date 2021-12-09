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
package com.ubiqube.etsi.mano.dao.mano.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersion;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private AuthentificationInformations authentification;

	private String url;

	private boolean ignoreSsl;

	@Lob
	private String tlsCert;

	private String version;

	@Enumerated(EnumType.STRING)
	// XXX only on DB@NotNull
	private SubscriptionType subscriptionType;

	@Enumerated(EnumType.STRING)
	private PlanStatusType serverStatus;

	private FailureDetails failureDetails;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> capabilities;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RemoteSubscription> remoteSubscriptions;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ApiVersion> versions;

	public void addVersion(final ApiVersion version2) {
		if (null == versions) {
			versions = new HashSet<>();
		}
		versions.add(version2);
	}
}
