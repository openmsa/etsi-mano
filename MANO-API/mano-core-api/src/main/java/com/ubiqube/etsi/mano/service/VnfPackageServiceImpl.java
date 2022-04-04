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

import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPackageServiceImpl extends SearchableService implements VnfPackageService {
	private final VnfPackageRepository vnfPackageRepository;

	private final VnfPackageJpa vnfPackageJpa;

	private final VnfInstanceJpa vnfInstanceJpa;

	public VnfPackageServiceImpl(final VnfPackageJpa vnfPackageJpa, final EntityManager em, final ManoSearchResponseService searchService, final VnfInstanceJpa vnfInstanceJpa,
			final GrammarParser grammarParser, final VnfPackageRepository vnfPackageRepository) {
		super(searchService, em, VnfPackage.class, grammarParser);
		this.vnfPackageJpa = vnfPackageJpa;
		this.vnfInstanceJpa = vnfInstanceJpa;
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@Override
	public VnfPackage findById(final UUID vnfPkgId) {
		final VnfPackage ret = vnfPackageJpa.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("VNF Package: " + vnfPkgId + " not found."));
		final int i = vnfInstanceJpa.countByVnfPkgId(vnfPkgId);
		ret.setUsageState(i == 0 ? PackageUsageState.NOT_IN_USE : PackageUsageState.IN_USE);
		return ret;
	}

	@Override
	public VnfPackage save(final VnfPackage vnfPackage) {
		return vnfPackageRepository.save(vnfPackage);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorId(final String descriptorId) {
		return vnfPackageJpa.findByDescriptorId(descriptorId);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdFlavorIdVnfdVersion(final String descriptorId, final String flavorId, final String versionId) {
		return vnfPackageJpa.findByDescriptorIdAndFlavorIdAndVnfdVersion(descriptorId, flavorId, versionId);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdAndSoftwareVersion(final String name, final String version) {
		return vnfPackageJpa.findByDescriptorIdAndVnfSoftwareVersion(name, version);
	}

	@Override
	public VnfPackage findByVnfdId(final UUID id) {
		return vnfPackageJpa.findByVnfdIdAndOnboardingState(id.toString(), OnboardingStateType.ONBOARDED).orElseThrow(() -> new NotFoundException("Could not find vnfdId: " + id + ", or it is not ONBOARDED."));
	}

	@Override
	public void delete(final UUID id) {
		vnfPackageJpa.deleteById(id);
	}

	@Override
	public Path getPathByVnfdId(final UUID fromString) {
		return vnfPackageRepository.getPathByVnfdId(fromString);
	}

}
