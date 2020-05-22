package com.ubiqube.etsi.mano.dao.mano;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class VirtualLinkInfo extends VnfInstantiatedBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID vnfVirtualLinkDescId = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<LinkPortInfo> vnfLinkPorts = null;

	@ElementCollection
	private final Map<String, String> metadata = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final UUID vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public Set<LinkPortInfo> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(final Set<LinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

}
