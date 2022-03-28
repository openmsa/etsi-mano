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

import java.util.UUID;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface OnboardedPackageFrontController {
	ResponseEntity<Resource> onboardedGetContentByVnfdId(String vnfdId, String accept, String includeSignature);

	<U> ResponseEntity<String> onboardedSearch(MultiValueMap<String, String> requestParams, Class<U> class1, Consumer<U> makeLinks);

	ResponseEntity<Resource> onboardedGetArtifact(HttpServletRequest request, UUID safeUUID, @Valid String includeSignature);

	ResponseEntity<Resource> onboardedGetVnfdByVnfdId(UUID safeUUID, String accept);

	<U> ResponseEntity<U> onboardedFindById(UUID safeUUID, Class<U> class1, Consumer<U> makeLinks);

	ResponseEntity<Resource> onboardedGetManifestByVnfd(UUID fromString, @Valid String includeSignature);

	ResponseEntity<Resource> onboardedGetVnfdByVnfdId(String vnfdId, @Valid String includeSignature);

	ResponseEntity<Resource> onboardedGetArtifactByVnfdId(UUID safeUUID);

}
