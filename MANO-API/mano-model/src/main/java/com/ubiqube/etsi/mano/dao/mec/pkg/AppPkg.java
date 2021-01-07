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
package com.ubiqube.etsi.mano.dao.mec.pkg;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Entity
@Table(schema = "mec_meo")
public class AppPkg {
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String appDId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<DNSRuleDescriptor> appDNSRule;

	private String appDVersion;

	private String appDescription;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<AppExternalCpd> appExtCpd;

	@ElementCollection
	@CollectionTable(schema = "mec_meo")
	private Set<FeatureDependency> appFeatureOptional;

	@ElementCollection
	@CollectionTable(schema = "mec_meo")
	private Set<FeatureDependency> appFeatureRequired;

	private String appInfoName;

	@Embedded
	private LatencyDescriptor appLatency;

	private String appName;

	private String appProvider;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@CollectionTable(schema = "mec_meo")
	private Set<ServiceDependency> appServiceOptional = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<ServiceDescriptor> appServiceProduced = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@CollectionTable(schema = "mec_meo")
	private Set<ServiceDependency> appServiceRequired = new HashSet<>();

	private String appSoftVersion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<TrafficRuleDescriptor> appTrafficRule = new HashSet<>();

	private String changeAppInstanceStateOpConfig;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(schema = "mec_meo")
	private Set<String> mecVersion = new HashSet<>();

	private String swImageDescriptor;

	private String terminateAppInstanceOpConfig;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<TransportDependency> transportDependencies = new HashSet<>();

	private String virtualComputeDescriptor;

	@ElementCollection
	@CollectionTable(schema = "mec_meo")
	private Set<String> virtualStorageDescriptor = new HashSet<>();

}
