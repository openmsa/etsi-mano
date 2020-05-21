package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
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
import javax.persistence.OneToOne;

@Entity
@EntityListeners(AuditListener.class)

public class VnfInstantiedVirtualLink implements Auditable, BaseEntity {
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
	private Map<String, String> metadata = new HashMap<>();

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	@ManyToOne
	private VnfVl vnfVirtualLink;

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

	public ResourceHandleEntity getCompResource() {
		return compResource;
	}

	public void setCompResource(final ResourceHandleEntity compResource) {
		this.compResource = compResource;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfVl getVnfVirtualLink() {
		return vnfVirtualLink;
	}

	public void setVnfVirtualLink(final VnfVl vnfVirtualLink) {
		this.vnfVirtualLink = vnfVirtualLink;
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
