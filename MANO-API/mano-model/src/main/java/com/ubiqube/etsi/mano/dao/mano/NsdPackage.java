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

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.nfvo.ArchiveSecurityOptionEnumType;
import com.ubiqube.etsi.mano.dao.mano.nfvo.NsArchiveArtifactInfo;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class NsdPackage implements PackageBase, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	// OVI removed just for plugtest => @Version
	private long version;

	@FullTextField
	private String nsdId;

	@FullTextField
	private String nsdName;

	@FullTextField
	private String nsdVersion;

	@FullTextField
	private String nsdDesigner;

	@FullTextField
	private String nsdInvariantId;

	private String instantiationLevel;

	private int minNumberOfInstance;

	private int maxNumberOfInstance;

	private String flavorId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "nsdPackage")
	private Set<NsdPackageVnfPackage> vnfPkgIds;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn
	private Set<PnfDescriptor> pnfdInfoIds;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
	private Set<NsdPackageNsdPackage> nestedNsdInfoIds;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private OnboardingStateType nsdOnboardingState;

	@IndexedEmbedded
	@Embedded
	private FailureDetails onboardingFailureDetails;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageOperationalState nsdOperationalState;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageUsageState nsdUsageState;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> userDefinedData;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<NsSap> nsSaps;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<NsVirtualLink> nsVirtualLinks;

	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn
	private Set<NsdInstance> nsInstance;
	// 2.7.1
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<NsArchiveArtifactInfo> artifacts;
	// 2.7.1
	private String signingCertificate;
	// 2.7.1
	@Enumerated(EnumType.STRING)
	private ArchiveSecurityOptionEnumType archiveSecurityOption;
	// Probably 3.5.1
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VnffgDescriptor> vnffgs;

	@Override
	public PackageOperationalState getOperationalState() {
		return this.nsdOperationalState;
	}

	@Override
	public OnboardingStateType getOnboardingState() {
		return this.nsdOnboardingState;
	}

	@Override
	public PackageUsageState getUsageState() {
		return this.nsdUsageState;
	}
}
