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

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
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

	public VnfLcmControllerImpl(final VnfLcmService _vnfLcmOpOccsRepository, final EntityManager _em, final ManoSearchResponseService searchService) {
		super(searchService, _em, VnfBlueprint.class);
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id) {
		return vnfLcmOpOccsRepository.findById(id);
	}
}
