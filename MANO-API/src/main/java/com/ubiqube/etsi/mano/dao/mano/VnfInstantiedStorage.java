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
public class VnfInstantiedStorage extends VnfInstantiatedBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VnfStorage vnfVirtualStorage;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	/**
	 * Vim name.
	 */
	private String aliasName;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfStorage getVnfVirtualStorage() {
		return vnfVirtualStorage;
	}

	public void setVnfVirtualStorage(final VnfStorage vnfVirtualStorage) {
		this.vnfVirtualStorage = vnfVirtualStorage;
	}

	@Override
	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	@Override
	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(final String aliasName) {
		this.aliasName = aliasName;
	}

}
