package com.ubiqube.etsi.mano.nfvo.v261.model.nsd;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NsdPkgIndex {

	private Map<String, NsdPkgInstance> instances = new HashedMap();

	@JsonProperty("Instances")
	public Map<String, NsdPkgInstance> getInstances() {
		return instances;
	}

	public void setInstances(final Map<String, NsdPkgInstance> instances) {
		this.instances = instances;
	}

	public NsdPkgInstance getNsdPkgInstance(final String _id) {
		return instances.get(_id);
	}

	public void addNsdPkgInstance(final NsdPkgInstance _nNsdPkgInstance) {
		instances.put(_nNsdPkgInstance.getInstanceId(), _nNsdPkgInstance);
	}
}
