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
package com.ubiqube.etsi.mano.controller.vnflcm;

import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfSnapshotsFrontController {

	public <U> ResponseEntity<List<U>> search(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker, final Class<U> clazz, final Consumer<U> makeLink) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<U> create(@Valid final Object body, final Class<U> clazz, final Consumer<U> makeLink) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Void> delete(final String vnfSnapshotInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<U> findById(final String vnfSnapshotInfoId, final Class<U> clazz, final Consumer<U> makeLink) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<U> patch(final String vnfSnapshotInfoId, @Valid final Object body, final Class<U> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Resource> fetch(final String vnfSnapshotInfoId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

}
