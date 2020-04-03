package com.ubiqube.etsi.mano.service.pkg;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;

public class ToscaPackageProviderTest {

	@Test
	void testName() throws Exception {
		final Path path = Paths.get("src/test/resources/ubi-tosca.csar");
		final byte[] data = Files.readAllBytes(path);
		final ToscaPackageProvider tpp = new ToscaPackageProvider(data);
		final Set<AdditionalArtifact> aa = tpp.getAdditionalArtefacts();
		System.out.println("" + aa);
	}
}
