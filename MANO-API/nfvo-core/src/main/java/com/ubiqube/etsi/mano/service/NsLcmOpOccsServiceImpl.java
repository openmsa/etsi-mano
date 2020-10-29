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
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.NsLcmOpOccsJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class NsLcmOpOccsServiceImpl implements NsLcmOpOccsService {
	private final NsLcmOpOccsJpa nsLcmOpOccsJpa;
	private final EntityManager em;

	public NsLcmOpOccsServiceImpl(final NsLcmOpOccsJpa _nsLcmOpOccsJpa, final EntityManager _em) {
		nsLcmOpOccsJpa = _nsLcmOpOccsJpa;
		em = _em;
	}

	@Override
	@Nonnull
	public NsLcmOpOccs createLcmOpOccs(final NsdInstance nsInstance, @Nonnull final NsdChangeType state) {
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, state);
		return nsLcmOpOccsJpa.save(lcmOpOccs);
	}

	@Override
	public NsLcmOpOccs save(final NsLcmOpOccs lcmOpOccs) {
		return nsLcmOpOccsJpa.save(lcmOpOccs);
	}

	@Override
	public NsLcmOpOccs get(final UUID id) {
		return nsLcmOpOccsJpa.findById(id).orElseThrow(() -> new NotFoundException("Lcm Op OCCS not found: " + id));
	}

	@Override
	public List<NsLcmOpOccs> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<NsLcmOpOccs>) sq.getCriteria(filter, NsLcmOpOccs.class)
				.getResultStream().collect(Collectors.toList());
	}

	@Override
	public NsLcmOpOccs findById(final UUID nsLcmId) {
		return nsLcmOpOccsJpa.findById(nsLcmId).orElseThrow(() -> new NotFoundException("NsLcmOpOccs Not found " + nsLcmId));
	}

}
