package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class VlProfileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Qos qos;

	private Integer linkBitrateRoot;

	private Integer linkBitrateLeaf;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<VlProtocolData> virtualLinkProtocolData;

	private Integer maxBitrateRequirementsRoot;

	private Integer maxBitrateRequirementsLeaf;

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

	public List<VlProtocolData> getVirtualLinkProtocolData() {
		return virtualLinkProtocolData;
	}

	public void setVirtualLinkProtocolData(final List<VlProtocolData> virtualLinkProtocolData) {
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

}
