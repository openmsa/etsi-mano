package com.ubiqube.etsi.mano.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.jpa.NsSapJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageNsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageVnfPackageJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;

@Service
public class NsdPackageService {

	private final NsSapJpa nsSapJpa;

	private final VnfPackageJpa vnfPackageJpa;

	private final NsdPackageJpa nsdPackageJpa;
	private final NsdPackageNsdPackageJpa nsdPackageNsdPackageJpa;
	private final NsdPackageVnfPackageJpa nsdPackageVnfPackageJpa;

	public NsdPackageService(final NsSapJpa _nsSapJpa, final VnfPackageJpa _vnfPackageJpa, final NsdPackageJpa _nsdPackageJpa, final NsdPackageNsdPackageJpa _nsdPackageNsdPackageJpa, final NsdPackageVnfPackageJpa _nsdPackageVnfPackageJpa) {
		nsSapJpa = _nsSapJpa;
		vnfPackageJpa = _vnfPackageJpa;
		nsdPackageJpa = _nsdPackageJpa;
		nsdPackageNsdPackageJpa = _nsdPackageNsdPackageJpa;
		nsdPackageVnfPackageJpa = _nsdPackageVnfPackageJpa;
	}

	public Set<NsSap> getSapByNsdPackage(final NsdPackage nsdPackage) {
		return nsSapJpa.findByNsdPackage(nsdPackage);
	}

	public Set<NsdPackageVnfPackage> findVnfPackageByNsPackage(final NsdPackage nsdPackage) {
		return nsdPackageVnfPackageJpa.findByNsdPackage(nsdPackage);
	}

	public Set<NsdPackageNsdPackage> findNestedNsdByNsdPackage(final NsdPackage nsdPackage) {
		return nsdPackageNsdPackageJpa.findByChild(nsdPackage);
	}

}
