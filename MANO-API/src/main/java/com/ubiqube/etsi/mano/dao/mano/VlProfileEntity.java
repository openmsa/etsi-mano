package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@EntityListeners(AuditListener.class)
public class VlProfileEntity implements Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Qos qos;

	private Integer linkBitrateRoot;

	private Integer linkBitrateLeaf;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VlProtocolData> virtualLinkProtocolData;

	private Integer maxBitrateRequirementsRoot;

	private Integer maxBitrateRequirementsLeaf;

	private Audit audit;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Qos getQos() {
		return qos;
	}

	public void setQos(final Qos qos) {
		this.qos = qos;
	}

	public Set<VlProtocolData> getVirtualLinkProtocolData() {
		return virtualLinkProtocolData;
	}

	public void setVirtualLinkProtocolData(final Set<VlProtocolData> virtualLinkProtocolData) {
		this.virtualLinkProtocolData = virtualLinkProtocolData;
	}

	public Integer getLinkBitrateRoot() {
		return linkBitrateRoot;
	}

	public void setLinkBitrateRoot(final Integer linkBitrateRoot) {
		this.linkBitrateRoot = linkBitrateRoot;
	}

	public Integer getLinkBitrateLeaf() {
		return linkBitrateLeaf;
	}

	public void setLinkBitrateLeaf(final Integer linkBitrateLeaf) {
		this.linkBitrateLeaf = linkBitrateLeaf;
	}

	public Integer getMaxBitrateRequirementsRoot() {
		return maxBitrateRequirementsRoot;
	}

	public void setMaxBitrateRequirementsRoot(final Integer maxBitrateRequirementsRoot) {
		this.maxBitrateRequirementsRoot = maxBitrateRequirementsRoot;
	}

	public Integer getMaxBitrateRequirementsLeaf() {
		return maxBitrateRequirementsLeaf;
	}

	public void setMaxBitrateRequirementsLeaf(final Integer maxBitrateRequirementsLeaf) {
		this.maxBitrateRequirementsLeaf = maxBitrateRequirementsLeaf;
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
