package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class VnfExtCp implements Serializable, ToscaEntity {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;

	private String toscaName;

	private String state;

	private String externalVirtualLink;

	private String internalVirtualLink;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VirtualNicReq> virtualNetworkInterfaceRequirements;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public String getToscaId() {
		return toscaId;
	}

	@Override
	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	@Override
	public String getToscaName() {
		return toscaName;
	}

	@Override
	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
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
