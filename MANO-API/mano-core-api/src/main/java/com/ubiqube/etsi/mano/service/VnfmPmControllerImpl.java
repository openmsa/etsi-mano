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

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.vnfpm.VnfmPmController;
import com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.jpa.PmJobsJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmPmControllerImpl implements VnfmPmController {
	private final EntityManager em;

	private final PmJobsJpa pmJobsJpa;

	public VnfmPmControllerImpl(final PmJobsJpa _pmJobsJpa, final EntityManager _em) {
		pmJobsJpa = _pmJobsJpa;
		em = _em;
	}

	@Override
	public void delete(final UUID id) {
		findById(id);
		pmJobsJpa.deleteById(id);
	}

	@Override
	public PmJob findById(final UUID id) {
		return pmJobsJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find PM Job: " + id));
	}

	@Override
	public PerformanceReport findReport(final UUID fromString, final UUID fromString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PmJob save(final PmJob res) {
		return pmJobsJpa.save(res);
	}

	@Override
	public List<PmJob> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		final AstBuilder astBuilder = new AstBuilder(filter);
		final List<Node<String>> nodes = astBuilder.getNodes();
		return sq.getCriteria((List<Node<?>>) (Object) nodes, PmJob.class);
	}

}
