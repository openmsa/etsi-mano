package com.ubiqube.bean;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.api.ws.entities.orchestration.ProcessInstance;

public class OrchestrationRestTest {
	// private final String URL_BASE = "http://10.31.1.246/ubi-api-rest";
	private final String URL_BASE = "http://localhost:8380/ubi-api-rest";
	private final RestTemplate restTemplate = new RestTemplate();
	final HttpHeaders httpHeaders = new HttpHeaders();

	public OrchestrationRestTest() {
		httpHeaders.add("Authorization", "Basic bmNyb290OnViaXF1YmU=");
	}

	@org.junit.jupiter.api.Test
	public void testName01() throws Exception {
		final String ubiqubeId = "TMAA6";
		final String serviceId = "";
		final String serviceName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_HeatBAD";
		final String processName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final Map<String, String> vars = new HashMap<>();
		vars.put("ubiqubeId", ubiqubeId);
		vars.put("serviceId", serviceId);
		final URI uri = apiUriBuilder()
				.pathSegment("orchestration/service/execute/{ubiqubeId}/{serviceId}")
				.queryParam("serviceName", serviceName)
				.queryParam("processName", processName)
				.buildAndExpand(vars)
				.toUri();
		post(uri, "{}", ProcessInstance.class);
	}

	private <T> T get(URI uri, HttpMethod method, Class<T> clazz) {
		final HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

	private <T> T post(URI uri, Object body, Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
		final ResponseEntity<T> resp = restTemplate.exchange(uri, HttpMethod.POST, request, clazz);
		return resp.getBody();
	}

	private UriComponentsBuilder apiUriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(URL_BASE);
	}
}
