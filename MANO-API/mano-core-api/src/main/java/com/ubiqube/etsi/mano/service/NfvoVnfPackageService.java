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
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;

@ConditionalOnMissingBean(VnfPackageService.class)
@Service
public class NfvoVnfPackageService implements VnfPackageService {

	@Override
	public List<VnfComputeAspectDelta> findAspectDeltaByAspectId(final VnfCompute vnfCompute, final String aspectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfStorage> findStorageByName(final VnfPackage vnfPackage, final String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfVl> findVirtualLnkById(final UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfStorage> findVirtualStorageById(final UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfCompute> findComputeById(final UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfExtCp> findExtCpById(final UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VduInstantiationLevel findByVnfComputeAndInstantiationLevel(final VnfCompute x, final String scaleInfoName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfPackage findById(final VnfPackage vnfPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfPackage findById(final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfComputeAndLevel(final VnfPackage vnfPackage, final String level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfPacckage(final VnfPackage vnfPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfPackage save(final VnfPackage vnfPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfPackage> findByDescriptorId(final String descriptorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdFlavorIdVnfdVersion(final String descriptorId, final String flavorId, final String versionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdAndSoftwareVersion(final String name, final String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<VnfLinkPort> findVnfVirtualLinks(final VnfPackage vnfPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfPackage findByVnfdId(final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink) {
		// TODO Auto-generated method stub
		return null;
	}

}
