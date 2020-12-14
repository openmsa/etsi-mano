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

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.dto.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.jpa.NsBlueprintJpa;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsBlueprintServiceImpl implements NsBlueprintService {

	private final NsBlueprintJpa nsBlueprintJpa;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	public NsBlueprintServiceImpl(final NsBlueprintJpa _nsBlueprintJpa, final NsLiveInstanceJpa _nsLiveInstanceJpa) {
		nsBlueprintJpa = _nsBlueprintJpa;
		nsLiveInstanceJpa = _nsLiveInstanceJpa;
	}

	@Override
	public int getNumberOfLiveSap(final NsdInstance nsInstance, final NsSap x) {
		final List<NsLiveInstance> res = nsLiveInstanceJpa.findByVnfInstanceAndTaskSapIsNotNull(nsInstance, x.getToscaName());
		return res.size();
	}

	@Override
	public int getNumberOfLiveVl(final NsdInstance nsInstance, final NsVirtualLink x) {
		final List<NsLiveInstance> res = nsLiveInstanceJpa.findByVnfInstanceAndTaskVlIsNotNull(nsInstance, x.getToscaName());
		return res.size();
	}

	@Override
	public NsBlueprint findById(final UUID blueprintId) {
		return nsBlueprintJpa.findById(blueprintId).orElseThrow();
	}

	@Override
	public NsBlueprint save(final NsBlueprint nsBlueprint) {
		return nsBlueprintJpa.save(nsBlueprint);
	}

	@Override
	public List<NsLcmOpOccs> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
