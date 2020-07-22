package com.ubiqube.etsi.mano.service.pkg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.tosca.ToscaPackageProvider;

public class ToscaNsdTest {
	private final ToscaPackageProvider tpp;

	public ToscaNsdTest() throws IOException {
		final Path path = Paths.get("src/test/resources/ubi-nsd-tosca.csar");
		final byte[] data = Files.readAllBytes(path);
		tpp = new ToscaPackageProvider(data);
	}

	@Test
	void testNsInformations() throws Exception {
		final NsInformations list = tpp.getNsInformations(new HashMap<String, String>());
		assertEquals("flavor01", list.getFlavorId());
		assertEquals("demo", list.getInstantiationLevel());
		assertEquals(1, list.getMinNumberOfInstance());
		assertEquals(1, list.getMaxNumberOfInstance());
		assertEquals("ovi@ubiqube.com", list.getNsdDesigner());
		assertEquals("65f6fbed-654b-4d68-b730-5d8d72a8b865", list.getNsdInvariantId());
		assertEquals("ns01", list.getNsdName());
		assertEquals("0.0.1", list.getNsdVersion());
	}

	@Test
	void testNsVirtualLink() throws Exception {
		final Set<NsVirtualLink> list = tpp.getNsVirtualLink(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testNsSap() throws Exception {
		final Set<NsSap> list = tpp.getNsSap(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testSecurityGroupAdapter() throws Exception {
		final Set<SecurityGroupAdapter> list = tpp.getSecurityGroups(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testUbiqube01() throws Exception {
		final Set<String> list = tpp.getVnfd(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testUbiqube02() throws Exception {
		final Set<String> list = tpp.getNestedNsd(new HashMap<String, String>());
		assertEquals(1, list.size());
	}
}
