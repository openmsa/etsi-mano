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
