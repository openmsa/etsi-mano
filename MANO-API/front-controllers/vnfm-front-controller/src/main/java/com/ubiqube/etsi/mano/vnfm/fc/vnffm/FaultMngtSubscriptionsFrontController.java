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
package com.ubiqube.etsi.mano.vnfm.fc.vnffm;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface FaultMngtSubscriptionsFrontController {

	<U> ResponseEntity<List<U>> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLink);

	<U> ResponseEntity<U> create(Object fmSubscriptionRequest, Class<U> clazz, Consumer<U> makeLink, Function<U, String> getSelfLink);

	ResponseEntity<Void> delete(String subscriptionId);

	<U> ResponseEntity<U> findById(String subscriptionId, Class<U> clazz, Consumer<U> makeLink);

}