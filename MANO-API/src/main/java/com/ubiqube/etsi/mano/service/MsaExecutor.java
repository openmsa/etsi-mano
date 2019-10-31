package com.ubiqube.etsi.mano.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

/**
 * NFVO+VNFM & NVFO MSA implementation.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MsaExecutor implements Vim {
	private static final String CUSTOMER_ID = "customerId";

	private static final Logger LOG = LoggerFactory.getLogger(MsaExecutor.class);

	private final OrchestrationService orchestrationService;

	public MsaExecutor(final OrchestrationService orchestrationService) {
		super();
		this.orchestrationService = orchestrationService;
	}

	@Override
	public String onVnfInstanceTerminate(final Map<String, Object> userData) {
		final String msaServiceId = (String) userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = (String) userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	@Override
	public String onVnfInstantiate(final String vnfPkgId, final Map<String, Object> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = (String) userData.get(CUSTOMER_ID);
		varsMap.put("vnfPkgId", vnfPkgId);
		varsMap.put(CUSTOMER_ID, customerId);
		// TODO My NFVO
		varsMap.put("nfvoDevice", "TMA129");
		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	@Override
	public String onNsInstantiate(final String nsdId, final Map<String, Object> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = (String) userData.get(CUSTOMER_ID);
		varsMap.put("deviceid", (String) userData.get("vimId"));
		varsMap.put("nsPkgId", nsdId);
		// TODO My NFVO
		varsMap.put("nfvoDevice", "TMA129");
		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	@Override
	public String onNsInstanceTerminate(final Map<String, Object> userData) {
		final String msaServiceId = (String) userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = (String) userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	private String executeProcess(final String customerId, final long serviceId, final String serviceName, final String processName) {
		final Map<String, String> varsMap = new HashMap<>();
		return executeProcess(customerId, serviceId, serviceName, processName, varsMap);
	}

	private String executeProcess(final String customerId, final long serviceId, final String serviceName, final String processName, final Map<String, String> varsMap) {
		try {
			LOG.info("Calling MSA remote FW: custormerId={}, serviceId={}, serviceName={}, processName={}, params={}", customerId, serviceId, serviceName, processName, varsMap);
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			return String.valueOf(resp.getProcessId().getId());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public LcmOperationStateType waitForCompletion(final String processId, final int seconds) {
		LOG.debug("Entering Wait for Completion.");
		while (true) {
			try {
				final ProcessInstance res = orchestrationService.getProcessInstance(new Long(processId));
				final String status = res.getStatus().getStatus();
				if (!"RUNNING".equals(status)) {
					LOG.debug("Wait for completion done with result: {}", status);
					return convert(status);
				}
				Thread.sleep(15 * 1000);
			} catch (NumberFormatException | InterruptedException e) {
				throw new GenericException(e);
			}
		}

	}

	private static LcmOperationStateType convert(final String status) {

		if ("FAIL".equals(status)) {
			return LcmOperationStateType.FAILED;
		} else if ("ENDED".equals(status)) {
			return LcmOperationStateType.COMPLETED;
		} else if ("RUNNING".equals(status)) {
			return LcmOperationStateType.PROCESSING;
		}
		LOG.warn("Unknown status: {}", status);
		return null;
	}

}
