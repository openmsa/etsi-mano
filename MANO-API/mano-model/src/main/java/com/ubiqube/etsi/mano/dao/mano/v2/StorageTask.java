package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

@Entity
public class StorageTask extends Task {
	/** Version. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfStorage vnfStorage;

	private String parentAlias;

	public VnfStorage getVnfStorage() {
		return vnfStorage;
	}

	public void setVnfStorage(final VnfStorage vnfStorage) {
		this.vnfStorage = vnfStorage;
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public void setParentAlias(final String parentAlias) {
		this.parentAlias = parentAlias;
	}

}
