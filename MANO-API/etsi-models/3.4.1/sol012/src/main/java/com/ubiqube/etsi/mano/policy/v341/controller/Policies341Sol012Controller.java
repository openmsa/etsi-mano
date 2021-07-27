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
package com.ubiqube.etsi.mano.policy.v341.controller;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.controller.policy.PolicyFrontController;
import com.ubiqube.etsi.mano.policy.v341.model.CreatePolicyRequest;
import com.ubiqube.etsi.mano.policy.v341.model.Link;
import com.ubiqube.etsi.mano.policy.v341.model.Policy;
import com.ubiqube.etsi.mano.policy.v341.model.PolicyLinks;
import com.ubiqube.etsi.mano.policy.v341.model.PolicyModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Policies341Sol012Controller implements Policies341Sol012Api {
	private final PolicyFrontController fc;

	public Policies341Sol012Controller(final PolicyFrontController fc) {
		super();
		this.fc = fc;
	}

	@Override
	public ResponseEntity<String> policiesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return fc.search(requestParams, nextpageOpaqueMarker, Policy.class, Policies341Sol012Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> policiesPolicyIdDelete(final String policyId) {
		return fc.deleteById(getSafeUUID(policyId));
	}

	@Override
	public ResponseEntity<Policy> policiesPolicyIdGet(final String policyId) {
		return fc.findById(policyId, Policy.class, Policies341Sol012Controller::makeLinks);
	}

	@Override
	public ResponseEntity<PolicyModifications> policiesPolicyIdPatch(final String policyId, @Valid final PolicyModifications body) {
		return fc.modify(policyId, body, Policy.class, Policies341Sol012Controller::makeLinks);
	}

	@Override
	public ResponseEntity<byte[]> policiesPolicyIdSelectedVersionsGet(final String policyId) {
		return fc.getContentBySelectedVersion(policyId);
	}

	@Override
	public ResponseEntity<Void> policiesPolicyIdVersionsVersionDelete(final String policyId, final String version) {
		return fc.deleteByVersion(policyId, version);
	}

	@Override
	public ResponseEntity<byte[]> policiesPolicyIdVersionsVersionGet(final String policyId, final String version) {
		return fc.getContentByPolicyIdAndVersion(policyId, version);
	}

	@Override
	public ResponseEntity<Void> policiesPolicyIdVersionsVersionPut(final String policyId, final String version, final MultipartFile file) {
		return fc.putContent(policyId, version, file);
	}

	@Override
	public ResponseEntity<Policy> policiesPost(@Valid final CreatePolicyRequest body) {
		return fc.create(body, Policy.class, Policies341Sol012Controller::makeLinks);
	}

	private static PolicyLinks makeLinks(final Policy pol) {
		final PolicyLinks pl = new PolicyLinks();
		final Link selected = new Link(linkTo(methodOn(Policies341Sol012Api.class).policiesPolicyIdGet(pol.getId())).withSelfRel().getHref());
		pl.setSelected(selected);

		final Link self = new Link(linkTo(methodOn(Policies341Sol012Api.class).policiesPolicyIdGet(pol.getId())).withSelfRel().getHref());
		pl.setSelf(self);

		if (null != pol.getVersions()) {
			final List<Link> versions = pol.getVersions().stream().map(x -> new Link(linkTo(methodOn(Policies341Sol012Api.class).policiesPolicyIdVersionsVersionGet(pol.getId(), x)).withSelfRel().getHref())).collect(Collectors.toList());
			pl.setVersions(versions);
		}
		return pl;
	}

}