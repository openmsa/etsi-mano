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
package com.ubiqube.etsi.mano.controller.vnffm;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class FaultMngtSubscriptionsFrontControllerImpl implements FaultMngtSubscriptionsFrontController {
	private final SubscriptionServiceV2 subscriptionService;

	public FaultMngtSubscriptionsFrontControllerImpl(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public <U> ResponseEntity<List<U>> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLink) {
		final List<U> res = subscriptionService.query(requestParams, clazz, makeLink, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	@Override
	public <U> ResponseEntity<U> create(final Object fmSubscriptionRequest, final Class<U> clazz, final Consumer<U> makeLink, final Function<U, String> getSelfLink) {
		final U res = subscriptionService.create(fmSubscriptionRequest, clazz, makeLink, SubscriptionType.ALARM);
		final String link = getSelfLink.apply(res);
		return ResponseEntity.created(URI.create(link)).body(res);
	}

	@Override
	public ResponseEntity<Void> delete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.ALARM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public <U> ResponseEntity<U> findById(final String subscriptionId, final Class<U> clazz, final Consumer<U> makeLink) {
		final U res = subscriptionService.findById(UUID.fromString(subscriptionId), clazz, makeLink, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}
}