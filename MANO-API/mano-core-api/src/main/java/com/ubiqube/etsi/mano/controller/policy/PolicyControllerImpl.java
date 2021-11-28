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
import com.ubiqube.etsi.mano.jpa.policy.PoliciesJpa;
import com.ubiqube.etsi.mano.jpa.policy.PolicyVersionJpa;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PolicyControllerImpl extends SearchableService implements PolicyController {

	private final PoliciesJpa policiesJpa;
	private final PolicyVersionJpa policyVersionJpa;

	public PolicyControllerImpl(final ManoSearchResponseService searchService, final EntityManager em, final PoliciesJpa policiesJpa,
			final PolicyVersionJpa policyVersionJpa, final GrammarParser grammarParser) {
		super(searchService, em, Policies.class, grammarParser);
		this.policiesJpa = policiesJpa;
		this.policyVersionJpa = policyVersionJpa;
	}

	@Override
	public void deleteById(final UUID safeUUID) {
		policiesJpa.deleteById(safeUUID);
	}

	@Override
	public void deleteByIdAndVersion(final UUID safeUUID, final String version) {
		policiesJpa.deleteByIdAndVersionsVersion(safeUUID, version);
	}

	@Override
	public Policies findById(final UUID uuid) {
		return policiesJpa.findById(uuid).orElseThrow();
	}

	@Override
	public byte[] getContentByPolicyIdAndVersion(final UUID safeUUID, final String version) {
		final PolicyVersion p = policyVersionJpa.findByPolicyIdAndVersion(safeUUID, version).orElseThrow(() -> new GenericException("Could not find Policy version: " + safeUUID + "/" + version));
		return p.getContent().getBytes();
	}

	@Override
	public byte[] getContentBySelectedVersion(final UUID safeUUID) {
		final Policies p = policiesJpa.findById(safeUUID).orElseThrow();
		final PolicyVersion pv = policyVersionJpa.findByPolicyIdAndVersion(safeUUID, p.getSelectedVersion()).orElseThrow();
		return pv.getContent().getBytes();
	}

	@Override
	public void putContent(final UUID safeUUID, final String version, final InputStream is) {
		final Policies p = policiesJpa.findById(safeUUID).orElseThrow();
		try {
			final PolicyVersion pv = new PolicyVersion(version, new String(is.readAllBytes()));
			p.getVersions().add(pv);
			policiesJpa.save(p);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public Policies create(final Policies p) {
		return policiesJpa.save(p);
	}

	@Override
	public Policies modify(final UUID safeUUID, final PolicyPatchDto patch) {
		final Policies p = policiesJpa.findById(safeUUID).orElseThrow();
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
		return policiesJpa.save(p);
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLinks) {
		return search(requestParams, clazz, null, null, makeLinks);
	}

}
