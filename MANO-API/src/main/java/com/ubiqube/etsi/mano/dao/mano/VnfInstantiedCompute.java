package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VnfInstantiedCompute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vduId = null;
	/**
	 * Also reservationId
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private GrantInformation computeResource = null;

	@ElementCollection
	private List<String> storageResourceIds = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CpProtocolDataEntity> vnfcCpInfo = null;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public GrantInformation getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(final GrantInformation computeResource) {
		this.computeResource = computeResource;
	}

	public List<String> getStorageResourceIds() {
		return storageResourceIds;
	}

	public void setStorageResourceIds(final List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
	}

	public Set<CpProtocolDataEntity> getVnfcCpInfo() {
		return vnfcCpInfo;
	}

	public void setVnfcCpInfo(final Set<CpProtocolDataEntity> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

}
