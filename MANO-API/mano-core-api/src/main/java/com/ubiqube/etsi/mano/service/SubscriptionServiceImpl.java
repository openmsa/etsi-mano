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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;
import com.ubiqube.etsi.mano.jpa.SubscriptionJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private final EntityManager em;

	private final SubscriptionJpa subscriptionJpa;

	private final GrammarParser grammarParser;

	public SubscriptionServiceImpl(final SubscriptionJpa repository, final EntityManager em, final GrammarParser grammarParser) {
		super();
		this.subscriptionJpa = repository;
		this.em = em;
		this.grammarParser = grammarParser;
	}

	@Override
	public List<Subscription> query(final String filter, final SubscriptionType type) {
		final SearchQueryer sq = new SearchQueryer(em, grammarParser);
		final List<Node<Object>> nodes = grammarParser.parse(filter);
		nodes.add(Node.of("subscriptionType", Operand.EQ, type.toString()));
		return sq.getCriteria((List<Node<?>>) (Object) nodes, Subscription.class);
	}

	@Override
	public Subscription save(final Subscription subscription, final SubscriptionType type) {
		subscription.setSubscriptionType(type);
		testSubscription(subscription);
		return subscriptionJpa.save(subscription);
	}

	private static void testSubscription(final Subscription subscription) {
		try {
			final URL url = new URL(subscription.getCallbackUri());
			url.openConnection();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(final UUID subscriptionId, final SubscriptionType type) {
		findById(subscriptionId, type);
		subscriptionJpa.deleteById(subscriptionId);
	}

	@Override
	public Subscription findById(final UUID subscriptionId, final SubscriptionType type) {
		return subscriptionJpa.findById(subscriptionId).orElseThrow(() -> new NotFoundException("Could not find subscription id: " + subscriptionId));
	}

	@Override
	public List<Subscription> selectNotifications(final UUID vnfPkgId, final String event) {
		return subscriptionJpa.findEventAndVnfPkg(event, vnfPkgId.toString());
	}

}
