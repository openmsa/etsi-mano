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
package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.function.Consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface NsLcmGenericFrontController {

	<U> ResponseEntity<U> fail(String nsLcmOpOccId, Class<U> clazz, Consumer<U> makeLinks);

	ResponseEntity<Void> cancel(String nsLcmOpOccId, String string);

	<U> ResponseEntity<String> search(MultiValueMap<String, String> requestParams, Class<U> clazz, String nextpageOpaqueMarker, Consumer<U> makeLinks);

	ResponseEntity<Void> continu(String nsLcmOpOccId);

	<U> ResponseEntity<U> findById(String nsLcmOpOccId, Class<U> clazz, Consumer<U> makeLinks);

	ResponseEntity<Void> retry(String nsLcmOpOccId);

	ResponseEntity<Void> rollback(String nsLcmOpOccId);

}