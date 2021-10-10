package com.ubiqube.etsi.mano.vnfm.service;

import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.VnfmGateway;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;
import com.ubiqube.etsi.mano.vnfm.service.rest.NfvoRestImpl;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfmVersionManager {
	private final List<VnfmGateway> vnfmGateway;
	private final NfvoRest vnfmRest;
	private final MapperFacade mapper;

	public VnfmVersionManager(final List<VnfmGateway> vnfmGateway, final NfvoRestImpl vnfmRest, final MapperFacade mapper) {
		super();
		this.vnfmGateway = vnfmGateway;
		this.vnfmRest = vnfmRest;
		this.mapper = mapper;
		if (vnfmGateway.isEmpty()) {
			throw new GenericException("No VNFM gateway found. At leat onr gateway is needed.");
		}
	}

	public VnfPackage findVnfPkgById(final String pkgId) {
		final Class<?> clazz = vnfmGateway.get(0).getVnfPackageClass();
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = vnfmRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		final Object res = vnfmRest.get(uri, clazz);
		return mapper.map(res, VnfPackage.class);
	}

	public void getPackageContent(final String pkgId, final Path file) {
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = vnfmRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{id}/package_content")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.get(uri, clientHttpResponse -> StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(file.toFile())));
	}

}
