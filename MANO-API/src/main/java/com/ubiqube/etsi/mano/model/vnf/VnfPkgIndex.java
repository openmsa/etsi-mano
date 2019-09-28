package com.ubiqube.etsi.mano.model.vnf;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgIndex {

	private Map<String, VnfPkgInstance> instances = new HashMap<>();

	@JsonProperty("Instances")
	public Map<String, VnfPkgInstance> getInstances() {
		return instances;
	}

	public void setInstances(final Map<String, VnfPkgInstance> instances) {
		this.instances = instances;
	}

	public VnfPkgInstance getVnfPkgInstance(final String _id) {
		return instances.get(_id);
	}

	public void addVnfPkgInstance(final VnfPkgInstance _instance) {
		instances.put(_instance.getInstanceId(), _instance);
	}

	public void remove(final VnfPkgInstance instance) {
		instances.remove(instance.getInstanceId());
	}

	@JsonIgnore
	public boolean isEmpty() {
		return instances.isEmpty();
	}
}
