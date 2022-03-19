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
package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

@Primary
public interface VnfPackageJpa extends CrudRepository<VnfPackage, UUID> {

	Optional<VnfPackage> findByDescriptorId(String descriptorId);

	Optional<VnfPackage> findByDescriptorIdAndVnfSoftwareVersion(String name, String version);

	Optional<VnfPackage> findByDescriptorIdAndFlavorIdAndVnfdVersion(String descriptorId, String flavorId, String versionId);

	Optional<VnfPackage> findByVnfdIdAndOnboardingState(String id, OnboardingStateType state);

	void deleteByVnfdId(String fromString);

}
