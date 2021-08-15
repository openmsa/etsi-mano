/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.ApiVersionInformation;
import com.ubiqube.etsi.mano.model.ApiVersionInformationApiVersions;

/**
 * Duplicate of VnfmVersion.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
@RequestMapping(value = "/sol005")
@Lazy
public class NfvoApiVersion {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoApiVersion.class);

	private final MultiValueMap<String, String> dedupe = new LinkedMultiValueMap<>();

	private final ApplicationContext applicationContext;

	public NfvoApiVersion(final ApplicationContext _applicationContext) {
		applicationContext = _applicationContext;
		extractVersions();
	}

	private void extractVersions() {
		final String[] beans = applicationContext.getBeanNamesForAnnotation(Controller.class);
		final List<String> list = Arrays.asList(beans);
		final Map<String, Endpoint> res = new HashMap<>();
		list.stream().forEach(x -> {
			LOG.debug("Reading: {}", x);
			if ("nfvoApiVersion".equals(x) || "vnfmApiVersion".equals(x)) {
				return;
			}
			final Object obj = applicationContext.getBean(x);
			final RequestMapping req = AnnotationUtils.findAnnotation(obj.getClass(), RequestMapping.class);
			if ((null != req) && (req.headers() != null)) {
				final List<String> version = getVersion(req.headers());
				if ((version != null) && (req.value().length > 0)) {
					final String part = findMatch(req.value()[0]);
					version.forEach(y -> res.put(part + y, new Endpoint(part.replace("/", ""), y)));
				}
			}
		});

		res.entrySet().forEach(x -> dedupe.add(x.getValue().part, x.getValue().version));
	}

	private static String findMatch(final String url) {
		final Set<String> mutableSet = new HashSet<>(Arrays.asList("/vnfpkgm/", "/grant/", "/vnfpm/", "/vnflcm/", "/vnfind/", "/vnffm/", "/vrgan/", "/nsd/", "/nsfm/", "/nslcm/", "/nspm/", "/vnfpkgm/"));
		for (final String string : mutableSet) {
			if (url.contains(string)) {
				return string;
			}
		}
		return null;
	}

	private static List<String> getVersion(final String[] headers) {
		final List<String> res = new ArrayList<>();
		if (headers.length == 0) {
			return res;
		}
		for (final String string : headers) {
			if (string.startsWith("Version=")) {
				res.add(string.substring("version=".length()));
			}
		}
		return res;
	}

	class Endpoint {
		private final String part;
		private final String version;

		public Endpoint(final String _part, final String _version) {
			part = _part;
			version = _version;
		}

		@Override
		public String toString() {
			return "Endpoint [part=" + part + ", version=" + version + "]";
		}

	}

	@GetMapping(value = { "/{module}/v1/api_versions", "/{module}/api_versions" }, produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<ApiVersionInformation> apiMajorVersionsGet(@PathVariable("module") final String module) {
		final ApiVersionInformation apiVersion = new ApiVersionInformation();
		apiVersion.setUriPrefix("/sol005/" + module + "/v1/");

		final List<String> versions = dedupe.get(module);
		if (null == versions) {
			return ResponseEntity.noContent().build();
		}
		final List<ApiVersionInformationApiVersions> list = versions.stream().map(x -> new ApiVersionInformationApiVersions().version(x).isDeprecated(Boolean.FALSE)).collect(Collectors.toList());
		apiVersion.setApiVersions(list);
		return ResponseEntity.ok(apiVersion);
	}

}
