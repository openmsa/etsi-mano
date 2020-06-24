package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;

@Entity
@Indexed
public class NsdInstance implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

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

	// XXX Add pnfInfo
	// XXX Add virtualLinkInfo
	// XXX Add vnffgInfo
	// XXX Add sapInfo

	@OneToMany
	private List<NsdInstance> nestedNsInstance = null;

	@Enumerated(EnumType.STRING)
	@Field
	private InstantiationStateEnum nsState = null;

	// XXX Add nsScaleStatus

	// XXX Add additionalAffinityOrAntiAffinityRule

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
