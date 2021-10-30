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
package com.ubiqube.etsi.mano.em.v351.controller.lcmcoord;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoord;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordRequest;
import com.ubiqube.etsi.mano.vnfm.fc.lcmcoord.CoordinationFrontController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Coordinations351Sol002Controller implements Coordinations351Sol002Api {
	private final CoordinationFrontController coordinationFrontController;

	public Coordinations351Sol002Controller(final CoordinationFrontController coordinationFrontController) {
		super();
		this.coordinationFrontController = coordinationFrontController;
	}

	@Override
	public ResponseEntity<Void> coordinationsCoordinationIdCancelPost(final String coordinationId) {
		return coordinationFrontController.cancel(coordinationId);
	}

	@Override
	public ResponseEntity<LcmCoord> coordinationsCoordinationIdGet(final String coordinationId) {
		return coordinationFrontController.findById(coordinationId, LcmCoord.class);
	}

	@Override
	public ResponseEntity<LcmCoord> coordinationsPost(@Valid final LcmCoordRequest body) {
		return coordinationFrontController.create(body, LcmCoord.class);
	}

}
