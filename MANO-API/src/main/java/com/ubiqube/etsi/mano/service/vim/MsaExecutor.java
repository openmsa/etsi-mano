package com.ubiqube.etsi.mano.service.vim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.eclipse.jdt.annotation.NonNull;
import org.jgrapht.ListenableGraph;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.service.graph.vnfm.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

import ma.glasnost.orika.MapperFacade;

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

	private String defaultfNfvo;

	private String devaultfVim;

	private final OrchestrationService orchestrationService;

	private final VimConnectionInformationJpa vimJpa;

	private final MapperFacade mapper;

	public MsaExecutor(final OrchestrationService orchestrationService, final VimConnectionInformationJpa _vimJpa, final MapperFacade _mapper) {
		super();
		this.orchestrationService = orchestrationService;
		vimJpa = _vimJpa;
		mapper = _mapper;
	}

	@Override
	public String onVnfInstanceTerminate(final Map<String, String> userData) {
		final String msaServiceId = userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	@Override
	public String onVnfInstantiate(final GrantInformation grantInformation, final VnfPackage vnfPackage) {
		final Set<VimConnectionInformation> vims = vimJpa.findByVimType("MSA_20");
		if (vims.isEmpty()) {
			throw new GenericException("Unable to find configuration for im MSA_20");
		}
		final VimConnectionInformation vci = vims.iterator().next();
		LOG.info("VNF Instantiante using VIM {}", vci.getId());
		final Map<String, String> accessInfo = vci.getAccessInfo();
		defaultfNfvo = accessInfo.get("defaultNfvo");
		devaultfVim = accessInfo.get("defaultVim");

		final Map<String, String> varsMap = new HashMap<>();
		final Map<String, String> userData = vnfPackage.getUserDefinedData();
		final String customerId = userData.get(CUSTOMER_ID);
		varsMap.put("vnfPkgId", vnfPackage.getId().toString());
		varsMap.put(CUSTOMER_ID, customerId);
		varsMap.put("nfvoDevice", defaultfNfvo);
		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	@Override
	public String onNsInstantiate(final UUID nsdId, final Map<String, Object> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = (String) userData.get(CUSTOMER_ID);
		varsMap.put("deviceid", getDefault((String) userData.get("vimId"), devaultfVim));
		varsMap.put("nsPkgId", nsdId.toString());
		varsMap.put("nfvoDevice", defaultfNfvo);
		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	@Override
	public String onNsInstanceTerminate(final String processId, final Map<String, Object> userData) {
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
			LOG.info("Calling MSA remote FW: custormerId={}, serviceId={}, servic/home/ncuser/etsi-mano/WORKFLOWS/ETSI-MANO/Reference/Common/mano.phpeName={}, processName={}, params={}", customerId, serviceId, serviceName, processName, varsMap);
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			return String.valueOf(resp.getProcessId().getId());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public VimStatus waitForCompletion(final String processId, final int seconds) {
		LOG.debug("Entering Wait for Completion.");
		while (true) {
			try {
				final ProcessInstance res = orchestrationService.getProcessInstance(Long.parseLong(processId));
				final String status = res.getStatus().getStatus();
				if (!"RUNNING".equals(status)) {
					LOG.debug("Wait for completion done with result: {}", status);
					if ("ENDED".equals(status)) {
						return createResult(LcmOperationStateType.COMPLETED, "Ok");
					}
					LOG.error("Error: {}", res);
					return createResult(LcmOperationStateType.FAILED, res.toString());
				}
				Thread.sleep(15 * 1000L);
			} catch (NumberFormatException | InterruptedException e) {
				throw new GenericException(e);
			}
		}

	}

	@Nonnull
	private static VimStatus createResult(final LcmOperationStateType state, final String message) {
		final VimStatus vimStatus = new VimStatus();
		vimStatus.setLcmOperationStateType(state);
		if (state == LcmOperationStateType.COMPLETED) {
			return vimStatus;
		}
		final FailureDetails failureDetails = new FailureDetails();
		failureDetails.setDetail(message);
		failureDetails.setStatus(500);
		vimStatus.setProblemDetails(failureDetails);
		return vimStatus;
	}

	private static String getDefault(final String orig, final String def) {
		if (null != orig) {
			return orig;
		}
		return def;
	}

	@Override
	public void allocateResources(final VimConnectionInformation vimConnectionInformation, final GrantInformation grantInformation) {
		//
	}

	@Override
	public void freeResources(final VimConnectionInformation vimConnectionInformation, final GrantInformation grantInformation) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return "MSA_20";
	}

	public VimFlavor getFlavors(final String name) {
		final OSClientV3 os = getOs();
		final List<? extends Flavor> flavors = os.compute().flavors().list();
		final Flavor flavor = flavors.stream()
				.filter(x -> x.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Flavor not found: " + name));
		return mapper.map(flavor, VimFlavor.class);
	}

	private static OSClientV3 getOs() {
		final Identifier domainIdentifier = Identifier.byName("Default");
		return OSFactory.builderV3()
				.endpoint("http://10.31.1.240:5000/v3")
				.credentials("admin", "84612d9a2e404ac9", domainIdentifier)
				.scopeToProject(Identifier.byId("df1f081bf2d345099e6bb53f6b9407ff"))
				.authenticate();
	}

	@Override
	public @NonNull VimImage getImagesInformations(final VimConnectionInformation vimConnectionInformation, final String name) {
		final OSClientV3 os = getOs();
		final List<? extends Image> images = os.compute().images().list();
		final Image image = images.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new NotFoundException("Image " + name + " Cannot be found on Vim."));
		return mapper.map(image, VimImage.class);
	}

	@Override
	public String createNetwork(final VimConnectionInformation vimConnectionInformation, final VlProtocolData vl, final String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refineExecutionPlan(final ListenableGraph<UnitOfWork, ConnectivityEdge> g) {
		// MSA don't have an execution plan.
	}

	@Override
	public Optional<SoftwareImage> getSwImageMatching(final VimConnectionInformation vimInfo, final SoftwareImage img) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public SoftwareImage uploadSoftwareImage(final VimConnectionInformation vimInfo, final SoftwareImage img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrCreateFlavor(final VimConnectionInformation vimConnectionInformation, final String name, final int numVcpu, final long virtualMemorySize, final long disk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage, final String aliasName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createObjectStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCompute(final VimConnectionInformation vimConnectionInformation, final String instanceName, final String flavorId, final String imageId, final List<String> networks, final List<String> storages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getZoneAvailableList(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCompute(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteVirtualLink(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObjectStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRouter(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String createRouter(final VimConnectionInformation vimConnectionInformation, final String name, final String internalNetworkId, final String externalNetworkId) {
		// TODO Auto-generated method stub
		return null;
	}

}
