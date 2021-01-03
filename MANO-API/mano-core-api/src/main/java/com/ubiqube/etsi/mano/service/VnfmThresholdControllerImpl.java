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

import com.ubiqube.etsi.mano.controller.vnfpm.VnfmThresholdController;
import com.ubiqube.etsi.mano.dao.mano.pm.Threshold;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.jpa.ThresholdJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmThresholdControllerImpl implements VnfmThresholdController {
	private final EntityManager em;

	private final ThresholdJpa thresholdJpa;

	public VnfmThresholdControllerImpl(final EntityManager _em, final ThresholdJpa _thresholdJpa) {
		em = _em;
		thresholdJpa = _thresholdJpa;
	}

	@Override
	public Threshold save(final Threshold res) {
		return thresholdJpa.save(res);
	}

	@Override
	public void delete(final UUID fromString) {
		thresholdJpa.deleteById(fromString);
	}

	@Override
	public Threshold findById(final UUID fromString) {
		return thresholdJpa.findById(fromString).orElseThrow();
	}

	@Override
	public List<Threshold> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		final AstBuilder astBuilder = new AstBuilder(filter);
		final List<Node<Object>> nodes = (List<Node<Object>>) (Object) astBuilder.getNodes();
		return sq.getCriteria((List<Node<?>>) (Object) nodes, Threshold.class);
	}

}
