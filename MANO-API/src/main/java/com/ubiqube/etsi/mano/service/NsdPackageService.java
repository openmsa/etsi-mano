package com.ubiqube.etsi.mano.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.NsSapJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageNsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageVnfPackageJpa;

@Service
public class NsdPackageService {

	private final NsSapJpa nsSapJpa;

	private final NsdPackageJpa nsdPackageJpa;

	private final NsdPackageNsdPackageJpa nsdPackageNsdPackageJpa;

	private final NsdPackageVnfPackageJpa nsdPackageVnfPackageJpa;

	public NsdPackageService(final NsSapJpa _nsSapJpa, final NsdPackageJpa _nsdPackageJpa, final NsdPackageNsdPackageJpa _nsdPackageNsdPackageJpa, final NsdPackageVnfPackageJpa _nsdPackageVnfPackageJpa) {
		nsSapJpa = _nsSapJpa;
		nsdPackageJpa = _nsdPackageJpa;
		nsdPackageNsdPackageJpa = _nsdPackageNsdPackageJpa;
		nsdPackageVnfPackageJpa = _nsdPackageVnfPackageJpa;
	}

	public Set<NsSap> getSapByNsdPackage(final NsdPackage nsdPackage) {
		return nsSapJpa.findByNsdPackage(nsdPackage);
	}

	public Set<NsSap> getSapByNsdPackageId(final UUID id) {
		return nsSapJpa.findByNsdPackageId(id);
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

}
