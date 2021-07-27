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
package com.ubiqube.etsi.mano.controller.policy;

import java.io.InputStream;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.policy.Policies;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface PolicyController {

	void deleteById(UUID safeUUID);

	void deleteByIdAndVersion(UUID safeUUID, String version);

	Policies findById(UUID uuid);

	byte[] getContentByPolicyIdAndVersion(UUID safeUUID, String version);

	byte[] getContentBySelectedVersion(UUID safeUUID);

	void putContent(UUID safeUUID, String version, InputStream is);

	Policies create(Policies p);

	Policies modify(UUID safeUUID, PolicyPatchDto patch);

	<U> ResponseEntity<String> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLinks);

}
