package com.ubiqube.etsi.mano.dao.mano;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {
	@PrePersist
	public void setCreatedOn(final Auditable auditable) {
		Audit audit = auditable.getAudit();

		if (audit == null) {
			audit = new Audit();
			auditable.setAudit(audit);
		}

		audit.setCreatedOn(LocalDateTime.now());
	}

	@PreUpdate
	public void setUpdatedOn(final Auditable auditable) {
		final Audit audit = auditable.getAudit();

		audit.setUpdatedOn(LocalDateTime.now());
	}
}
