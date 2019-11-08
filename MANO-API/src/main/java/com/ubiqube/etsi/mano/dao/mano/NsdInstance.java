package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceNsScaleStatus;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstancePnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceSapInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVirtualLinkInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnffgInfo;

@Entity
@Indexed
public class NsdInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Field
	private String nsInstanceName = null;

	@Field
	private String nsInstanceDescription = null;

	@Field
	private String nsdId = null;

	@OneToOne
	private NsdPackage nsdInfo = null;

	@Field
	private String flavourId = null;

	@OneToMany
	private List<VnfInstance> vnfInstance = null;

	@Transient
	private List<NsInstancesNsInstancePnfInfo> pnfInfo = null;
	@Transient
	private List<NsInstancesNsInstanceVirtualLinkInfo> virtualLinkInfo = null;
	@Transient
	private List<NsInstancesNsInstanceVnffgInfo> vnffgInfo = null;
	@Transient
	private List<NsInstancesNsInstanceSapInfo> sapInfo = null;

	@OneToMany
	private List<NsdInstance> nestedNsInstance = null;
	@Enumerated(EnumType.STRING)
	@Field
	private InstantiationStateEnum nsState = null;
	@Transient
	private List<NsInstancesNsInstanceNsScaleStatus> nsScaleStatus = null;
	@Transient
	private List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

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

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
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

	public List<NsInstancesNsInstancePnfInfo> getPnfInfo() {
		return pnfInfo;
	}

	public void setPnfInfo(final List<NsInstancesNsInstancePnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
	}

	public List<NsInstancesNsInstanceVirtualLinkInfo> getVirtualLinkInfo() {
		return virtualLinkInfo;
	}

	public void setVirtualLinkInfo(final List<NsInstancesNsInstanceVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
	}

	public List<NsInstancesNsInstanceVnffgInfo> getVnffgInfo() {
		return vnffgInfo;
	}

	public void setVnffgInfo(final List<NsInstancesNsInstanceVnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
	}

	public List<NsInstancesNsInstanceSapInfo> getSapInfo() {
		return sapInfo;
	}

	public void setSapInfo(final List<NsInstancesNsInstanceSapInfo> sapInfo) {
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

	public List<NsInstancesNsInstanceNsScaleStatus> getNsScaleStatus() {
		return nsScaleStatus;
	}

	public void setNsScaleStatus(final List<NsInstancesNsInstanceNsScaleStatus> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
	}

	public List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
		return additionalAffinityOrAntiAffinityRule;
	}

	public void setAdditionalAffinityOrAntiAffinityRule(final List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
	}

}
