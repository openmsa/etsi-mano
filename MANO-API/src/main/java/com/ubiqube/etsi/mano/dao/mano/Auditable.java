package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

public interface Auditable extends Serializable {

	Audit getAudit();

	void setAudit(Audit audit);
}
