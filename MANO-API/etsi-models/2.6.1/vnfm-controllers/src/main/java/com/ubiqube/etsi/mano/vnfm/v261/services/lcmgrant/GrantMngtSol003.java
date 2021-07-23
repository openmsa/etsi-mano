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

package com.ubiqube.etsi.mano.vnfm.v261.services.lcmgrant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequestLinks;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003.VnfLcm261Sol003Api;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003.VnfLcm261Sol003Controller;

import ma.glasnost.orika.MapperFacade;

/**
 * Handle HTTP. XXX Should be moved to versinService.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class GrantMngtSol003 implements GrantManagement {
	private final static Pattern UUID_REGEXP = Pattern.compile("(?<uuid>[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})$");
	private final NfvoRest nfvoRest;
	private final MapperFacade mapper;

	public GrantMngtSol003(@Qualifier("nfvoRestImpl") final NfvoRest _nfvoRest, final MapperFacade _mapper) {
		nfvoRest = _nfvoRest;
		mapper = _mapper;
	}

	@Override
	public GrantResponse get(final UUID grantId) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("grantId", grantId);
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants/{grantId}")
				.buildAndExpand(uriVariables)
				.toUri();
		final HttpEntity<Object> request = new HttpEntity<>(getHttpHeaders());
		final ResponseEntity<Grant> resp = nfvoRest.getRestTemplate().exchange(uri, HttpMethod.GET, request, Grant.class);
		GrantResponse grants = new GrantResponse();
		if ((resp.getStatusCodeValue() == 202)) {
			grants.setId(grantId);
			grants.setAvailable(Boolean.FALSE);
		} else {
			grants = mapper.map(resp.getBody(), GrantResponse.class);
		}
		return grants;
	}

	@Override
	public GrantResponse post(final GrantInterface grant) {
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants")
				.build()
				.toUri();
		// XXX Elect version, and map.
		final GrantRequest manoGrant = mapper.map(grant, GrantRequest.class);
		makeLinks(manoGrant);
		final HttpEntity<Object> request = new HttpEntity<>(manoGrant, getHttpHeaders());
		final ResponseEntity<Grant> resp = nfvoRest.getRestTemplate().exchange(uri, HttpMethod.POST, request, Grant.class);
		final GrantResponse grants = new GrantResponse();
		if ((resp.getStatusCodeValue() == 201)) {
			final Optional<List<String>> loc = Optional.ofNullable(resp.getHeaders().get("Location"));
			if (loc.isPresent()) {
				final Matcher m = UUID_REGEXP.matcher(loc.get().get(0));
				m.find();
				final String uuid = m.group("uuid");
				grants.setId(UUID.fromString(uuid));
				grants.setAvailable(Boolean.FALSE);
				return grants;
			}
			throw new GenericException("Grant post received a ACCEPTED response with no Location header");
		}
		return mapper.map(resp.getBody(), GrantResponse.class);
	}

	private static void makeLinks(final GrantRequest manoGrant) {
		final GrantRequestLinks links = new GrantRequestLinks();
		Link link = new Link();
		link.setHref(VnfLcm261Sol003Controller.getSelfLink(manoGrant.getVnfInstanceId()));
		links.setVnfInstance(link);

		link = new Link();
		link.setHref(linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdGet(manoGrant.getVnfLcmOpOccId())).withSelfRel().getHref());
		links.setVnfLcmOpOcc(link);
	}

	private final HttpHeaders getHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		final MultiValueMap<String, String> auth = nfvoRest.getAutorization();
		httpHeaders.addAll(auth);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Version", "2.6.1");
		return httpHeaders;
	}

}
