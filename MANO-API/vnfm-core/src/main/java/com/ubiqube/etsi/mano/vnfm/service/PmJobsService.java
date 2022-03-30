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
package com.ubiqube.etsi.mano.vnfm.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.jpa.PmJobsJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PmJobsService {
	private final PmJobsJpa pmJobsJpa;

	public PmJobsService(final PmJobsJpa pmJobsJpa) {
		super();
		this.pmJobsJpa = pmJobsJpa;
	}

	public void deleteById(final UUID id) {
		pmJobsJpa.deleteById(id);
	}

	public PmJob save(final PmJob res) {
		return pmJobsJpa.save(res);
	}

	public Optional<PmJob> findById(final UUID id) {
		return pmJobsJpa.findById(id);
	}

}
