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
package com.ubiqube.etsi.mano.controller.nsd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;

import ma.glasnost.orika.MapperFacade;

public class PnfFrontController {

	private static final Set<String> PNFD_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private static final String PNFD_SEARCH_DEFAULT_EXCLUDE_FIELDS = "userDefinedData";

	private final PnfdController pnfdController;

	private final MapperFacade mapper;

	public PnfFrontController(final PnfdController _pnfdController, final MapperFacade _mapper) {
		pnfdController = _pnfdController;
		mapper = _mapper;
	}

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor resources.\&quot;
	 *
	 */
	public <U> ResponseEntity<String> search(@Nonnull @RequestParam final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLink) {
		return pnfdController.search(requestParams, clazz, PNFD_SEARCH_DEFAULT_EXCLUDE_FIELDS, PNFD_SEARCH_MANDATORY_FIELDS, makeLink);
	}

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An individual PNF descriptor resource can only be deleted when there is no NS instance using it or there is NSD referencing it. To delete all PNFD versions identified by a particular value of the \&quot;pnfdInvariantId\&quot; attribute, the procedure is to first use the GET method with filter \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find all versions of the PNFD. Then, the client uses the DELETE
	 * method described in this clause to delete each PNFD version individually.
	 *
	 */
	public ResponseEntity<Void> delete(final String pnfdInfoId) {
		pnfdController.pnfDescriptorsPnfdInfoIdDelete(UUID.fromString(pnfdInfoId));
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	public <U> ResponseEntity<U> findById(final String pnfdInfoId, final Class<U> clazz, final Consumer<U> makeLink) {
		final PnfDescriptor pnfdInfoDb = pnfdController.pnfDescriptorsPnfdInfoIdGet(UUID.fromString(pnfdInfoId));
		final U pnfdInfo = mapper.map(pnfdInfoDb, clazz);
		makeLink.accept(pnfdInfo);
		return new ResponseEntity<>(pnfdInfo, HttpStatus.OK);
	}

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF descriptor resource.
	 *
	 */
	public <U> ResponseEntity<U> modify(final String pnfdInfoId, final String contentType, final Object body) {
		// : Implement...

		return null;
	}

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the provisions specified in the Table 5.4.7.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 * @param range
	 *
	 */
	public ResponseEntity<Void> getContent(final String pnfdInfoId, final String range) {
		// : Implement...
		return ResponseEntity.noContent().build();
	}

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource represents the content of the individual PNF descriptor, i.e. PNFD content. The client can use this resource to upload and download the content of the PNFD.
	 *
	 */
	public ResponseEntity<Void> putContent(final String pnfdInfoId, final String accept) {
		// PnfdOnBoardingNotification OSS/BSS
		return ResponseEntity.noContent().build();
	}

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	public <U> ResponseEntity<U> create(final Map<String, Object> userDefinedData, final Class<U> clazz, final Consumer<U> makeLink) {
		final PnfDescriptor pnfdDb = pnfdController.pnfDescriptorsPost(userDefinedData);
		final U pnfd = mapper.map(pnfdDb, clazz);
		makeLink.accept(pnfd);
		return new ResponseEntity<>(pnfd, HttpStatus.OK);
	}

	public ResponseEntity<Void> manifestGet(final String pnfdInfoId, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Void> getPnfd(final String pnfdInfoId, final String range, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Void> getArtifact(final String pnfdInfoId, final String artifactPath, final String range, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

}
