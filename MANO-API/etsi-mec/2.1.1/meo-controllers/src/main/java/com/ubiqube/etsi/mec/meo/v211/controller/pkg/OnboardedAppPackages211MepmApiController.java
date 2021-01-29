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
package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppD;

@Controller
public class OnboardedAppPackages211MepmApiController implements OnboardedAppPackages211MepmApi {

	@Override
	public ResponseEntity<AppD> appDGET(final String appDId, @Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> appDIdGET(final String appDId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> appDIdPUT(final String appDId, @Valid final Object body) {
		// TODO Auto-generated method stub
		return null;
	}

}
