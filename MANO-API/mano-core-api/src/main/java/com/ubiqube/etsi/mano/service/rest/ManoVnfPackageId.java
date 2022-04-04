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
package com.ubiqube.etsi.mano.service.rest;

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author ncuser
 *
 */
public class ManoVnfPackageId {

	private static final String PACKAGE_CONTENT = "/vnf_packages/{id}/package_content";

	private static final Logger LOG = LoggerFactory.getLogger(ManoVnfPackageId.class);

	private final ManoClient client;

	public ManoVnfPackageId(final ManoClient manoClient, final UUID id) {
		this.client = manoClient;
		client.setObjectId(id);
		client.setQueryType(ApiVersionType.SOL003_VNFPKGM);
		client.setFragment("/vnf_packages/{id}");
	}

	public VnfPackage find() {
		return client.createQuery()
				.setWireOutClass(HttpGateway::getVnfPackageClass)
				.setOutClass(VnfPackage.class)
				.getSingle();
	}

	public void downloadContent(final Path file) {
		client.setFragment(PACKAGE_CONTENT);
		client.createQuery()
				.download(file);
	}

	public void delete() {
		client.createQuery()
				.delete();
	}

	public void onboard(final Path path, final String accept) {
		client.setFragment(PACKAGE_CONTENT);
		client.createQuery().upload(path, accept);
	}

	public VnfPackage waitOnboading() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException e) {
				LOG.warn("Interrupted!", e);
				Thread.currentThread().interrupt();
			}
			final VnfPackage pkg = find();
			final OnboardingStateType state = pkg.getOnboardingState();
			LOG.debug("state {}", state);
			if (state == OnboardingStateType.ONBOARDED || state == OnboardingStateType.ERROR) {
				return pkg;
			}
		}
	}

	public VnfPackage patch(final String ifMatch, final Map<String, Object> patch) {
		return client.createQuery()
				.setWireOutClass(HttpGateway::getVnfPackageClass)
				.setOutClass(VnfPackage.class)
				.patch(ifMatch, patch);
	}

}
