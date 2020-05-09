package com.ubiqube.etsi.mano.dao.mano;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZoneGroupInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> zoneId = new ArrayList<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public List<String> getZoneId() {
		return zoneId;
	}

	public void setZoneId(final List<String> zoneId) {
		this.zoneId = zoneId;
	}

}
