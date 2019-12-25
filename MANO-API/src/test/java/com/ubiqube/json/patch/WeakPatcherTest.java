package com.ubiqube.json.patch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.WeakPatcher;

public class WeakPatcherTest {
	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test01() throws IOException {
		final Patcher patcher = new WeakPatcher();
		final String patchStr = readFile("src/test/resources/VnfPkgInfoModifications.json");

		final String vnfPkgInfoStr = readFile("src/test/resources/VnfPkgInfo.json");
		final VnfPkgInfo vnfPkgInfo = mapper.readValue(vnfPkgInfoStr, VnfPkgInfo.class);
		patcher.patch(patchStr, vnfPkgInfo);

		assertEquals("Encryption algorithm", "SHA256", vnfPkgInfo.getChecksum().getAlgorithm());
		assertEquals("VnfId", "1234-1234-1234", vnfPkgInfo.getVnfdId());
		assertEquals("Operation state", PackageOperationalStateType.DISABLED, vnfPkgInfo.getOperationalState());

		assertEquals("id", "8a5878be-21c8-4123-a6a1-a9cea03123a0", vnfPkgInfo.getId());
	}

	private String readFile(final String fileName) throws IOException {
		final InputStream is = new FileInputStream(fileName);
		final BufferedReader buf = new BufferedReader(new InputStreamReader(is));

		String line = buf.readLine();
		final StringBuilder sb = new StringBuilder();

		while (line != null) {
			sb.append(line).append("\n");
			line = buf.readLine();
		}

		return sb.toString();
	}

	@Test
	void testEnum() throws Exception {
		final Class<?> beanClass = VnfPkgInfo.class;
		final BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		final PropertyDescriptor[] lpd = beanInfo.getPropertyDescriptors();
		for (final PropertyDescriptor propertyDescriptor : lpd) {
			if (propertyDescriptor.getName().equals("operationalState")) {
				assertNotNull(propertyDescriptor.getWriteMethod());
			}
		}
	}

	@Test
	void testVnfPkgEnum() throws Exception {
		final Patcher patcher = new WeakPatcher();
		final String patchStr = readFile("src/test/resources/VnfPkgInfoModifications2.json");
		final String vnfPkgInfoStr = readFile("src/test/resources/VnfPkgInfo.json");
		final VnfPkgInfo vnfPkgInfo = mapper.readValue(vnfPkgInfoStr, VnfPkgInfo.class);
		patcher.patch(patchStr, vnfPkgInfo);
		System.out.println("" + vnfPkgInfo);
	}

	@Test
	void testNsdModifiaction() throws Exception {
		final Patcher patcher = new WeakPatcher();
		final String patchStr = readFile("src/test/resources/NsdModification.json");
		final String nsdPkgInfoStr = readFile("src/test/resources/NsdPkgInfo.json");
		final NsdInfo nsdPkgInfo = mapper.readValue(nsdPkgInfoStr, NsdInfo.class);
		patcher.patch(patchStr, nsdPkgInfo);
	}
}
