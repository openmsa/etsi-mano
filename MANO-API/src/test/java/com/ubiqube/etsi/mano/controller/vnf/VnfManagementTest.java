package com.ubiqube.etsi.mano.controller.vnf;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.vnf.sol003.Sol003Linkable;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.utils.RangeHeader;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { VnfManagement.class })
@ExtendWith(SpringExtension.class)
public class VnfManagementTest {
	@MockBean
	private VnfPackageRepository vnfPackageRepository;
	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void testNullParameter() throws Exception {
		final List<VnfPkgInfo> vnfPkgInfos = new ArrayList<>();
		when(vnfPackageRepository.query(null)).thenReturn(vnfPkgInfos);
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);

		final Map<String, String> queryParameters = new HashMap<>();
		final String res = vnfPManagement.vnfPackagesGet(queryParameters, new Sol003Linkable());
		assertEquals("[ ]", res);
	}

	@Test
	void testOneTupple() throws Exception {
		final List<VnfPkgInfo> vnfPkgInfos = new ArrayList<>();
		vnfPkgInfos.add(VnfPackageFactory.createVnfPkgInfo(new HashMap<String, Object>()));
		when(vnfPackageRepository.query(null)).thenReturn(vnfPkgInfos);
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);

		final Map<String, String> queryParameters = new HashMap<>();
		final String res = vnfPManagement.vnfPackagesGet(queryParameters, new Sol003Linkable());
		System.out.println(">>>" + res + "<<<");
		final List<VnfPkgInfo> value = mapper.readValue(res, new TypeReference<List<VnfPkgInfo>>() {
		});
		assertEquals(1, value.size(), "List size should be 1");
		final VnfPkgInfo vnf = value.get(0);
		assertNotNull(vnf);
		assertEquals("aaa", vnf.getId(), "Id should be 'aaa'");
		assertEquals("/aaa", vnf.getLinks().getSelf().getHref());
		assertEquals("CREATED", vnf.getOnboardingState());
		assertEquals("DISABLED", vnf.getOperationalState().value());
		assertEquals("NOT_IN_USE", vnf.getUsageState());
	}

	@Test
	void testgetVnfUniq() throws Exception {
		final VnfPkgInfo value = VnfPackageFactory.createVnfPkgInfo(new HashMap<String, Object>());
		when(vnfPackageRepository.get("aaa")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final VnfPkgInfo res = vnfPManagement.vnfPackagesVnfPkgIdGet("aaa", new Sol003Linkable());
		assertNotNull(res);
		assertEquals("/aaa", res.getLinks().getSelf().getHref());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetJson() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "VnfPkgInfo.json"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		assertThrows(NotFoundException.class, () -> {
			vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet("aaa", "artifactPath", null);
		});
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZip404() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		assertThrows(NotFoundException.class, () -> {
			vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet("aaa", "artifactPath", null);
		});
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOk() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet("aaa", "nsd.json", null);

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final InputStreamResource isr = (InputStreamResource) res.getBody();

		assertEquals(4977, isr.contentLength());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOkRanged() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final RangeHeader rangeHeader = RangeHeader.fromValue("Range: bytes=200-1000");
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet("aaa", "nsd.json", rangeHeader);

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final InputStreamResource isr = (InputStreamResource) res.getBody();

		assertEquals(800, isr.contentLength());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOkRangedStar() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final RangeHeader rangeHeader = RangeHeader.fromValue("Range: bytes=200-");
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet("aaa", "nsd.json", rangeHeader);

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final InputStreamResource isr = (InputStreamResource) res.getBody();

		assertEquals(4777, isr.contentLength());
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetJson() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "VnfPkgInfo.json"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet("aaa", "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetZip() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet("aaa", "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetText() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "hello.txt"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet("aaa", "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/octet-stream", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGet() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd")).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet("aaa", null);
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGetRangeOk() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd", 200, Integer.decode("1000"))).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet("aaa", RangeHeader.fromValue("Range: bytes=200-1000"));
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
		final InputStreamResource isr = (InputStreamResource) res.getBody();
		// This is the size of the returned object from getBinary.
		assertEquals(3606, isr.contentLength());
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGetRangePartial() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary("aaa", "vnfd", 200, null)).thenReturn(value);

		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet("aaa", RangeHeader.fromValue("Range: bytes=200-"));
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
		final InputStreamResource isr = (InputStreamResource) res.getBody();
		// This is the size of the returned object from getBinary.
		assertEquals(3606, isr.contentLength());
	}
}
