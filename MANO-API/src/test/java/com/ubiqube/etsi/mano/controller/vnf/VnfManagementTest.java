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
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnf.Sol003Linkable;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaAutoConfiguration;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { VnfManagement.class, OrikaConfiguration.class, OrikaAutoConfiguration.class })
@ExtendWith(SpringExtension.class)
public class VnfManagementTest {
	@MockBean
	private VnfPackageRepository vnfPackageRepository;
	private final ObjectMapper mapper = new ObjectMapper();
	private final DefaultMapperFactory mapperFactory;

	public VnfManagementTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testNullParameter() throws Exception {
		final List<VnfPackage> vnfPkgInfos = new ArrayList<>();
		when(vnfPackageRepository.query(null)).thenReturn(vnfPkgInfos);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);

		final Map<String, String> queryParameters = new HashMap<>();
		final String res = vnfPManagement.vnfPackagesGet(queryParameters, new Sol003Linkable());
		assertEquals("[ ]", res);
	}

	@Test
	void testOneTupple() throws Exception {
		final List<VnfPackage> vnfPkgInfos = new ArrayList<>();
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		vnfPkgInfos.add(VnfPackageFactory.createVnfPkgInfo(new HashMap<String, String>()));
		when(vnfPackageRepository.query(null)).thenReturn(vnfPkgInfos);
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);

		final Map<String, String> queryParameters = new HashMap<>();
		final String res = vnfPManagement.vnfPackagesGet(queryParameters, new Sol003Linkable());
		System.out.println(">>>" + res + "<<<");
		final List<VnfPkgInfo> value = mapper.readValue(res, new TypeReference<List<VnfPkgInfo>>() {
		});
		assertEquals(1, value.size(), "List size should be 1");
		final VnfPkgInfo vnf = value.get(0);
		assertNotNull(vnf);
		assertEquals(PackageOnboardingStateType.CREATED, vnf.getOnboardingState());
		assertEquals(PackageOperationalStateType.DISABLED, vnf.getOperationalState());
		assertEquals(PackageUsageStateType.NOT_IN_USE, vnf.getUsageState());
	}

	@Test
	void testgetVnfUniq() throws Exception {
		final VnfPackage value = VnfPackageFactory.createVnfPkgInfo(new HashMap<String, String>());
		when(vnfPackageRepository.get(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"))).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final VnfPkgInfo res = vnfPManagement.vnfPackagesVnfPkgIdGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), new Sol003Linkable());
		assertNotNull(res);
		// assertEquals("/79afa4e8-3f76-4239-9175-309c12a06b6e",
		// res.getLinks().getSelf().getHref());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetJson() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "VnfPkgInfo.json"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		assertThrows(NotFoundException.class, () -> {
			vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "artifactPath", null);
		});
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZip404() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		assertThrows(NotFoundException.class, () -> {
			vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "artifactPath", null);
		});
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOk() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "nsd.json", null);

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final List<ResourceRegion> isr = res.getBody();
		assertEquals(1, isr.size());
		final ResourceRegion r1 = isr.get(0);
		assertEquals(4977, r1.getCount());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOkRanged() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "nsd.json", "bytes=200-1000");

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final List<ResourceRegion> isr = res.getBody();

		assertEquals(1, isr.size());
		final ResourceRegion r1 = isr.get(0);
		assertEquals(801, r1.getCount());
	}

	@Test
	void testvnfPackagesVnfPkgIdArtifactsArtifactPathGetZipOkRangedStar() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "nsd.json", "bytes=200-");

		assertTrue(res.getStatusCode().is2xxSuccessful());
		final List<ResourceRegion> isr = res.getBody();

		assertEquals(1, isr.size());
		final ResourceRegion r1 = isr.get(0);
		assertEquals(4777, r1.getCount());
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetJson() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "VnfPkgInfo.json"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetZip() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdVnfdGetText() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "hello.txt"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<Resource> res = vnfPManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "");
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/octet-stream", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGet() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), null);
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/zip", res.getHeaders().get("Content-Type").get(0));
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGetRangeOk() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "bytes=200-1000");
		assertEquals(1, res.getBody().size());
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/octet-stream", res.getHeaders().get("Content-Type").get(0));
		final List<ResourceRegion> isr = res.getBody();
		// This is the size of the returned object from getBinary.
		final ResourceRegion r1 = isr.get(0);
		assertEquals(801, r1.getCount());
	}

	@Test
	void testvnfPackagesVnfPkgIdPackageContentGetRangePartial() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "pack.zip"));
		when(vnfPackageRepository.getBinary(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "vnfd")).thenReturn(value);
		final MapperFacade mapperOrika = mapperFactory.getMapperFacade();
		final VnfPackageManagement vnfPManagement = new VnfManagement(vnfPackageRepository, mapperOrika);
		final ResponseEntity<List<ResourceRegion>> res = vnfPManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString("79afa4e8-3f76-4239-9175-309c12a06b6e"), "bytes=200-");
		assertEquals(1, res.getBody().size());
		assertTrue(res.getStatusCode().is2xxSuccessful());
		assertEquals("application/octet-stream", res.getHeaders().get("Content-Type").get(0));
		final List<ResourceRegion> isr = res.getBody();
		// This is the size of the returned object from getBinary.
		final ResourceRegion r1 = isr.get(0);
		assertEquals(3406, r1.getCount());
	}
}
