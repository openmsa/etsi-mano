package com.ubiqube.etsi.mano.controller;

import java.io.IOException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;

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

	protected ObjectMapper mapper;

	@Inject
	public BaseApi(ObjectMapper _mapper) {
		super();
		mapper = _mapper;
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
		} catch (final IOException e) {
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
