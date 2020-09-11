package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;

@Entity
@EntityListeners(AuditListener.class)
public class ComputeTask extends Task {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	VnfCompute vnfCompute;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

}
