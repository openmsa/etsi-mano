package com.ubiqube.etsi.mano.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrchestrationServiceRest implements OrchestrationService {

	@Autowired
	private UbiRest rest;

	@Override
	public ProcessInstance scheduleServiceImmediateMode(final String ubiqubeId, final long serviceId, final String serviceName, final String processName, final Map<String, String> varsMap) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("orchestration/v1/scheduleServiceImmediateMode")
				.queryParam("ubiqubeId", ubiqubeId)
				.queryParam("serviceId", serviceId)
				.queryParam("serviceName", serviceName)
				.queryParam("processName", processName)
				.build()
				.toUri();
		return rest.post(uri, varsMap, ProcessInstance.class);
	}

	@Override
	public ProcessInstance getProcessInstance(final long processId) {
		final Map<String, String> vars = new HashMap<>();
		vars.put("processId", Long.toString(processId));
		final URI uri = rest.uriBuilder()
				.pathSegment("orchestration/process/instance/{processId}")
				.buildAndExpand(vars)
				.toUri();
		return rest.get(uri, ProcessInstance.class);
	}
}
