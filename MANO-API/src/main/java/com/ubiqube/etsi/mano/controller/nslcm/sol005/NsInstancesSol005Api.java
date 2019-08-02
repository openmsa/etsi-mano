package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse200;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesCreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdHealPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdInstantiatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdScalePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdTerminatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdUpdatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesPostQuery;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;

@RestController
public class NsInstancesSol005Api implements NsInstancesSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(NsLcmSol005Api.class);

	private final DeviceService deviceService;
	private final NsdRepository nsdRepository;
	private final NsInstanceRepository nsInstanceRepository;

	private final OrchestrationService orchestrationService;

	public NsInstancesSol005Api(DeviceService _deviceService, NsdRepository _nsdRepository, NsInstanceRepository _nsInstanceRepository, OrchestrationService _orchestrationService) {
		deviceService = _deviceService;
		nsdRepository = _nsdRepository;
		nsInstanceRepository = _nsInstanceRepository;
		orchestrationService = _orchestrationService;
		LOG.debug("Instantiate SOL005 NS Instance.");
	}

	/**
	 * Query multiple NS instances.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@Override
	public ResponseEntity<List<NsInstancesNsInstance>> nsInstancesGet(String accept, String filter, String allFields, String fields, String excludeFields, String excludeDefault) {
		return new ResponseEntity<>(nsInstanceRepository.query(), HttpStatus.OK);
	}

	/**
	 * Delete NS instance resource.
	 *
	 * Delete NS Identifier This method deletes an individual NS instance resource.
	 *
	 */
	@Override
	public void nsInstancesNsInstanceIdDelete(String nsInstanceId) {
		try {
			final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			deviceService.deleteDevice(deviceService.getDeviceId(nsInstanceId), principal.getUsername());
		} catch (final ServiceException e) {
			throw new NotFoundException("Object not found.", e);
		}

	}

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an
	 * individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<InlineResponse200> nsInstancesNsInstanceIdGet(String nsInstanceId) {

		final InlineResponse200 resp = new InlineResponse200();
		final NsInstancesNsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		resp.setNsInstance(nsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Heal a NS instance.
	 *
	 * The POST method requests to heal a NS instance resource. This method shall
	 * follow the provisions specified in the Tables 6.4.7.3.1-1 and 6.4.7.3.1-2 for
	 * URI query parameters, request and response data structures, and response
	 * codes.
	 *
	 */
	@Override
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdHealPost(String nsInstanceId, NsInstancesNsInstanceIdHealPostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdInstantiatePost(String nsInstanceId, NsInstancesNsInstanceIdInstantiatePostQuery body) {
		final Map<String, String> varsMap = new HashMap<>();
		final NsInstancesNsInstance nsInstancesNsInstance = nsInstanceRepository.get(nsInstanceId);
		final String nsdId = nsInstancesNsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		final Map<String, String> userData = (Map<String, String>) nsdInfo.getUserDefinedData();
		varsMap.put("deviceid", userData.get("vimId"));
		varsMap.put("nsPkgId", nsdId);
		final String processName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String serviceName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";
		final long serviceId = 0;
		final String ubiqubeId = userData.get("customerId");
		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(ubiqubeId, serviceId, serviceName, processName, varsMap);
			nsInstancesNsInstance.setNsState(NsStateEnum.INSTANTIATED);
			nsInstanceRepository.save(nsInstancesNsInstance);
			userData.put("msaProcessId", String.valueOf(resp.processId.id));
			nsdRepository.save(nsdInfo);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		return new ResponseEntity<>(nsInstancesNsInstance, HttpStatus.OK);
	}

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdScalePost(String nsInstanceId, String accept, String contentType, NsInstancesNsInstanceIdScalePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Terminate a NS instance.
	 *
	 * Terminate NS task. The POST method terminates a NS instance. This method can
	 * only be used with a NS instance in the INSTANTIATED state. Terminating a NS
	 * instance does not delete the NS instance identifier, but rather transitions
	 * the NS into the NOT_INSTANTIATED state. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 6.4.8.3.1-1 and 6.8.8.3.1-2.
	 *
	 */
	@Override
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdTerminatePost(String nsInstanceId, String accept, String contentType, NsInstancesNsInstanceIdTerminatePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstancesNsInstance> nsInstancesNsInstanceIdUpdatePost(String nsInstanceId, String accept, String contentType, NsInstancesNsInstanceIdUpdatePostQuery body) {
		throw new GenericException("TODO");
	}

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<InlineResponse200> nsInstancesPost(NsInstancesPostQuery body) {
		final NsInstancesCreateNsRequest req = body.getCreateNsRequest();
		if (req.getNsdId() == null) {
			throw new NotFoundException("NsdId field is empty.");
		}
		final NsDescriptorsNsdInfo nsd = nsdRepository.get(req.getNsdId());

		final NsInstancesNsInstance nsInstancesNsInstance = new NsInstancesNsInstance();
		nsInstancesNsInstance.setNsdId(req.getNsdId());
		nsInstancesNsInstance.setNsInstanceDescription(req.getNsDescription());
		nsInstancesNsInstance.setNsInstanceName(req.getNsName());
		nsInstancesNsInstance.setNestedNsInstanceId(nsd.getNestedNsdInfoIds());
		nsInstancesNsInstance.setNsState(NsStateEnum.NOT_INSTANTIATED);
		final String id = UUID.randomUUID().toString();
		nsInstancesNsInstance.setId(id);

		nsInstanceRepository.save(nsInstancesNsInstance);

		final InlineResponse200 resp = new InlineResponse200();
		resp.setNsInstance(nsInstancesNsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
