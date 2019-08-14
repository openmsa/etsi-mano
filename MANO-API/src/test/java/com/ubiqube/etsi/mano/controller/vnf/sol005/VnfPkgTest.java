package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.etsi.mano.config.Http403EntryPoint;
import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.EventManager;
import com.ubiqube.etsi.mano.service.ManufacturerModel;
import com.ubiqube.etsi.mano.service.Patcher;

@AutoConfigureMockMvc
@WebMvcTest
//@ComponentScan("com.ubiqube.etsi.mano")
@ContextConfiguration(classes = { Http403EntryPoint.class, VnfPackageSol005Api.class })
public class VnfPkgTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private VnfManagement vnfManagement;
	@MockBean
	private Patcher patcher;
	@MockBean
	private VnfPackageRepository vnfPackageRepository;
	@MockBean
	private ManufacturerModel manufacturerModel;
	@MockBean
	private DeviceService deviseService;
	@MockBean
	private EventManager eventManager;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testVnfPackagePost() throws Exception {
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "vnf-pkg-post.json"));
		final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sol005/vnfpkgm/v1/vnf_packages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(value)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf())
				.with(user("ooo")))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is2xxSuccessful())
				.andReturn();

		final String resultServ = result.getResponse().getContentAsString();

		verify(vnfPackageRepository).save(Mockito.any(VnfPkgInfo.class));
		verify(eventManager).sendEvent(Mockito.any(), Mockito.anyString());
	}

	@Test
	void testVnfPackageDeleteNonDisabled() throws Exception {
		final String vnfPkgId = "aaa";
		final VnfPkgInfo value = objectMapper.readValue(new File("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		when(vnfPackageRepository.get(vnfPkgId)).thenReturn(value);

		final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/sol005/vnfpkgm/v1/vnf_packages/{vnfPkgId}", vnfPkgId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf())
				.with(user("ooo")))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(409))
				.andReturn();

		final String resultServ = result.getResponse().getContentAsString();
	}

	@Test
	void testVnfPackageDeleteDisabled() throws Exception {
		final String vnfPkgId = "aaa";
		final VnfPkgInfo value = objectMapper.readValue(new File("src/test/resources/VnfPkgInfo-disabled.json"), VnfPkgInfo.class);
		when(vnfPackageRepository.get(vnfPkgId)).thenReturn(value);

		final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/sol005/vnfpkgm/v1/vnf_packages/{vnfPkgId}", vnfPkgId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf())
				.with(user("ooo")))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(204))
				.andReturn();

	}
}
