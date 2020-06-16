package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedBase implements Auditable, BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private Date startTime;

	private Date endTime;

	@Enumerated(EnumType.STRING)
	private NsdChangeType changeType;

	@Enumerated(EnumType.STRING)
	private InstantiationStatusType changeResult = InstantiationStatusType.NOT_STARTED;

	// Vim or VNFM resourceId.
	private String resourceId;

	private String instantiationLevel;

	@ManyToOne
	private NsLcmOpOccs nsLcmOpOccs;

	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public NsdChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final NsdChangeType changeType) {
		this.changeType = changeType;
	}

	public InstantiationStatusType getChangeResult() {
		return changeResult;
	}

	public void setChangeResult(final InstantiationStatusType changeResult) {
		this.changeResult = changeResult;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public NsLcmOpOccs getNsLcmOpOccs() {
		return nsLcmOpOccs;
	}

	public void setNsLcmOpOccs(final NsLcmOpOccs nsLcmOpOccs) {
		this.nsLcmOpOccs = nsLcmOpOccs;
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
