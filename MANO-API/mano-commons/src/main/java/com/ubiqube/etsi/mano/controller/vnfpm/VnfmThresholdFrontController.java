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
package com.ubiqube.etsi.mano.controller.vnfpm;

import static com.ubiqube.etsi.mano.Constants.VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFTHR_SEARCH_MANDATORY_FIELDS;

import java.net.URI;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfmThresholdFrontController {
	private final VnfmThresholdController vnfmThresholdController;

	private final MapperFacade mapper;

	public VnfmThresholdFrontController(final VnfmThresholdController vnfmThresholdController, final MapperFacade mapper) {
		super();
		this.vnfmThresholdController = vnfmThresholdController;
		this.mapper = mapper;
	}

	public <U> ResponseEntity<U> thresholdsCreate(final Object request, final Class<U> clazz, final Consumer<U> makeLink, final Function<U, String> getSelfLink) {
		com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = mapper.map(request, com.ubiqube.etsi.mano.dao.mano.pm.Threshold.class);
		res = vnfmThresholdController.save(res);
		final U ret = mapper.map(res, clazz);
		makeLink.accept(ret);
		final String link = getSelfLink.apply(ret);
		return ResponseEntity.created(URI.create(link)).body(ret);
	}

	public ResponseEntity<Void> deleteById(final String thresholdId) {
		vnfmThresholdController.delete(UUID.fromString(thresholdId));
		return ResponseEntity.noContent().build();
	}

	public <U> ResponseEntity<U> findById(final String thresholdId, final Class<U> clazz, final Consumer<U> makeLink) {
		final com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = vnfmThresholdController.findById(UUID.fromString(thresholdId));
		final U ret = mapper.map(res, clazz);
		makeLink.accept(ret);
		return ResponseEntity.ok(ret);
	}

	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker, final Class<U> clazz, final Consumer<U> makeLink) {
		return vnfmThresholdController.search(requestParams, clazz, VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFTHR_SEARCH_MANDATORY_FIELDS, makeLink);
	}

	public <U> ResponseEntity<U> patch(final String thresholdId, final Object body, final Class<U> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
