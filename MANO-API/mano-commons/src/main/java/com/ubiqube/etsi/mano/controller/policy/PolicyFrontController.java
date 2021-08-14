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

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface PolicyFrontController {

	<U> ResponseEntity<String> search(MultiValueMap<String, String> requestParams, String nextpageOpaqueMarker, Class<U> clazz, Consumer<U> makeLinks);

	ResponseEntity<Void> deleteById(UUID safeUUID);

	<U> ResponseEntity<U> findById(String uuid, Class<U> clazz, Consumer<U> makeLinks);

	ResponseEntity<Void> deleteByVersion(String policyId, String version);

	ResponseEntity<byte[]> getContentByPolicyIdAndVersion(String policyId, String version);

	ResponseEntity<byte[]> getContentBySelectedVersion(String policyId);

	ResponseEntity<Void> putContent(String policyId, String version, InputStreamSource file);

	<U> ResponseEntity<U> create(Object body, Class<U> clazz, Consumer<U> makeLinks);

	<U, V> ResponseEntity<V> modify(String policyId, V body, Class<U> clazz, Consumer<U> makeLinks);

}