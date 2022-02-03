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
package com.ubiqube.etsi.mano.nfvo.v351.controller.nfvici;

import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.NfviCapacityInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
@ConditionalOnMissingClass("com.ubiqube.etsi.mano.nfvo.v331.controller.nfvici.NfviCapacityInfos331Sol005Api")
public class NfviCapacityInfos351Sol005Controller implements NfviCapacityInfos351Sol005Api {

	@Override
	public ResponseEntity<List<NfviCapacityInfo>> nfviCapacityInfosGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<NfviCapacityInfo> nfviCapacityInfosVimIdGet(@Valid final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
