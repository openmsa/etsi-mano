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

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.dto.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;

@Service
public class NsBlueprintServiceImpl implements NsBlueprintService {

	@Override
	public int getNumberOfLiveSap(final NsdInstance nsInstance, final NsSap x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLiveVl(final NsdInstance nsInstance, final NsVirtualLink x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NsBlueprint findById(final UUID blueprintId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NsBlueprint save(final NsBlueprint nsBlueprint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NsLcmOpOccs> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
