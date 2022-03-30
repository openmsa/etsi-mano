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
package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.policy.Policies;
import com.ubiqube.etsi.mano.dao.mano.policy.PolicyVersion;
import com.ubiqube.etsi.mano.jpa.policy.PoliciesJpa;
import com.ubiqube.etsi.mano.jpa.policy.PolicyVersionJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PolicyService {
	private final PoliciesJpa policiesJpa;
	private final PolicyVersionJpa policyVersionJpa;

	public PolicyService(final PoliciesJpa policiesJpa, final PolicyVersionJpa policyVersionJpa) {
		super();
		this.policiesJpa = policiesJpa;
		this.policyVersionJpa = policyVersionJpa;
	}

	public Policies policiesSave(final Policies p) {
		return policiesJpa.save(p);
	}

	public Optional<Policies> findPoliciesById(final UUID safeUUID) {
		return policiesJpa.findById(safeUUID);
	}

	public Optional<PolicyVersion> findByPolicyIdAndVersion(final UUID safeUUID, final String selectedVersion) {
		return policyVersionJpa.findByPolicyIdAndVersion(safeUUID, selectedVersion);
	}

	public void deletePoliciesById(final UUID safeUUID) {
		policiesJpa.deleteById(safeUUID);
	}

	public void deleteByIdAndVersionsVersion(final UUID safeUUID, final String version) {
		policiesJpa.deleteByIdAndVersionsVersion(safeUUID, version);
	}

}
