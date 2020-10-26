package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VnfVl;

@Entity
public class NetworkTask extends Task {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfVl vnfVl;

	private String vimZoneId;

	public VnfVl getVnfVl() {
		return vnfVl;
	}

	public void setVnfVl(final VnfVl vnfVl) {
		this.vnfVl = vnfVl;
	}

	public String getVimZoneId() {
		return vimZoneId;
	}

	public void setVimZoneId(final String vimZoneId) {
		this.vimZoneId = vimZoneId;
	}

}
