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
import javax.validation.Valid;

import org.springframework.core.io.Resource;
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
	ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull UUID vnfPkgId, @Nonnull String artifactPath);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull UUID vnfPkgId, boolean includeSignature);

	ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull UUID vnfPkgId);

	ResponseEntity<Void> getPackageManifest(@Nonnull UUID vnfPkgId, String includeSignatures);

	ResponseEntity<Resource> vnfPackagesVnfdIdArtifactsArtifactPathGet(@Nonnull UUID vnfPkgId, String artifactPath);

	ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(@Nonnull UUID vnfdId, String includeSignatures);

	ResponseEntity<Resource> onboardedVnfPackagesVnfdIdPackageContentGet(@Nonnull UUID vnfdId);

	ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(@Nonnull UUID vnfdId, String includeSignatures);

	<U> U onboardedVnfPackagesVnfdIdGet(@Nonnull UUID vnfdId, Class<U> clazz);

	<U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink);

	ResponseEntity<Resource> onboardedGetManifestByVnfd(@Nonnull UUID vnfdId, @Valid String includeSignature);

	ResponseEntity<Resource> onboardedVnfPackagesVnfdIdArtifactsGet(@Nonnull UUID vnfdId);

	<U> U vnfPackagesVnfPkgVnfdIdGet(@Nonnull UUID vnfPkgId, Class<U> clazz);

}