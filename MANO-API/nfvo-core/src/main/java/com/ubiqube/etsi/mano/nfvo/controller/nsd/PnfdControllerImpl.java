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
package com.ubiqube.etsi.mano.nfvo.controller.nsd;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.nfvo.factory.PnfFactory;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PnfdControllerImpl extends SearchableService implements PnfdController {
	private final PnfdInfoRepository pnfdInfoRepository;

	public PnfdControllerImpl(final PnfdInfoRepository pnfdInfoRepository, final EntityManager em, final ManoSearchResponseService searchService) {
		super(searchService, em, PnfDescriptor.class);
		this.pnfdInfoRepository = pnfdInfoRepository;
	}

	@Override
	public void pnfDescriptorsPnfdInfoIdDelete(final UUID id) {
		pnfdInfoRepository.delete(id);
	}

	@Override
	public PnfDescriptor pnfDescriptorsPnfdInfoIdGet(final UUID id) {
		return pnfdInfoRepository.get(id);
	}

	@Override
	public PnfDescriptor pnfDescriptorsPost(final Map<String, Object> userDefinedData) {
		final PnfDescriptor pnfdDb = PnfFactory.createPnfDescriptorsPnfdInfo(userDefinedData);
		return pnfdInfoRepository.save(pnfdDb);
	}
}
