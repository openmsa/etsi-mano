package com.ubiqube.etsi.mano.dao.mano;

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
public class VnfLiveInstance implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfInstance vnfInstance;

	private String instantiationLevel;

	@ManyToOne
	private VnfInstantiatedBase vnfInstantiatedBase;

	@ManyToOne
	private VnfLcmOpOccs vnfLcmOpOccs;

	/**
	 * VIM resourceId.
	 */
	private String resourceId;

	private UUID vduId;

	@Embedded
	private Audit audit;

	public VnfLiveInstance() {
		// Nothing.
	}

	public VnfLiveInstance(final VnfInstance vnfInstance, final String instantiationLevel, final VnfInstantiatedBase vnfInstantiatedBase, final VnfLcmOpOccs vnfLcmOpOccs, final String _resourceId, final UUID _vduId) {
		super();
		this.vnfInstance = vnfInstance;
		this.instantiationLevel = instantiationLevel;
		this.vnfInstantiatedBase = vnfInstantiatedBase;
		this.vnfLcmOpOccs = vnfLcmOpOccs;
		resourceId = _resourceId;
		vduId = _vduId;
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

	public VnfLcmOpOccs getVnfLcmOpOccs() {
		return vnfLcmOpOccs;
	}

	public void setVnfLcmOpOccs(final VnfLcmOpOccs vnfLcmOpOccs) {
		this.vnfLcmOpOccs = vnfLcmOpOccs;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
		this.vduId = vduId;
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
