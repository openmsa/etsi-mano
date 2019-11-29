package com.ubiqube.etsi.mano.controller.vnf;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.utils.RangeHeader;

public interface VnfPackageManagement {

	VnfPkgInfo vnfPackagesVnfPkgIdGet(@Nonnull String vnfPkgId, @Nonnull Linkable links);

	String vnfPackagesGet(@Nonnull Map<String, String> queryParameters, @Nonnull Linkable links);

	/**
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param rangeHeader
	 * @return
	 */
	ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull String vnfPkgId, @Nonnull String artifactPath, @Nullable RangeHeader rangeHeader);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull String vnfPkgId, @Nullable String accept);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull String _vnfPkgId, @Nullable RangeHeader range);

}