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
package com.ubiqube.etsi.mano.nfvo.v281.controller.nsd;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.SingleControllerCondition;
import com.ubiqube.etsi.mano.controller.nsd.PnfFrontController;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.CreatePnfdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.Link;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.PnfdInfo;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.PnfdInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.PnfdInfoModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
@Conditional(SingleControllerCondition.class)
public class PnfDescriptors281Sol005Controller implements PnfDescriptors281Sol005Api {
	private final PnfFrontController pnfFrontController;

	public PnfDescriptors281Sol005Controller(final PnfFrontController pnfFrontController) {
		super();
		this.pnfFrontController = pnfFrontController;
	}

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor
	 * resources.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> pnfDescriptorsGet(@Nonnull @RequestParam final MultiValueMap<String, String> requestParams) {
		final Consumer<PnfdInfo> setLink = x -> x.setLinks(makeLinks(x));
		return pnfFrontController.search(requestParams, PnfdInfo.class, setLink);
	}

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An
	 * individual PNF descriptor resource can only be deleted when there is no NS
	 * instance using it or there is NSD referencing it. To delete all PNFD versions
	 * identified by a particular value of the \&quot;pnfdInvariantId\&quot;
	 * attribute, the procedure is to first use the GET method with filter
	 * \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find
	 * all versions of the PNFD. Then, the client uses the DELETE method described
	 * in this clause to delete each PNFD version individually.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdDelete(final String pnfdInfoId) {
		return pnfFrontController.delete(pnfdInfoId);
	}

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This
	 * method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and
	 * 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfo> pnfDescriptorsPnfdInfoIdGet(final String pnfdInfoId) {
		return pnfFrontController.findById(pnfdInfoId, PnfdInfo.class, PnfDescriptors281Sol005Controller::makeLinks);
	}

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF
	 * descriptor resource.
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfoModifications> pnfDescriptorsPnfdInfoIdPatch(final String pnfdInfoId, final PnfdInfoModifications body) {
		return pnfFrontController.modify(pnfdInfoId, null, body);
	}

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the
	 * provisions specified in the Table 5.4.7.3.2-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentGet(final String pnfdInfoId, final String range) {
		return pnfFrontController.getContent(pnfdInfoId, range);
	}

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource
	 * represents the content of the individual PNF descriptor, i.e. PNFD content.
	 * The client can use this resource to upload and download the content of the
	 * PNFD.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentPut(final String pnfdInfoId, final String accept) {
		return pnfFrontController.putContent(pnfdInfoId, accept);
	}

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfo> pnfDescriptorsPost(final CreatePnfdInfoRequest body) {
		return pnfFrontController.create(body.getUserDefinedData(), PnfdInfo.class, PnfDescriptors281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdManifestGet(final String pnfdInfoId, @Valid final String includeSignatures) {
		return pnfFrontController.manifestGet(pnfdInfoId, includeSignatures);
	}

	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdGet(final String pnfdInfoId, final String range, @Valid final String includeSignatures) {
		return pnfFrontController.getPnfd(pnfdInfoId, range, includeSignatures);
	}

	private static PnfdInfoLinks makeLinks(final PnfdInfo x) {
		final PnfdInfoLinks links = new PnfdInfoLinks();
		final Link pnfdContent = new Link();
		pnfdContent.setHref(linkTo(methodOn(PnfDescriptors281Sol005Api.class).pnfDescriptorsPnfdInfoIdPnfdContentGet(x.getId(), "")).withSelfRel().getHref());
		links.setPnfdContent(pnfdContent);
		final Link self = new Link();
		self.setHref(linkTo(methodOn(PnfDescriptors281Sol005Api.class).pnfDescriptorsPnfdInfoIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(self);

		return null;
	}

}
