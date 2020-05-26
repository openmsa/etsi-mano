package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfLiveInstance implements BaseEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfInstance vnfInstance;

	private String instantiationLevel;

	private VnfInstantiatedBase vnfInstantiatedBase;

	@Embedded
	private Audit audit;

	public VnfLiveInstance(final VnfInstance vnfInstance, final String instantiationLevel, final VnfInstantiatedBase vnfInstantiatedBase) {
		super();
		this.vnfInstance = vnfInstance;
		this.instantiationLevel = instantiationLevel;
		this.vnfInstantiatedBase = vnfInstantiatedBase;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfInstantiatedBase getVnfInstantiatedBase() {
		return vnfInstantiatedBase;
	}

	public void setVnfInstantiatedBase(final VnfInstantiatedBase vnfInstantiatedBase) {
		this.vnfInstantiatedBase = vnfInstantiatedBase;
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
