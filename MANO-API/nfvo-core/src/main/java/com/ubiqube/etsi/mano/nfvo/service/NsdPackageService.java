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
package com.ubiqube.etsi.mano.nfvo.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageNsdPackageJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageVnfPackageJpa;

@Service
public class NsdPackageService {

	private final NsdPackageJpa nsdPackageJpa;

	private final NsdPackageNsdPackageJpa nsdPackageNsdPackageJpa;

	private final NsdPackageVnfPackageJpa nsdPackageVnfPackageJpa;

	public NsdPackageService(final NsdPackageJpa nsdPackageJpa, final NsdPackageNsdPackageJpa nsdPackageNsdPackageJpa, final NsdPackageVnfPackageJpa nsdPackageVnfPackageJpa) {
		this.nsdPackageJpa = nsdPackageJpa;
		this.nsdPackageNsdPackageJpa = nsdPackageNsdPackageJpa;
		this.nsdPackageVnfPackageJpa = nsdPackageVnfPackageJpa;
	}

	public Set<NsdPackageVnfPackage> findVnfPackageByNsPackage(final NsdPackage nsdPackage) {
		return nsdPackageVnfPackageJpa.findByNsdPackage(nsdPackage);
	}

	public Set<NsdPackageNsdPackage> findNestedNsdByNsdPackage(final NsdPackage nsdPackage) {
		return nsdPackageNsdPackageJpa.findByParent(nsdPackage);
	}

	public NsdPackage findById(final UUID nsdId) {
		return nsdPackageJpa.findById(nsdId).orElseThrow(() -> new NotFoundException("Not found " + nsdId));
	}

	public NsdPackage save(final NsdPackage nsd) {
		return nsdPackageJpa.save(nsd);
	}

	public NsdPackage findByNsdId(final String _nsdId) {
		return nsdPackageJpa.findByNsdId(_nsdId).orElseThrow(() -> new GenericException("Could not find nsdId: " + _nsdId));
	}

}
