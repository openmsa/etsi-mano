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
package com.ubiqube.etsi.mano.controller.vnf;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfPackageManagement {
	/**
	 * Only for read.
	 *
	 * @param <U>      The json class.
	 * @param vnfPkgId A Id.
	 * @param u        The json class.
	 * @return A Json instance.
	 */
	<U> U vnfPackagesVnfPkgIdGet(@Nonnull UUID vnfPkgId, Class<U> u);

	/**
	 * Only for write.
	 *
	 * @param vnfPkgId An Id.
	 * @return A VnfPackage instance.
	 */
	VnfPackage vnfPackagesVnfPkgIdGet(UUID vnfPkgId);

	List<VnfPackage> vnfPackagesGet(String filter);

	/**
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param rangeHeader
	 * @return
	 */
	ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull UUID vnfPkgId, @Nonnull String artifactPath, @Nullable String rangeHeader);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull UUID vnfPkgId, boolean includeSignature);

	ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull UUID vnfPkgId, @Nullable String range);

	ResponseEntity<Void> getPackageManifest(UUID vnfPkgId, String includeSignatures);

	ResponseEntity<List<ResourceRegion>> vnfPackagesVnfdIdArtifactsArtifactPathGet(UUID vnfPkgId, String artifactPath, String range);

	ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(UUID vnfdId, @Valid String includeSignatures);

	ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(UUID vnfdId, String range);

	ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(UUID vnfdId, @Valid String includeSignatures);

	<U> U onboardedVnfPackagesVnfdIdGet(UUID vnfdId, Class<U> clazz);

	<U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink);

	ResponseEntity<Resource> onboardedGetManifestByVnfd(UUID vnfdId, @Valid String includeSignature);

	ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsGet(UUID vnfdId, String range);

	<U> U vnfPackagesVnfPkgVnfdIdGet(UUID vnfPkgId, Class<U> clazz);

}