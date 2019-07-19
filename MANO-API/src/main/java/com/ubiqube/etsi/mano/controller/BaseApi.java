package com.ubiqube.etsi.mano.controller;

/**
 * Common part to all ETSI MANO API.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
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

}
