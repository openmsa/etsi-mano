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

import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SubscriptionFrontControllerImpl implements SubscriptionFrontController {
	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	public SubscriptionFrontControllerImpl(final SubscriptionService subscriptionService, final MapperFacade mapper) {
		super();
		this.subscriptionService = subscriptionService;
		this.mapper = mapper;
	}

	@Override
	public <U> ResponseEntity<List<U>> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLinks, final SubscriptionType type) {
		final String filter = getSingleField(requestParams, "filter");
		final List<Subscription> list = subscriptionService.query(filter, type);
		final List<U> pkgms = mapper.mapAsList(list, clazz);
		pkgms.stream().forEach(makeLinks);
		return ResponseEntity.ok(pkgms);
	}

	@Override
	public <U> ResponseEntity<U> create(final Object subscriptionRequest, final Class<U> clazz, final Consumer<U> makeLinks, final Function<U, String> getSelfLink, final SubscriptionType type) {
		Subscription subscription = mapper.map(subscriptionRequest, Subscription.class);
		subscription = subscriptionService.save(subscription, type);
		final U res = mapper.map(subscription, clazz);
		makeLinks.accept(res);
		final String link = getSelfLink.apply(res);
		final URI location = URI.create(link);
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> deleteById(@NotNull final String subscriptionId, final SubscriptionType type) {
		subscriptionService.delete(UUID.fromString(subscriptionId), type);
		return ResponseEntity.noContent().build();
	}

	@Override
	public <U> ResponseEntity<U> findById(final String subscriptionId, final Class<U> clazz, final Consumer<U> makeLinks, final SubscriptionType type) {
		final Subscription subscription = subscriptionService.findById(UUID.fromString(subscriptionId), type);
		final U res = mapper.map(subscription, clazz);
		makeLinks.accept(res);
		return ResponseEntity.ok(res);
	}

}
