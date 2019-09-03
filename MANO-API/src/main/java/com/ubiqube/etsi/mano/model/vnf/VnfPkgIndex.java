package com.ubiqube.etsi.mano.model.vnf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgIndex {

	private List<VnfPkgInstances> instances = new ArrayList<>();

	@JsonProperty("Instances")
	public List<VnfPkgInstances> getInstances() {
		return instances;
	}

	public void setInstances(final List<VnfPkgInstances> instances) {
		this.instances = instances;
	}

}
