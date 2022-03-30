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
package com.ubiqube.etsi.mano.controller.policy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.policy.Policies;
import com.ubiqube.etsi.mano.dao.mano.policy.PolicyVersion;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.PolicyService;
import com.ubiqube.etsi.mano.service.SearchableService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PolicyControllerImpl extends SearchableService implements PolicyController {

	private final PolicyService policyService;

	public PolicyControllerImpl(final EntityManager em, final ManoSearchResponseService searchService, final PolicyService policyService, final GrammarParser grammarParser) {
		super(searchService, em, Policies.class, grammarParser);
		this.policyService = policyService;
	}

	@Override
	public void deleteById(final UUID safeUUID) {
		policyService.deletePoliciesById(safeUUID);
	}

	@Override
	public void deleteByIdAndVersion(final UUID safeUUID, final String version) {
		policyService.deleteByIdAndVersionsVersion(safeUUID, version);
	}

	@Override
	public Policies findById(final UUID uuid) {
		return policyService.findPoliciesById(uuid).orElseThrow();
	}

	@Override
	public byte[] getContentByPolicyIdAndVersion(final UUID safeUUID, final String version) {
		final PolicyVersion p = policyService.findByPolicyIdAndVersion(safeUUID, version).orElseThrow(() -> new GenericException("Could not find Policy version: " + safeUUID + "/" + version));
		return p.getContent().getBytes();
	}

	@Override
	public byte[] getContentBySelectedVersion(final UUID safeUUID) {
		final Policies p = policyService.findPoliciesById(safeUUID).orElseThrow();
		final PolicyVersion pv = policyService.findByPolicyIdAndVersion(safeUUID, p.getSelectedVersion()).orElseThrow();
		return pv.getContent().getBytes();
	}

	@Override
	public void putContent(final UUID safeUUID, final String version, final InputStream is) {
		final Policies p = policyService.findPoliciesById(safeUUID).orElseThrow();
		try {
			final PolicyVersion pv = new PolicyVersion(version, new String(is.readAllBytes()));
			p.getVersions().add(pv);
			policyService.policiesSave(p);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public Policies create(final Policies p) {
		return policyService.policiesSave(p);
	}

	@Override
	public Policies modify(final UUID safeUUID, final PolicyPatchDto patch) {
		final Policies p = policyService.findPoliciesById(safeUUID).orElseThrow();
		if (null != patch.getRemoveAssociations()) {
			patch.getRemoveAssociations().forEach(x -> {
				final List<String> newList = p.getAssociations().stream().filter(y -> y.equals(x)).toList();
				p.setAssociations(newList);
			});
		}
		if (null != patch.getAddAssociations()) {
			p.getAssociations().addAll(patch.getAddAssociations());
		}
		if (null != patch.getActivationStatus()) {
			p.setActivationStatus(patch.getActivationStatus());
		}
		if (null != patch.getSelectedVersion()) {
			p.setSelectedVersion(patch.getSelectedVersion());
		}
		return policyService.policiesSave(p);
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLinks) {
		return search(requestParams, clazz, null, null, makeLinks);
	}

}
