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
package com.ubiqube.etsi.mano.service.event.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 * A kind of VNF rollback. We need to kepp track of the state while uploading.
 * This service will switch back the VNF state on failure.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class UriUploadListener implements JobListener {

	private static final Logger LOG = LoggerFactory.getLogger(UriUploadListener.class);
	private final VnfPackageRepository vnfPackageRepository;

	public UriUploadListener(final VnfPackageRepository vnfPackageRepository) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@Override
	public String getName() {
		return "VNF Package URI Uploader Listener.";
	}

	@Override
	public void jobToBeExecuted(final JobExecutionContext context) {
		LOG.info("Job jobToBeExecuted.");

	}

	@Override
	public void jobExecutionVetoed(final JobExecutionContext context) {
		LOG.info("Job Vetoed.");
	}

	@Override
	public void jobWasExecuted(final JobExecutionContext context, final JobExecutionException jobException) {
		if (null == jobException || jobException.getMessage().isEmpty()) {
			return;
		}
		LOG.info("Rollback successfull.");
	}

}
