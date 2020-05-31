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
public class NsLiveInstance implements BaseEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String instantiationLevel;

	@ManyToOne
	private NsInstantiatedBase nsInstantiatedBase;

	@ManyToOne
	private NsLcmOpOccs nsLcmOpOccs;

	private String resourceId;

	@Embedded
	private Audit audit;

	public NsLiveInstance() {
		// Nothing.
	}

	public NsLiveInstance(final String vnfInstanceId, final String instantiationLevel, final NsInstantiatedBase nsInstantiatedBase, final NsLcmOpOccs nsLcmOpOccs) {
		super();
		this.resourceId = vnfInstanceId;
		this.instantiationLevel = instantiationLevel;
		this.nsInstantiatedBase = nsInstantiatedBase;
		this.nsLcmOpOccs = nsLcmOpOccs;
		this.resourceId = resourceId;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public NsInstantiatedBase getNsInstantiatedBase() {
		return nsInstantiatedBase;
	}

	public void setNsInstantiatedBase(final NsInstantiatedBase nsInstantiatedBase) {
		this.nsInstantiatedBase = nsInstantiatedBase;
	}

	public NsLcmOpOccs getNsLcmOpOccs() {
		return nsLcmOpOccs;
	}

	public void setNsLcmOpOccs(final NsLcmOpOccs nsLcmOpOccs) {
		this.nsLcmOpOccs = nsLcmOpOccs;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
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
