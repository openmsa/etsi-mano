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
/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.fc.vnfpm;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface VnfmPmGenericFrontController {

	<U> ResponseEntity<String> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLink);

	ResponseEntity<Void> deleteById(UUID pmJobId);

	<U> ResponseEntity<U> findById(UUID pmJobIdn, Class<U> clazz, Consumer<U> makeLinks);

	<U> ResponseEntity<U> findReportById(String pmJobId, String reportId, Class<U> clazz);

	<U> ResponseEntity<U> pmJobsPost(Object createPmJobRequest, Class<U> clazz, Consumer<U> makeLinks, Function<U, String> getSelfLink);

	<U> ResponseEntity<U> pmJobsPmJobIdPatch(UUID pmJobId, Object pmJobModifications);

}