package com.ubiqube.etsi.mano.model.vnf;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgInstance {
	private String instanceId;
	private Map<UUID, VnfPkgOperation> operations = new HashMap<>();

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
	public Map<UUID, VnfPkgOperation> getOperations() {
		return operations;
	}

	public void setOperations(final Map<UUID, VnfPkgOperation> operations) {
		this.operations = operations;
	}

	public VnfPkgOperation getOperation(@NotNull final UUID id) {
		return operations.get(id);
	}

	public void addOperation(final VnfPkgOperation vnfPackageOperation) {
		operations.put(UUID.fromString(vnfPackageOperation.getId()), vnfPackageOperation);
	}
}
