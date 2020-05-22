package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfInstantiedVirtualLink extends VnfInstantiatedBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	@ManyToOne
	private VnfVl vnfVirtualLink;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfVl getVnfVirtualLink() {
		return vnfVirtualLink;
	}

	public void setVnfVirtualLink(final VnfVl vnfVirtualLink) {
		this.vnfVirtualLink = vnfVirtualLink;
	}

}
