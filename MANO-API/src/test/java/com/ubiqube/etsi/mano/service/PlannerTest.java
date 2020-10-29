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
package com.ubiqube.etsi.mano.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.service.pkg.tosca.ToscaPackageProvider;

@ExtendWith(MockitoExtension.class)
public class PlannerTest {
	@Mock
	VnfInstancesRepository vnfInstancesRepository = Mockito.mock(VnfInstancesRepository.class);

	@Test
	void testName() throws Exception {
		final byte[] data = Files.readAllBytes(Paths.get("src/test/resources/ubi-tosca.csar"));
		final ToscaPackageProvider toscaPackageProvider = new ToscaPackageProvider(data);
		final VnfPackage vnfPackage = new VnfPackage();
		vnfPackage.setId(UUID.randomUUID());
		final Set<VnfCompute> computes = toscaPackageProvider.getVnfComputeNodes(new HashMap<String, String>());
		vnfPackage.setVnfCompute(computes);
		final Set<VnfLinkPort> vnfLinkPort = toscaPackageProvider.getVnfVduCp(new HashMap<String, String>());
		vnfPackage.setVnfLinkPort(vnfLinkPort);
		final Set<VnfStorage> vnfStorage = toscaPackageProvider.getVnfStorages(new HashMap<String, String>());
		vnfPackage.setVnfStorage(vnfStorage);
		final Set<VnfVl> vnfVl = toscaPackageProvider.getVnfVirtualLinks(new HashMap<String, String>());
		vnfPackage.setVnfVl(vnfVl);

		// final ExecutionPlanner executionPlanner = new
		// ExecutionPlanner(vnfInstancesRepository);

		// executionPlanner.plan(vnfPackage, "12");
	}
}
