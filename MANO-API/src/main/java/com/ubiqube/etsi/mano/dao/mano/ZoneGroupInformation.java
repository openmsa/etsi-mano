package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZoneGroupInformation implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> zoneId = new LinkedHashSet<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Set<String> getZoneId() {
		return zoneId;
	}

	public void setZoneId(final Set<String> zoneId) {
		this.zoneId = zoneId;
	}

}
