package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.etsi.mano.config.Http403EntryPoint;
import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.EventManager;
import com.ubiqube.etsi.mano.service.ManufacturerModel;
import com.ubiqube.etsi.mano.service.Patcher;

@AutoConfigureMockMvc
@WebMvcTest(VnfPackageSol005Api.class)
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

	@Test
	void testName() throws Exception {

		final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sol005/vnfpkgm/v1/vnf_packages")
				.with(user("ncroot"))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		final String resultDOW = result.getResponse().getContentAsString();
		System.out.println(resultDOW);
	}
}
