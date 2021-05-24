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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.Nonnull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceGenericFrontController;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.CreateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.HealNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstance;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstanceLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.ScaleNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.TerminateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.UpdateNsRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public final class NsInstances261Sol005Controller implements NsInstances261Sol005Api {

	private final NsInstanceGenericFrontController frontController;

	public NsInstances261Sol005Controller(final NsInstanceGenericFrontController _frontController) {
		frontController = _frontController;
	}

	/**
	 * Query multiple NS instances.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS instances. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@Override
	public ResponseEntity<String> nsInstancesGet(final MultiValueMap<String, String> requestParams) {
		return frontController.search(requestParams, NsInstance.class, null, NsInstances261Sol005Controller::makeLinks);
	}

	/**
	 * Delete NS instance resource.
	 *
	 * Delete NS Identifier This method deletes an individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdDelete(final String nsInstanceId) {
		return frontController.delete(nsInstanceId);
	}

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdGet(final String nsInstanceId) {
		return frontController.findById(nsInstanceId, NsInstance.class, NsInstances261Sol005Controller::makeLinks);
	}

	/**
	 * Heal a NS instance.
	 *
	 * The POST method requests to heal a NS instance resource. This method shall follow the provisions specified in the Tables 6.4.7.3.1-1 and 6.4.7.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdHealPost(final String nsInstanceId, final HealNsRequest body) {
		return frontController.heal(nsInstanceId, body);
	}

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdInstantiatePost(final String nsInstanceId, final InstantiateNsRequest body) {
		return frontController.instantiate(nsInstanceId, body, NsInstances261Sol005Controller::getSelfLink);
	}

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdScalePost(final String nsInstanceId, final ScaleNsRequest body) {
		return frontController.scale(nsInstanceId, body);
	}

	/**
	 * Terminate a NS instance.
	 *
	 * Terminate NS task. The POST method terminates a NS instance. This method can only be used with a NS instance in the INSTANTIATED state. Terminating a NS instance does not delete the NS instance identifier, but rather transitions the NS into the NOT_INSTANTIATED state. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 6.4.8.3.1-1 and 6.8.8.3.1-2.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdTerminatePost(final String nsInstanceId, final TerminateNsRequest body) {
		return frontController.terminate(nsInstanceId, body, NsInstances261Sol005Controller::getSelfLink);
	}

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdUpdatePost(final String nsInstanceId, final UpdateNsRequest body) {
		return frontController.update(nsInstanceId, body);
	}

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesPost(final CreateNsRequest request) {
		return frontController.create(request, NsInstance.class, NsInstances261Sol005Controller::makeLinks, NsInstances261Sol005Controller::getSelfLink);
	}

	private static void makeLinks(@Nonnull final NsInstance nsdInfo) {
		final String id = nsdInfo.getId();
		final NsInstanceLinks nsInstanceLinks = new NsInstanceLinks();
		final Link heal = new Link();
		heal.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdHealPost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setHeal(heal);

		final Link instantiate = new Link();
		instantiate.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdInstantiatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setInstantiate(instantiate);
		// nsInstanceLinks.setNestedNsInstances(nestedNsInstances);
		final Link scale = new Link();
		scale.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdScalePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setScale(scale);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdGet(id)).withSelfRel().getHref());
		nsInstanceLinks.setSelf(self);

		final Link terminate = new Link();
		terminate.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdTerminatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setTerminate(terminate);

		final Link update = new Link();
		update.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdUpdatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setUpdate(update);
		nsdInfo.setLinks(nsInstanceLinks);
	}

	private static String getSelfLink(final NsBlueprint nsInstance) {
		return linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdGet(nsInstance.getId().toString())).withSelfRel().getHref();
	}

	private static String getSelfLink(final NsInstance nsdInfo) {
		return linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdHealPost(nsdInfo.getId(), null)).withSelfRel().getHref();
	}
}
