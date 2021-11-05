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
package com.ubiqube.etsi.mano.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;

@RestController
@RequestMapping("/vnfm-admin")
public class VnfmAdminController {
	private final VnfPackageJpa vnfPackageRepository;

	public VnfmAdminController(final VnfPackageJpa vnfPackageRepository) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@DeleteMapping("vnf-package/{vnfPkgId}")
	public ResponseEntity<Void> deleteVnfPackage(@PathVariable("vnfPkgId") final String vnfPkgId) {
		vnfPackageRepository.deleteById(UUID.fromString(vnfPkgId));
		return ResponseEntity.noContent().build();
	}
}
