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
package com.ubiqube.etsi.mano.vnfm.v331.controller.lcmgrant;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.grant.Grant;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Grants331Sol005Controller implements Grants331Sol005Api {

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest body) {
		// TODO Auto-generated method stub
		return null;
	}
}
