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
package com.ubiqube.etsi.mano.service.vim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.query.dsl.SearchQuerySelectStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.common.EntityReference;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.common.GeoPoint;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VimManager {

	private final List<Vim> vims;

	private final VimConnectionInformationJpa vimConnectionInformationJpa;

	private final Map<UUID, Vim> vimAssociation;

	private final EntityManager entityManager;

	public VimManager(final List<Vim> _vims, final VimConnectionInformationJpa _vimConnectionInformationJpa, final EntityManager _entityManager) {
		vims = _vims;
		vimAssociation = new HashMap<>();
		vimConnectionInformationJpa = _vimConnectionInformationJpa;
		entityManager = _entityManager;
		init();
	}

	private void init() {
		vims.forEach(x -> {
			final Set<VimConnectionInformation> vimsId = vimConnectionInformationJpa.findByVimType(x.getType());
			associateVims(vimsId, x);
		});
	}

	private void associateVims(final Set<VimConnectionInformation> vimsIs, final Vim vim) {
		vimsIs.forEach(x -> vimAssociation.put(x.getId(), vim));
	}

	@NotNull
	public Vim getVimById(final UUID id) {
		return Optional.ofNullable(vimAssociation.get(id)).orElseThrow(() -> new NotFoundException("No such Vim: " + id));
	}

	public void rebuildCache() {
		init();
	}

	public VimConnectionInformation findVimById(final UUID id) {
		return vimConnectionInformationJpa.findById(id).orElseThrow(() -> new NotFoundException("No connection Id " + id));
	}

	public VimConnectionInformation findVimByVimId(final String id) {
		return vimConnectionInformationJpa.findByVimId(id).orElseThrow(() -> new NotFoundException("No connection vimId " + id));
	}

	@Nonnull
	public Set<VimConnectionInformation> getVimByType(final String type) {
		return vimConnectionInformationJpa.findByVimType(type);
	}

	public VimConnectionInformation save(final VimConnectionInformation x) {
		return vimConnectionInformationJpa.save(x);
	}

	public Optional<VimConnectionInformation> findOptionalVimByVimId(final String vimId) {
		return vimConnectionInformationJpa.findByVimId(vimId);
	}

	public Iterable<VimConnectionInformation> findAllVimconnections() {
		return vimConnectionInformationJpa.findAll();
	}

	public void getVimByDistance(final GeoPoint point) {
		final SearchSession session = Search.session(entityManager);
		final SearchQuerySelectStep<?, EntityReference, VimConnectionInformation, SearchLoadingOptionsStep, ?, ?> ss = session.search(VimConnectionInformation.class);
		final SearchPredicateFactory pf = session.scope(VimConnectionInformation.class).predicate();
		final SearchPredicate pr = pf.spatial().within().fields("").circle(point.getLat(), point.getLng(), 10000).toPredicate();
		ss.where(pr);
	}
}
