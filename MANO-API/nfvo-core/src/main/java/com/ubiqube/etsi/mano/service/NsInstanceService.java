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

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.jpa.NsVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.NsVnfPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdInstanceJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class NsInstanceService {
	private final NsVirtualLinkJpa nsVirtualLinkJpa;

	private final NsdPackageJpa nsdPackageJpa;

	private final NsVnfPackageJpa vnfPackageJpa;

	private final NsdInstanceJpa nsdInstanceJpa;

	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	private final EntityManager em;

	public NsInstanceService(final NsVirtualLinkJpa _nsVirtualLinkJpa, final NsdPackageJpa _nsdPackageJpa, final NsVnfPackageJpa _vnfPackageJpa, final NsdInstanceJpa _nsdInstanceJpa, final NsLiveInstanceJpa _nsLiveInstanceJpa, final EntityManager _em) {
		nsVirtualLinkJpa = _nsVirtualLinkJpa;
		nsdPackageJpa = _nsdPackageJpa;
		vnfPackageJpa = _vnfPackageJpa;
		nsdInstanceJpa = _nsdInstanceJpa;
		nsLiveInstanceJpa = _nsLiveInstanceJpa;
		em = _em;
	}

	public int countLiveInstanceOfSap(final NsdInstance nsInstance, final UUID id) {
		// TODO
		return 0;
	}

	public int countLiveInstanceOfVirtualLink(final NsdInstance nsInstance, final UUID id) {
		final List<NsLiveInstance> res = nsLiveInstanceJpa.findByNsInstanceAndNsTaskId(nsInstance, id);
		return res.size();
	}

	public int countLiveInstanceOfVnf(final NsdInstance nsInstance, final UUID id) {
		final List<NsLiveInstance> res = nsLiveInstanceJpa.findByNsInstanceAndNsTaskId(nsInstance, id);
		return res.size();
	}

	public int countLiveInstanceOfNsd(final NsdInstance nsInstance, final UUID id) {
		final List<NsLiveInstance> res = nsLiveInstanceJpa.findByNsInstanceAndNsTaskId(nsInstance, id);
		return res.size();
	}

	public Set<NsVirtualLink> findVlsByNsInstance(final NsdPackage nsdInfo) {
		return nsVirtualLinkJpa.findByNsdPackage(nsdInfo);
	}

	public Set<NsdPackage> findNestedNsdByNsInstance(final NsdPackage nsdInfo) {
		return nsdPackageJpa.findByNestedNsdInfoIds_Parent(nsdInfo);
	}

	public Set<VnfPackage> findVnfPackageByNsInstance(final NsdPackage nsdInfo) {
		return vnfPackageJpa.findByNsdPackages_NsdPackage_Id(nsdInfo.getId());
	}

	public NsdInstance save(final NsdInstance nsInstance) {
		return nsdInstanceJpa.save(nsInstance);
	}

	public void delete(final UUID nsInstanceUuid) {
		nsdInstanceJpa.deleteById(nsInstanceUuid);
	}

	public NsdInstance findById(final UUID nsUuid) {
		return nsdInstanceJpa.findById(nsUuid).orElseThrow(() -> new NotFoundException("Not found " + nsUuid));
	}

	public List<NsdInstance> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<NsdInstance>) sq.getCriteria(filter, NsdInstance.class)
				.getResultStream().collect(Collectors.toList());
	}

}
