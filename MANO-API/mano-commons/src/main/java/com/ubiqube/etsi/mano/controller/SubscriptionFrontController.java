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
package com.ubiqube.etsi.mano.controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface SubscriptionFrontController {
	<U> ResponseEntity<List<U>> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLinks, SubscriptionType type);

	<U> ResponseEntity<U> create(Object subscriptionRequest, Class<U> clazz, Consumer<U> makeLinks, Function<U, String> getSelfLink, SubscriptionType type);

	ResponseEntity<Void> deleteById(@NotNull String subscriptionId, SubscriptionType type);

	<U> ResponseEntity<U> findById(String subscriptionId, Class<U> clazz, Consumer<U> makeLinks, SubscriptionType type);

}
