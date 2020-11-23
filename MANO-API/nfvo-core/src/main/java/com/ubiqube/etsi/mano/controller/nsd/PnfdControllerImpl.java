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
package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.factory.PnfFactory;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

@Service
public class PnfdControllerImpl implements PnfdController {
	private final PnfdInfoRepository pnfdInfoRepository;

	public PnfdControllerImpl(final PnfdInfoRepository _pnfdInfoRepository) {
		pnfdInfoRepository = _pnfdInfoRepository;
	}

	@Override
	public List<PnfDescriptor> pnfDescriptorsGet(final String filter) {
		return pnfdInfoRepository.query(filter);
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
