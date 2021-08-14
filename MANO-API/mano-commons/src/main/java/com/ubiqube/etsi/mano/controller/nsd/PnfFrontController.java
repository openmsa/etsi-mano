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

import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface PnfFrontController {

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor resources.\&quot;
	 *
	 */
	<U> ResponseEntity<String> search(@Nonnull MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLink);

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An individual PNF descriptor resource can only be deleted when there is no NS instance using it or there is NSD referencing it. To delete all PNFD versions identified by a particular value of the \&quot;pnfdInvariantId\&quot; attribute, the procedure is to first use the GET method with filter \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find all versions of the PNFD. Then, the client uses the DELETE
	 * method described in this clause to delete each PNFD version individually.
	 *
	 */
	ResponseEntity<Void> delete(String pnfdInfoId);

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	<U> ResponseEntity<U> findById(String pnfdInfoId, Class<U> clazz, Consumer<U> makeLink);

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF descriptor resource.
	 *
	 */
	<U> ResponseEntity<U> modify(String pnfdInfoId, String contentType, Object body);

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the provisions specified in the Table 5.4.7.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 * @param range
	 *
	 */
	ResponseEntity<Void> getContent(String pnfdInfoId, String range);

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource represents the content of the individual PNF descriptor, i.e. PNFD content. The client can use this resource to upload and download the content of the PNFD.
	 *
	 */
	ResponseEntity<Void> putContent(String pnfdInfoId, String accept);

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	<U> ResponseEntity<U> create(Map<String, Object> userDefinedData, Class<U> clazz, Consumer<U> makeLink);

	ResponseEntity<Void> manifestGet(String pnfdInfoId, String includeSignatures);

	ResponseEntity<Void> getPnfd(String pnfdInfoId, String range, String includeSignatures);

	ResponseEntity<Void> getArtifact(String pnfdInfoId, String artifactPath, String range, String includeSignatures);

}