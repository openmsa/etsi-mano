package com.ubiqube.api.interfaces.orchestration;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;

import java.util.Map;


public interface OrchestrationService {

	public ProcessInstance scheduleServiceImmediateMode(String ubiqubeId, long serviceId, String serviceName, String processName, Map<String, String> varsMap) throws ServiceException;
}
