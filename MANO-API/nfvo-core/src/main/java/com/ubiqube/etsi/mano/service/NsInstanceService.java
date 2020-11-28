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

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.NsSapJpa;
import com.ubiqube.etsi.mano.jpa.NsVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.NsVnfPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdInstanceJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;

@Service
public class NsInstanceService {
	private final NsSapJpa nsSapJpa;

	private final NsVirtualLinkJpa nsVirtualLinkJpa;

	private final NsdPackageJpa nsdPackageJpa;

	private final NsVnfPackageJpa vnfPackageJpa;

	private final NsdInstanceJpa nsdInstanceJpa;

	public NsInstanceService(final NsSapJpa _nsSapJpa, final NsVirtualLinkJpa _nsVirtualLinkJpa, final NsdPackageJpa _nsdPackageJpa, final NsVnfPackageJpa _vnfPackageJpa, final NsdInstanceJpa _nsdInstanceJpa) {
		nsSapJpa = _nsSapJpa;
		nsVirtualLinkJpa = _nsVirtualLinkJpa;
		nsdPackageJpa = _nsdPackageJpa;
		vnfPackageJpa = _vnfPackageJpa;
		nsdInstanceJpa = _nsdInstanceJpa;
	}

	public int countLiveInstanceOfSap(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfVirtualLink(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfVnf(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfNsd(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<NsSap> findSapsByNsInstance(final NsdPackage nsdInfo) {
		return nsSapJpa.findByNsdPackage(nsdInfo);
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

}
