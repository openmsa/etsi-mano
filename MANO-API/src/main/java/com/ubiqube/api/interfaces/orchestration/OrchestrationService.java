package com.ubiqube.api.interfaces.orchestration;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;

public interface OrchestrationService {

	ProcessInstance scheduleServiceImmediateMode(@Nonnull String ubiqubeId, long serviceId, String serviceName, String processName, Map<String, String> varsMap) throws ServiceException;

	ProcessInstance getProcessInstance(final long processId);
}
