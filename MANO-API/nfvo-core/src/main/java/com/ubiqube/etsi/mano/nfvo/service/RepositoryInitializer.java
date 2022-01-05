/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.service;

import java.nio.file.Path;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.ClassPathConverter;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

/**
 * Initialize folder.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 */
@Service
public class RepositoryInitializer {

	protected static final String PROCESS_BASE_PATH = "Process";
	protected static final String DATAFILE_BASE_PATH = "Datafiles";
	protected static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	/** EJB Instance. */

	private final Low lowDriver;

	private static final ClassPathConverter CP_CONVERTER = new ClassPathConverter();

	private final NamingStrategy namingStrategy;

	public RepositoryInitializer(final Low repositoryService, final NamingStrategy namingStrategy) {
		this.lowDriver = repositoryService;
		this.namingStrategy = namingStrategy;
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
		final Set<Class<?>> set = CP_CONVERTER.getList();
		set.stream().forEach(x -> {
			final Path root = namingStrategy.getRoot(x);
			lowDriver.mkdir(root.toString());
		});
	}
}
