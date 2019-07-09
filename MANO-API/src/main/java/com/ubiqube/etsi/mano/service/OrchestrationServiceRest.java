package com.ubiqube.etsi.mano.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;

@Service
public class OrchestrationServiceRest implements OrchestrationService {

	@Override
	public ProcessInstance scheduleServiceImmediateMode(String ubiqubeId, long serviceId, String serviceName, String processName, Map<String, String> varsMap) throws ServiceException {
		return null;
	}

}
