package com.ubiqube.json.patch;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		assertEquals("Operation state", "ENABLED", vnfPkgInfo.getOperationalState());

		assertEquals("id", "8a5878be-21c8-4123-a6a1-a9cea03123a0", vnfPkgInfo.getId());
	}

	private String readFile(String fileName) throws IOException {
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
}
