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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class NsdPackage implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	@Field
	private String nsdId;

	@Field
	private String nsdName;

	@Field
	private String nsdVersion;

	@Field
	private String nsdDesigner;

	@Field
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
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private NsdOnboardingStateType nsdOnboardingState;

	@IndexedEmbedded
	private FailureDetails onboardingFailureDetails;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageOperationalStateType nsdOperationalState;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageUsageStateType nsdUsageState;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> userDefinedData;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<NsSap> nsSaps;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<NsVirtualLink> nsVirtualLinks;

	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn
	private Set<NsdInstance> nsInstance;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public String getNsdName() {
		return nsdName;
	}

	public void setNsdName(final String nsdName) {
		this.nsdName = nsdName;
	}

	public String getNsdVersion() {
		return nsdVersion;
	}

	public void setNsdVersion(final String nsdVersion) {
		this.nsdVersion = nsdVersion;
	}

	public String getNsdDesigner() {
		return nsdDesigner;
	}

	public void setNsdDesigner(final String nsdDesigner) {
		this.nsdDesigner = nsdDesigner;
	}

	public String getNsdInvariantId() {
		return nsdInvariantId;
	}

	public void setNsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
	}

	public Set<NsdPackageVnfPackage> getVnfPkgIds() {
		return vnfPkgIds;
	}

	public void setVnfPkgIds(final Set<NsdPackageVnfPackage> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
	}

	public Set<PnfDescriptor> getPnfdInfoIds() {
		return pnfdInfoIds;
	}

	public void setPnfdInfoIds(final Set<PnfDescriptor> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
	}

	public Set<NsdPackageNsdPackage> getNestedNsdInfoIds() {
		return nestedNsdInfoIds;
	}

	public void setNestedNsdInfoIds(final Set<NsdPackageNsdPackage> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
	}

	public NsdOnboardingStateType getNsdOnboardingState() {
		return nsdOnboardingState;
	}

	public void setNsdOnboardingState(final NsdOnboardingStateType nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
	}

	public FailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final FailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PackageOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final PackageOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public PackageUsageStateType getNsdUsageState() {
		return nsdUsageState;
	}

	public void setNsdUsageState(final PackageUsageStateType nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
	}

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public int getMinNumberOfInstance() {
		return minNumberOfInstance;
	}

	public void setMinNumberOfInstance(final int minNumberOfInstance) {
		this.minNumberOfInstance = minNumberOfInstance;
	}

	public int getMaxNumberOfInstance() {
		return maxNumberOfInstance;
	}

	public void setMaxNumberOfInstance(final int maxNumberOfInstance) {
		this.maxNumberOfInstance = maxNumberOfInstance;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public Set<NsSap> getNsSaps() {
		return nsSaps;
	}

	public void setNsSaps(final Set<NsSap> nsSaps) {
		this.nsSaps = nsSaps;
	}

	public Set<NsVirtualLink> getNsVirtualLinks() {
		return nsVirtualLinks;
	}

	public void setNsVirtualLinks(final Set<NsVirtualLink> nsVirtualLinks) {
		this.nsVirtualLinks = nsVirtualLinks;
	}

	public Set<NsdInstance> getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final Set<NsdInstance> nsInstance) {
		this.nsInstance = nsInstance;
	}

}
