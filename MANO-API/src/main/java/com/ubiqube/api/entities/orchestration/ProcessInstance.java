package com.ubiqube.api.entities.orchestration;

public interface ProcessInstance {

	public ProcessId processId = null;
	ServiceId serviceId = null;

	public interface ServiceId {
		public long id = 0;
	}

	public interface ProcessId {
		public long id = 0;
	}
}
