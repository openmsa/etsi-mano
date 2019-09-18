package com.ubiqube.etsi.mano.model.nsd;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NsdPkgIndex {

	private List<NsdPkgInstance> instances = new ArrayList<>();

	@JsonProperty("Instances")
	public List<NsdPkgInstance> getInstances() {
		return instances;
	}

	public void setInstances(final List<NsdPkgInstance> instances) {
		this.instances = instances;
	}

}
