package com.ubiqube.etsi.mano.service;

import java.nio.file.Path;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.repository.ClassPathConverter;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

/**
 * Initialize folder.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 */
@Service
@ConditionalOnBean(Low.class)
public class RepositoryInitializer {

	protected static final String PROCESS_BASE_PATH = "Process";
	protected static final String DATAFILE_BASE_PATH = "Datafiles";
	protected static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	/** EJB Instance. */

	private final Low lowDriver;

	private final ClassPathConverter cpConverter = new ClassPathConverter();

	private final NamingStrategy namingStrategy;

	public RepositoryInitializer(final Low _repositoryService, final NamingStrategy _namingStrategy) {
		lowDriver = _repositoryService;
		namingStrategy = _namingStrategy;
		init();
	}

	/**
	 * Init.
	 */
	private void init() {
		if (!lowDriver.exist(PROCESS_BASE_PATH)) {
			lowDriver.mkdir(PROCESS_BASE_PATH);
		}
		if (!lowDriver.exist(NVFO_DATAFILE_BASE_PATH)) {
			lowDriver.mkdir(NVFO_DATAFILE_BASE_PATH);
		}
		final Set<Class<?>> set = cpConverter.getList();
		set.stream().forEach(x -> {
			final Path root = namingStrategy.getRoot(x);
			lowDriver.mkdir(root.toString());
		});
	}
}
