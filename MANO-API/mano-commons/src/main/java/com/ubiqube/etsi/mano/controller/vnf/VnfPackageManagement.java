package com.ubiqube.etsi.mano.controller.vnf;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;

public interface VnfPackageManagement {

	<U> U vnfPackagesVnfPkgIdGet(@Nonnull UUID vnfPkgId, Class<U> u);

	String vnfPackagesGet(@Nonnull Map<String, String> queryParameters);

	/**
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param rangeHeader
	 * @return
	 */
	ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull UUID vnfPkgId, @Nonnull String artifactPath, @Nullable String rangeHeader);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull UUID vnfPkgId, @Nullable String accept);

	ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull UUID _vnfPkgId, @Nullable String range);

}