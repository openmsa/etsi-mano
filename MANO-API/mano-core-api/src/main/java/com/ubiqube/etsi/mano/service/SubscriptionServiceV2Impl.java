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
package com.ubiqube.etsi.mano.service;

import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.event.Notifications;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SubscriptionServiceV2Impl implements SubscriptionServiceV2 {
	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	private final Notifications notifications;

	public SubscriptionServiceV2Impl(final SubscriptionService subscriptionService, final MapperFacade mapper, final Notifications notifications) {
		super();
		this.subscriptionService = subscriptionService;
		this.mapper = mapper;
		this.notifications = notifications;
	}

	@Override
	public <U> List<U> query(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> setLink, final SubscriptionType type) {
		final String filter = getSingleField(requestParams, "filter");
		final List<Subscription> list = subscriptionService.query(filter, type);
		final List<U> pkgms = mapper.mapAsList(list, clazz);
		pkgms.stream().forEach(setLink);
		return pkgms;
	}

	@Override
	public <U> U create(final Object subscriptionRequest, final Class<U> clazz, final Consumer<U> setLink, final SubscriptionType type) {
		Subscription subscription = mapper.map(subscriptionRequest, Subscription.class);
		notifications.check(subscription.getAuthentication(), subscription.getCallbackUri());
		subscription = subscriptionService.save(subscription, type);
		final U pkgmSubscription = mapper.map(subscription, clazz);
		setLink.accept(pkgmSubscription);
		return pkgmSubscription;
	}

	@Override
	public void deleteById(final UUID id, final SubscriptionType type) {
		subscriptionService.delete(id, type);
	}

	@Override
	public <U> U findById(final UUID id, final Class<U> clazz, final Consumer<U> setLink, final SubscriptionType type) {
		final Subscription subscription = subscriptionService.findById(id, type);
		final U pkgmSubscription = mapper.map(subscription, clazz);
		setLink.accept(pkgmSubscription);
		return pkgmSubscription;
	}

}
