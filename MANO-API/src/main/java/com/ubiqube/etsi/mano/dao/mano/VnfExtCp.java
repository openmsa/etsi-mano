package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VnfExtCp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String toscaId;
	private String toscaName;
	private String state;

	private String externalVirtualLink;
	private String internalVirtualLink;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<VirtualNicReq> virtualNetworkInterfaceRequirements;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getToscaId() {
		return toscaId;
	}

	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getExternalVirtualLink() {
		return externalVirtualLink;
	}

	public void setExternalVirtualLink(final String externalVirtualLink) {
		this.externalVirtualLink = externalVirtualLink;
	}

	public String getInternalVirtualLink() {
		return internalVirtualLink;
	}

	public void setInternalVirtualLink(final String internalVirtualLink) {
		this.internalVirtualLink = internalVirtualLink;
	}

	public Set<VirtualNicReq> getVirtualNetworkInterfaceRequirements() {
		return virtualNetworkInterfaceRequirements;
	}

	public void setVirtualNetworkInterfaceRequirements(final Set<VirtualNicReq> virtualNetworkInterfaceRequirements) {
		this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
	}

}
