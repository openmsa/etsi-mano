package com.ubiqube.etsi.mano.dao.mano;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.AffinityOrAntiAffinityRule;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsScaleInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsVirtualLinkInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.PnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.SapInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.VnffgInfo;

@Entity
@Indexed
public class NsdInstance implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Field
	private String nsInstanceName = null;

	@Field
	private String nsInstanceDescription = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackage nsdInfo = null;

	@Field
	private String flavourId = null;

	private String nsInstantiationLevelId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<VnfInstance> vnfInstance = null;

	@Transient
	private List<PnfInfo> pnfInfo = null;
	@Transient
	private List<NsVirtualLinkInfo> virtualLinkInfo = null;
	@Transient
	private List<VnffgInfo> vnffgInfo = null;
	@Transient
	private List<SapInfo> sapInfo = null;

	@OneToMany
	private List<NsdInstance> nestedNsInstance = null;

	@Enumerated(EnumType.STRING)
	@Field
	private InstantiationStateEnum nsState = null;

	@Transient
	private List<NsScaleInfo> nsScaleStatus = null;

	@Transient
	private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<NestedNsInstanceData> nestedNsInstanceData;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstanceData> vnfInstanceData;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nsInstance")
	private Set<NsLcmOpOccs> lcmOpOccs;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsInstanceName() {
		return nsInstanceName;
	}

	public void setNsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
	}

	public String getNsInstanceDescription() {
		return nsInstanceDescription;
	}

	public void setNsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
	}

	public NsdPackage getNsdInfo() {
		return nsdInfo;
	}

	public void setNsdInfo(final NsdPackage nsdInfoId) {
		this.nsdInfo = nsdInfoId;
	}

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public List<VnfInstance> getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final List<VnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public List<PnfInfo> getPnfInfo() {
		return pnfInfo;
	}

	public void setPnfInfo(final List<PnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
	}

	public List<NsVirtualLinkInfo> getVirtualLinkInfo() {
		return virtualLinkInfo;
	}

	public void setVirtualLinkInfo(final List<NsVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
	}

	public List<VnffgInfo> getVnffgInfo() {
		return vnffgInfo;
	}

	public void setVnffgInfo(final List<VnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
	}

	public List<SapInfo> getSapInfo() {
		return sapInfo;
	}

	public void setSapInfo(final List<SapInfo> sapInfo) {
		this.sapInfo = sapInfo;
	}

	public List<NsdInstance> getNestedNsInstance() {
		return nestedNsInstance;
	}

	public void setNestedNsInstance(final List<NsdInstance> nestedNsInstanceId) {
		this.nestedNsInstance = nestedNsInstanceId;
	}

	public InstantiationStateEnum getNsState() {
		return nsState;
	}

	public void setNsState(final InstantiationStateEnum nsState) {
		this.nsState = nsState;
	}

	public List<NsScaleInfo> getNsScaleStatus() {
		return nsScaleStatus;
	}

	public void setNsScaleStatus(final List<NsScaleInfo> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
	}

	public List<AffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
		return additionalAffinityOrAntiAffinityRule;
	}

	public void setAdditionalAffinityOrAntiAffinityRule(final List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
	}

	public String getNsInstantiationLevelId() {
		return nsInstantiationLevelId;
	}

	public void setNsInstantiationLevelId(final String nsInstantiationLevelId) {
		this.nsInstantiationLevelId = nsInstantiationLevelId;
	}

	public Set<NestedNsInstanceData> getNestedNsInstanceData() {
		return nestedNsInstanceData;
	}

	public void setNestedNsInstanceData(final Set<NestedNsInstanceData> nestedNsInstanceData) {
		this.nestedNsInstanceData = nestedNsInstanceData;
	}

	public Set<VnfInstanceData> getVnfInstanceData() {
		return vnfInstanceData;
	}

	public void setVnfInstanceData(final Set<VnfInstanceData> vnfInstanceData) {
		this.vnfInstanceData = vnfInstanceData;
	}

	public Set<NsLcmOpOccs> getLcmOpOccs() {
		return lcmOpOccs;
	}

	public void setLcmOpOccs(final Set<NsLcmOpOccs> lcmOpOccs) {
		this.lcmOpOccs = lcmOpOccs;
	}

	public void addNestedNsInstance(final NsdInstance nsIn) {
		if (null == nestedNsInstance) {
			nestedNsInstance = new ArrayList<>();
		}
		nestedNsInstance.add(nsIn);
	}

}
