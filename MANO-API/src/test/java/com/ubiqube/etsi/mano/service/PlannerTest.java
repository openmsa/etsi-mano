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
import com.ubiqube.etsi.mano.service.pkg.ToscaPackageProvider;

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
