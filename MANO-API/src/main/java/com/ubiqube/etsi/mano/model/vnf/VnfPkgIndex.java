package com.ubiqube.etsi.mano.model.vnf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgIndex {

	private List<VnfPkgInstance> instances = new ArrayList<>();

	@JsonProperty("Instances")
	public List<VnfPkgInstance> getInstances() {
		return instances;
	}

	public void setInstances(final List<VnfPkgInstance> instances) {
		this.instances = instances;
	}

}
