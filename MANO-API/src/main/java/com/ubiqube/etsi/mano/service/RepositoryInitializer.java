package com.ubiqube.etsi.mano.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;

/**
 * TODO: Could be removed once GenericBinaryRepository deployement have been
 * achived.
 * 
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@ConditionalOnBean(RepositoryService.class)
public class RepositoryInitializer {

	protected static final String NCROOT = "ncroot";
	protected static final String MANO = "MANO";
	protected static final String PROCESS_BASE_PATH = "Process";
	protected static final String PROCESS_NFVO_BASE_PATH = PROCESS_BASE_PATH + "/NFVO";
	protected static final String PROCESS_VNF_VNF_PCKGM_BASE_PATH = PROCESS_NFVO_BASE_PATH + "/VNF_PCKGM";
	protected static final String DATAFILE_BASE_PATH = "Datafiles";
	protected static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	protected static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";
	protected static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";
	protected static final String REPOSITORY_NSD_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/nsd";
	protected static final String REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/vnf_instances";
	protected static final String REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/ns_instances";
	protected static final String REPOSITORY_LCM_OP_OCCS_DATAFILE_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/lcm-op-occs";
	protected static final String REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/vnf-lcm-op-occs";
	protected static final String REPOSITORY_PNF_DATAFILE_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/pnfd";
	/** EJB Instance. */

	private final RepositoryService repositoryService;

	public RepositoryInitializer(final RepositoryService _repositoryService) throws ServiceException {
		repositoryService = _repositoryService;
		init();
	}

	/**
	 *
	 * @throws ServiceException
	 */
	private void init() throws ServiceException {
		if (!repositoryService.exists(PROCESS_BASE_PATH)) {
			repositoryService.addDirectory(PROCESS_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(PROCESS_NFVO_BASE_PATH)) {
			repositoryService.addDirectory(PROCESS_NFVO_BASE_PATH, "", MANO, NCROOT);
		}

		if (!repositoryService.exists(PROCESS_VNF_VNF_PCKGM_BASE_PATH)) {
			repositoryService.addDirectory(PROCESS_VNF_VNF_PCKGM_BASE_PATH, "", MANO, NCROOT);
		}

		if (!repositoryService.exists(DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(NVFO_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(NVFO_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_NVFO_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_NVFO_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_SUBSCRIPTION_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_SUBSCRIPTION_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_NSD_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_NSD_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_LCM_OP_OCCS_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_LCM_OP_OCCS_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
		if (!repositoryService.exists(REPOSITORY_PNF_DATAFILE_BASE_PATH)) {
			repositoryService.addDirectory(REPOSITORY_PNF_DATAFILE_BASE_PATH, "", MANO, NCROOT);
		}
	}

}
