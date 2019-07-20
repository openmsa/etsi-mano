package com.ubiqube.api.entities.orchestration;


public interface ProcessInstance {

	public ProcessId processId = null;


	public interface ProcessId {
		public long id = 0;
	}
}
