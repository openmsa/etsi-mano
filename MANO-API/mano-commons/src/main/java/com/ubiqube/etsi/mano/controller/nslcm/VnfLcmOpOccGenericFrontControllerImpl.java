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
package com.ubiqube.etsi.mano.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.VNFLCMOPOCC_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFLCMOPOCC_SEARCH_MANDATORY_FIELDS;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfLcmOpOccGenericFrontControllerImpl implements VnfLcmOpOccGenericFrontController {
	private final VnfLcmController vnfLcmController;

	public VnfLcmOpOccGenericFrontControllerImpl(final VnfLcmController vnfLcmController) {
		super();
		this.vnfLcmController = vnfLcmController;
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> class1, final Consumer<U> makeLinks) {
		return vnfLcmController.search(requestParams, class1, VNFLCMOPOCC_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFLCMOPOCC_SEARCH_MANDATORY_FIELDS, makeLinks);
	}

	@Override
	public ResponseEntity<Void> lcmOpOccRollback(final UUID id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Void> lcmOpOccRetry(final UUID id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public VnfBlueprint lcmOpOccFindById(final UUID id) {
		return vnfLcmController.vnfLcmOpOccsVnfLcmOpOccIdGet(id);
	}

	@Override
	public <U> ResponseEntity<U> lcmOpOccFail(final UUID id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public <U> ResponseEntity<U> lcmOpOccCancel(final UUID id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
