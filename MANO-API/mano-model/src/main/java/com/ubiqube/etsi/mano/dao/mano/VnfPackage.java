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
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
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
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.pkg.OsContainer;
import com.ubiqube.etsi.mano.dao.mano.pkg.OsContainerDeployableUnit;
import com.ubiqube.etsi.mano.dao.mano.pkg.PackageSecurityOptionType;
import com.ubiqube.etsi.mano.dao.mano.pkg.UploadUriParameters;
import com.ubiqube.etsi.mano.dao.mano.pkg.VirtualCp;
import com.ubiqube.etsi.mano.dao.mano.pkg.VnfProfile;
import com.ubiqube.etsi.mano.dao.mano.vnfi.VimCapability;
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

	private String flavourDescription;

	@FullTextField
	private String descriptorId;

	@FullTextField
	private String descriptorVersion;

	private String productInfoDescription;

	private String defaultLocalizationLanguage;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> localizationLanguages;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderColumn
	private Set<ListKeyPair> virtualLinks;

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
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> vnfmInfo;
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
	private Set<SoftwareImage> softwareImages;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MonitoringParams> monitoringParameters;
	// Original vnf package id in NFVO.
	private String nfvoId;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SecurityGroup> securityGroups;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AffinityRule> affinityRules;

	@Embedded
	private VnfProfile vnfProfile;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ScaleInfo> scaleStatus;

	/**
	 * A collection of mandatory Vim capabilities.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<VimCapability> vimCapabilities;

	/**
	 * Content type of the generated vnfd file. Either text/plain or
	 * application/zip.
	 */
	private String vnfdContentType;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UploadUriParameters uploadUriParameters;

	/**
	 * The Vdu.OsContainer node type represents the OsContainerDesc information
	 * element as defined in ETSI GS NFV-IFA 011 [1]. Table 6.8.12.1-1 specifies the
	 * declared names for this node type. These names shall be used as specified in
	 * TOSCA-Simple-Profile-YAML-v1.3 [20].
	 *
	 * @since 4.2.1
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OsContainer> osContainer;

	/**
	 * The Vdu.OsContainerDeployableUnit node type describes the aggregate of OS
	 * containers of a VDU (when realized as OS containers) which is a construct
	 * supporting the description of the deployment and operational behaviour of a
	 * VNFC.
	 *
	 * @since 4.2.1
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OsContainerDeployableUnit> osContainerDeployableUnits;

	/**
	 * A VirtualCp node type represents the VirtualCpd information element as
	 * defined in ETSI GS NFV-IFA 011 [1], which describes a requirement to create a
	 * virtual connection point allowing the access to a number of VNFC instances
	 * (based on their respective VDUs).
	 *
	 * @Since 4.2.1
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VirtualCp> virtualCp;

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

	public void addVirtualLink(final String name) {
		if (null == virtualLinks) {
			virtualLinks = new HashSet<>();
		}
		virtualLinks.add(new ListKeyPair(name, virtualLinks.size()));
	}

	@Override
	public String toString() {
		return ToStringUtil.toString(this);
	}
}
