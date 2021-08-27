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

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexingDependency;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.ind.VnfIndicators;
import com.ubiqube.etsi.mano.dao.mano.pkg.PackageSecurityOptionType;
import com.ubiqube.etsi.mano.utils.ToStringIgnore;
import com.ubiqube.etsi.mano.utils.ToStringUtil;

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
public class VnfPackage implements PackageBase, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	private UUID id;

	private String defaultInstantiationLevel;

	@FullTextField
	private String vnfdId;

	@FullTextField
	private String vnfProvider;

	@FullTextField
	private String vnfProductName;

	@FullTextField
	private String vnfSoftwareVersion;

	@FullTextField
	private String vnfdVersion;

	@FullTextField
	private String flavorId;

	@FullTextField
	private String descriptorId;

	@FullTextField
	private String descriptorVersion;

	@Embedded
	@IndexedEmbedded
	private PkgChecksum checksum;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@IndexedEmbedded
	@IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
	private Set<AdditionalArtifact> additionalArtifacts;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private OnboardingStateType onboardingState;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageOperationalState operationalState;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageUsageState usageState;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Map<String, String> userDefinedData;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexedEmbedded
	@IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
	private Set<VnfCompute> vnfCompute = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexedEmbedded
	private Set<VnfVl> vnfVl = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfStorage> vnfStorage = new LinkedHashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfLinkPort> vnfLinkPort = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfExtCp> vnfExtCp = new LinkedHashSet<>();

	@ToStringIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<NsdInstance> nsInstance;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<OsContainerDesc> osContainerDesc;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<UUID> mciopId;

	@Embedded
	private Audit audit;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@IndexedEmbedded
	private Set<VnfInstantiationLevels> vnfInstantiationLevels;

	@ToStringIgnore
	@ManyToMany(cascade = CascadeType.DETACH, mappedBy = "vnfPackage")
	private Set<NsdPackageVnfPackage> nsdPackages;

	// 2.7.1
	private String vnfmInfo;
	// 2.8.1
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> vnfmInfo281;
	// 2.7.1
	private PackageSecurityOptionType packageSecurityOption = null;
	// 2.7.1
	private String signingCertificate = null;
	// 2.7.1
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> compatibleSpecificationVersions;
	// 2.7.1
	private FailureDetails onboardingFailureDetails = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VnfIndicators> vnfIndicators;

	@Version
	private long version;

	public void addInstantiationLevel(final VnfInstantiationLevels il) {
		if (null == vnfInstantiationLevels) {
			vnfInstantiationLevels = new HashSet<>();
		}
		il.setVnfPackage(this);
		vnfInstantiationLevels.add(il);
	}

	public void addNsdPackage(final NsdPackageVnfPackage nsdPackage) {
		if (null == nsdPackages) {
			nsdPackages = new HashSet<>();
		}
		nsdPackages.add(nsdPackage);
	}

	@Override
	public String toString() {
		return ToStringUtil.toString(this);
	}
}
