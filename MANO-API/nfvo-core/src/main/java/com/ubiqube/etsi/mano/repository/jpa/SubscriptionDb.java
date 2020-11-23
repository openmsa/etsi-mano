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
package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.jpa.SubscriptionJpa;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

@Service
public class SubscriptionDb extends AbstractJpaOnly<Subscription> implements SubscriptionRepository {

	private final SubscriptionJpa repository;

	public SubscriptionDb(final EntityManager em, final SubscriptionJpa _repository) {
		super(em, _repository);
		repository = _repository;
	}

	@Override
	protected Class<Subscription> getFrontClass() {
		return Subscription.class;
	}

	@Override
	public List<Subscription> selectNotifications(final UUID vnfPkgId, final String event) {
		return repository.findEventAndVnfPkg(event, vnfPkgId.toString());
	}

}
