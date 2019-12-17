package com.ubiqube.etsi.mano.dao.mano;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

@Embeddable
public class ZoneGroupInformation {
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> zoneId = new ArrayList<>();

	public List<String> getZoneId() {
		return zoneId;
	}

	public void setZoneId(final List<String> zoneId) {
		this.zoneId = zoneId;
	}

}
