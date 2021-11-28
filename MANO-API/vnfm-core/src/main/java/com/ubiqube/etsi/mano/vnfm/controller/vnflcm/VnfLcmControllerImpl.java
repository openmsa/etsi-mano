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
package com.ubiqube.etsi.mano.vnfm.controller.vnflcm;

import static com.ubiqube.etsi.mano.Constants.ensureFailedTemp;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;
import com.ubiqube.etsi.mano.vnfm.service.VnfLcmService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfLcmControllerImpl extends SearchableService implements VnfLcmController {
	private final VnfLcmService vnfLcmOpOccsRepository;
	private final VnfInstanceJpa vnfInstanceJpa;

	public VnfLcmControllerImpl(final VnfLcmService vnfLcmOpOccsRepository, final EntityManager em, final ManoSearchResponseService searchService,
			final VnfInstanceJpa vnfInstanceJpa, final GrammarParser grammarParser) {
		super(searchService, em, VnfBlueprint.class, grammarParser);
		this.vnfLcmOpOccsRepository = vnfLcmOpOccsRepository;
		this.vnfInstanceJpa = vnfInstanceJpa;
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id) {
		return vnfLcmOpOccsRepository.findById(id);
	}

	@Override
	public void failed(final UUID id) {
		final VnfBlueprint lcm = vnfLcmOpOccsRepository.findById(id);
		final VnfInstance instance = lcm.getVnfInstance();
		ensureFailedTemp(lcm);
		instance.setLockedBy(null);
		vnfInstanceJpa.save(instance);
		lcm.setOperationStatus(OperationStatusType.FAILED);
		vnfLcmOpOccsRepository.save(lcm);
	}
}
