package com.ubiqube.etsi.mano.model.nsd;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NsdPkgInstance {
	private String instanceId;
	final List<NsdPkgOperation> operations = new ArrayList<>();
	final List<String> VnfmOperations = new ArrayList<>();

	public NsdPkgInstance() {
		// Nothing.
	}

	public NsdPkgInstance(final String _instanceId) {
		instanceId = _instanceId;
	}

	@JsonProperty("InstanceId")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(final String instanceId) {
		this.instanceId = instanceId;
	}

	@JsonProperty("Operations")
	public List<NsdPkgOperation> getOperations() {
		return operations;
	}

	@JsonProperty("VnfmOperations")
	public List<String> getVnfmOperations() {
		return VnfmOperations;
	}

}
