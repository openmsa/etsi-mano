package com.ubiqube.etsi.mano.service;

import java.net.URI;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;

@Service
public class OrchestrationServiceRest implements OrchestrationService {

	private final static UbiRest rest = new UbiRest();

	@Override
	public ProcessInstance scheduleServiceImmediateMode(String ubiqubeId, long serviceId, String serviceName, String processName, Map<String, String> varsMap) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("orchestration/v1/scheduleImmediateMode")
				.queryParam("ubiqubeId", ubiqubeId)
				.queryParam("serviceId", serviceId)
				.queryParam("serviceName", serviceName)
				.queryParam("processName", processName)
				.build()
				.toUri();
		return rest.post(uri, varsMap, ProcessInstanceModel.class);
	}

	static class ProcessInstanceModel implements ProcessInstance {
	}
}
