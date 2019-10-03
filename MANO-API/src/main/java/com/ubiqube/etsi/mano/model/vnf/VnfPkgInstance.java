package com.ubiqube.etsi.mano.model.vnf;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgInstance {
	private String instanceId;
	private Map<String, VnfPkgOperation> operations = new HashMap<>();

	public VnfPkgInstance() {
		// Nothing.
	}

	public VnfPkgInstance(final String _instanceId) {
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
	public Map<String, VnfPkgOperation> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, VnfPkgOperation> operations) {
		this.operations = operations;
	}

	public VnfPkgOperation getOperation(@NotNull final String id) {
		return operations.get(id);
	}

	public void addOperation(final VnfPkgOperation vnfPackageOperation) {
		operations.put(vnfPackageOperation.getId(), vnfPackageOperation);
	}
}
