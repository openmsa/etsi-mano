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
/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nonnull;

import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface NsDescriptorGenericFrontController {

	/**
	 * Query information about multiple NS descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple NS descriptor resources. This method shall follow the provisions specified in the Tables 5.4.2.3.2-1 and 5.4.2.3.2-2 for URI query parameters, request and response data structures, and response codes.\&quot;
	 *
	 */
	<U> ResponseEntity<String> search(@Nonnull MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLink);

	/**
	 * Delete an individual NS descriptor resource.
	 *
	 * The DELETE method deletes an individual NS descriptor resource. An individual NS descriptor resource can only be deleted when there is no NS instance using it (i.e. usageState &#x3D; NOT_IN_USE) and has been disabled already (i.e. operationalState &#x3D; DISABLED). Otherwise, the DELETE method shall fail.
	 *
	 */
	ResponseEntity<Void> delete(String nsdInfoId);

	/**
	 * Read information about an individual NS descriptor resource.
	 *
	 * \&quot;The GET method reads information about an individual NS descriptor. This method shall follow the provisions specified in GS NFV-SOL 005 Tables 5.4.3.3.2-1 and 5.4.3.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response data structures, and response codes.\&quot;
	 *
	 */
	<U> ResponseEntity<U> finsById(String nsdInfoId, Class<U> clazz, Consumer<U> makeLink);

	/**
	 * Fetch the content of a NSD.
	 *
	 * The GET method fetches the content of the NSD. The NSD can be implemented as a single file or as a collection of multiple files. If the NSD is implemented in the form of multiple files, a ZIP file embedding these files shall be returned. If the NSD is implemented as a single file, either that file or a ZIP file embedding that file shall be returned. The selection of the format is controlled by the \&quot;Accept\&quot; HTTP header passed in the GET request:• If the \&quot;Accept\&quot; header
	 * contains only \&quot;text/plain\&quot; and the NSD is implemented as a single file, the file shall be returned; otherwise, an error message shall be returned.• If the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;, the single file or the multiple files that make up the NSD shall be returned embedded in a ZIP file.• If the \&quot;Accept\&quot; header contains both \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to the NFVO to choose the format to
	 * return for a single-file NSD; for a multi-file NSD, a ZIP file shall be returned.NOTE: The structure of the NSD zip file is outside the scope of the present document.
	 *
	 */
	ResponseEntity<List<ResourceRegion>> getNsdContent(String nsdInfoId, String accept, String range);

	/**
	 * Upload the content of a NSD.
	 *
	 * \&quot;The PUT method is used to upload the content of a NSD. The NSD to be uploaded can be implemented as a single file or as a collection of multiple files, as defined in clause 5.4.4.3.2 of GS NFV-SOL 005. If the NSD is implemented in the form of multiple files, a ZIP file embedding these files shall be uploaded. If the NSD is implemented as a single file, either that file or a ZIP file embedding that file shall be uploaded. The \&quot;Content-Type\&quot; HTTP header in the PUT request
	 * shall be set accordingly based on the format selection of the NSD. If the NSD to be uploaded is a text file, the \&quot;Content-Type\&quot; header is set to \&quot;text/plain\&quot;. If the NSD to be uploaded is a zip file, the \&quot;Content-Type\&quot; header is set to \&quot;application/zip\&quot;. This method shall follow the provisions specified in the Tables 5.4.4.3.3-1 and 5.4.4.3.3-2 of GS-NFV-SOL 005 for URI query parameters, request and response data structures, and response
	 * codes.\&quot;
	 *
	 */
	ResponseEntity<Void> putNsdContent(String nsdInfoId, String accept, InputStreamSource file);

	/**
	 * Modify the operational state and/or the user defined data of an individual NS descriptor resource.
	 *
	 * The PATCH method modifies the operational state and/or user defined data of an individual NS descriptor resource. This method can be used to: 1) Enable a previously disabled individual NS descriptor resource, allowing again its use for instantiation of new network service with this descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not change as result. 2) Disable a previously enabled individual NS descriptor resource, preventing any further use for instantiation of new
	 * network service(s) with this descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not changes a result. 3) Modify the user defined data of an individual NS descriptor resource.
	 *
	 */
	<U> ResponseEntity<U> modify(String nsdInfoId, String body, String ifMatch, Class<U> clazz, Consumer<U> makeLink);

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new version of an on-boarded NS descriptor.
	 *
	 */
	<U> ResponseEntity<U> create(String contentType, Map<String, String> userDefinedData, Class<U> clazz, Consumer<U> makeLink, Function<U, String> getSelfLink);

	ResponseEntity<Void> getArtifact(String nsdInfoId, String artifactPath, String range, String includeSignatures);

	ResponseEntity<Void> getManifest(String nsdInfoId, String includeSignatures);

	ResponseEntity<Void> getNsd(String nsdInfoId, String includeSignatures);

}