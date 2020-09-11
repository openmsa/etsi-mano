package com.ubiqube.etsi.mano.dao.mano.v2;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;

@Entity
@EntityListeners(AuditListener.class)
public class Task implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	LocalDateTime startDate;

	LocalDateTime endDate;

	PlanStatusType status;

	String vimResourceId;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public PlanStatusType getStatus() {
		return status;
	}

	public void setStatus(final PlanStatusType status) {
		this.status = status;
	}

	public String getVimResourceId() {
		return vimResourceId;
	}

	public void setVimResourceId(final String vimResourceId) {
		this.vimResourceId = vimResourceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
