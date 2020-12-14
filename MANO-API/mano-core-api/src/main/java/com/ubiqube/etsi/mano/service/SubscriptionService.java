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

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.jpa.SubscriptionJpa;

@Service
public class SubscriptionService {
	private final SubscriptionJpa subscriptionJpa;

	public SubscriptionService(final SubscriptionJpa repository) {
		super();
		this.subscriptionJpa = repository;
	}

	public List<Subscription> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public Subscription save(final Subscription subscription) {
		return subscriptionJpa.save(subscription);
	}

	public void delete(final UUID subscriptionId) {
		subscriptionJpa.deleteById(subscriptionId);
	}

	public Subscription findById(final UUID subscriptionId) {
		return subscriptionJpa.findById(subscriptionId).orElseThrow();
	}

	public List<Subscription> selectNotifications(final UUID vnfPkgId, final String event) {
		return subscriptionJpa.findEventAndVnfPkg(event, vnfPkgId.toString());
	}

}
