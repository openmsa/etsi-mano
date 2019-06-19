package com.ubiqube.etsi.mano.controller;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;

public class BaseApi {
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

	// Should be injected.

	protected final Patcher patcher;

	protected ObjectMapper mapper;
	protected SubscriptionRepository subscriptionRepository;
	protected VnfPackageRepository vnfPackageRepository;
	protected RepositoryService repositoryService;

	@Inject
	public BaseApi(Patcher _patcher, ObjectMapper _mapper, SubscriptionRepository _subscriptionRepository, VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService) {
		super();
		patcher = _patcher;
		mapper = _mapper;
		subscriptionRepository = _subscriptionRepository;
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
	}

	/**
	 * MSA related stuff.
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

	}

	/**
	 * Simple wrapper for removing Exceptions, and make sure that we serialize using
	 * correst latest.
	 *
	 * @param <T>
	 * @param input
	 * @param clazz
	 * @return
	 */
	protected <T> T string2Object(String input, Class<T> clazz) {
		try {
			return mapper.readValue(input, clazz);
		} catch (final Exception e) {
			throw new GenericException(e);
		}
	}

	protected <T> String object2String(T obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Prevent directory traversal.
	 *
	 * @param fileName
	 * @return
	 */
	protected String sanitize(String fileName) {
		return fileName.replaceAll("\\.\\.", "");
	}
}
