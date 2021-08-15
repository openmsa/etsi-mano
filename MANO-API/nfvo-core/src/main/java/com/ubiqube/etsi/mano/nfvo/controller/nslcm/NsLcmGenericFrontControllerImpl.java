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
package com.ubiqube.etsi.mano.nfvo.controller.nslcm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.nslcm.NsLcmGenericFrontController;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.service.NsBlueprintService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsLcmGenericFrontControllerImpl implements NsLcmGenericFrontController {
	private static final String NSLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS = "operationParams,changedVnfInfo,error,resourceChanges";

	private static final Set<String> NSLCM_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "startTime", "operationState", "statusEnteredTime", "nsInstanceId", "lcmOperationType", "isAutomaticInvocation", "isCancelPending", "_links.continue.href", "_links.self.href", "_links.nsInstance.href", "_links.retry.href", "_links.rollback.href"));
	private final NsBlueprintService nsLcmOpOccsService;

	private final MapperFacade mapper;

	public NsLcmGenericFrontControllerImpl(final NsBlueprintService nsLcmOpOccsService, final MapperFacade mapper) {
		super();
		this.nsLcmOpOccsService = nsLcmOpOccsService;
		this.mapper = mapper;
	}

	@Override
	public <U> ResponseEntity<U> fail(final String nsLcmOpOccId, final Class<U> clazz, final Consumer<U> makeLinks) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Void> cancel(final String nsLcmOpOccId, final String string) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String nextpageOpaqueMarker, final Consumer<U> makeLinks) {
		return nsLcmOpOccsService.search(requestParams, clazz, NSLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS, NSLCM_SEARCH_MANDATORY_FIELDS, makeLinks);
	}

	@Override
	public ResponseEntity<Void> continu(final String nsLcmOpOccId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public <U> ResponseEntity<U> findById(final String nsLcmOpOccId, final Class<U> clazz, final Consumer<U> makeLinks) {
		final NsBlueprint nsLcmOpOccs = nsLcmOpOccsService.findById(UUID.fromString(nsLcmOpOccId));
		final U res = mapper.map(nsLcmOpOccs, clazz);
		makeLinks.accept(res);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> retry(final String nsLcmOpOccId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Void> rollback(final String nsLcmOpOccId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
