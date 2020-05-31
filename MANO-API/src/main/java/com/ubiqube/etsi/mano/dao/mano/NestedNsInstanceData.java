package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NestedNsInstanceData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id = null;

	private String nestedNsInstanceId;

	private String nsProfileId;

	public String getNestedNsInstanceId() {
		return nestedNsInstanceId;
	}

	public void setNestedNsInstanceId(final String nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
	}

	public String getNsProfileId() {
		return nsProfileId;
	}

	public void setNsProfileId(final String nsProfileId) {
		this.nsProfileId = nsProfileId;
	}

	public UUID getId() {
		return id;
	}

}
