package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VnfLinkPort implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String toscaId;
	private String toscaName;
	private String state;

	private String virtualLink;

	private String virtualBinding;

	@Override
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

	public String getVirtualLink() {
		return virtualLink;
	}

	public void setVirtualLink(final String virtualLink) {
		this.virtualLink = virtualLink;
	}

	public String getVirtualBinding() {
		return virtualBinding;
	}

	public void setVirtualBinding(final String virtualBinding) {
		this.virtualBinding = virtualBinding;
	}

}
