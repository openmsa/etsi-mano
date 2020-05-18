package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfInstantiedCompute implements Auditable, BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	/*
	 * Vnf Compute.
	 */
	private UUID vduId = null;

	/**
	 * Also reservationId
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private GrantInformation computeResource = null;

	/**
	 * VIM Resources.
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResourceHandleEntity compResource = null;

	@ElementCollection
	private List<String> storageResourceIds = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CpProtocolDataEntity> vnfcCpInfo = null;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	private String flavorId;

	private String imageId;

	@ManyToOne
	private VnfCompute vnfCompute;

	@ManyToOne
	private VnfInstance vnfInstance;
	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
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

	public ResourceHandleEntity getCompResource() {
		return compResource;
	}

	public void setCompResource(final ResourceHandleEntity networkResource) {
		this.compResource = networkResource;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(final String imageId) {
		this.imageId = imageId;
	}

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute _vnfCompute) {
		vnfCompute = _vnfCompute;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
