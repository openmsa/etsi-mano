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
package com.ubiqube.etsi.mano.nfvo.controller.vnf;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.MetaStreamResource;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.MetaStream;
import com.ubiqube.etsi.mano.repository.ResetOnCloseInputStream;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;
import com.ubiqube.etsi.mano.service.VnfPackageService;

import ma.glasnost.orika.MapperFacade;

/**
 * This implementation cover VNFO + NFVM & VNFO only.
 *
 * @author ovi@ubiqube.com
 *
 */
@Service
public class VnfManagement extends SearchableService implements VnfPackageManagement {
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);

	private final VnfPackageRepository vnfPackageRepository;
	private final VnfPackageService vnfPackageService;
	private final MapperFacade mapper;

	public VnfManagement(final VnfPackageRepository vnfPackageRepository, final MapperFacade mapper, final VnfPackageService vnfPackageService, final EntityManager em,
			final ManoSearchResponseService searchService, final GrammarParser grammarParser) {
		super(searchService, em, VnfPackage.class, grammarParser);
		this.vnfPackageRepository = vnfPackageRepository;
		this.mapper = mapper;
		this.vnfPackageService = vnfPackageService;
		LOG.info("Starting VNF Package Management For NFVO+VNFM or NFVO Only Management.");
	}

	@Override
	public <U> U vnfPackagesVnfPkgIdGet(final UUID vnfPkgId, final Class<U> u) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		return mapper.map(vnfPackage, u);
	}

	@Override
	public VnfPackage vnfPackagesVnfPkgIdGet(final UUID vnfPkgId) {
		return vnfPackageRepository.get(vnfPkgId);
	}

	@Override
	public List<VnfPackage> vnfPackagesGet(final String filter) {
		return vnfPackageRepository.query(filter);
	}

	/**
	 * We should not reply a Response here.
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param _accept
	 * @param rangeHeader
	 * @return
	 */
	@SuppressWarnings("resource")
	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final UUID vnfPkgId, final String artifactPath) {
		vnfPackageRepository.get(vnfPkgId);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final ZipInputStream zis = new ZipInputStream(content.getInputStream());
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (entry.getName().equals(artifactPath)) {
					final ResetOnCloseInputStream is = new ResetOnCloseInputStream(zis);
					final MetaStreamResource res = new MetaStreamResource(new MetaStream(is, 350, "hh"));
					return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
							.contentType(MediaTypeFactory.getMediaType(res).orElse(MediaType.APPLICATION_OCTET_STREAM))
							.body(res);

				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	@SuppressWarnings("resource")
	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final UUID vnfPkgId, final boolean includeSignature) {
		vnfPackageRepository.get(vnfPkgId);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final MetaStreamResource res = new MetaStreamResource(new MetaStream(content.getInputStream(), 350, "hh"));
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaTypeFactory
						.getMediaType(res)
						.orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@SuppressWarnings("resource")
	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(final UUID vnfPkgId) {
		vnfPackageRepository.get(vnfPkgId);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final MetaStreamResource res = new MetaStreamResource(new MetaStream(content.getInputStream(), 350, "hh"));
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaTypeFactory
				.getMediaType(res).orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@Override
	public ResponseEntity<Void> getPackageManifest(final UUID vnfPkgId, @Valid final String includeSignatures) {
		// TODO: Look inside csar manifest for any metadata entry.
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfdIdArtifactsArtifactPathGet(final UUID vnfdId, final String artifactPath) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPackage.getId(), artifactPath);
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(final UUID vnfdId, @Valid final String includeSignatures) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return getPackageManifest(vnfPackage.getId(), includeSignatures);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdPackageContentGet(final UUID vnfdId) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdPackageContentGet(vnfPackage.getId());
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final UUID vnfdId, @Valid final String includeSignatures) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdVnfdGet(vnfPackage.getId(), null != includeSignatures);
	}

	@Override
	public <U> U onboardedVnfPackagesVnfdIdGet(final UUID vnfdId, final Class<U> clazz) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return mapper.map(vnfPackage, clazz);
	}

	@Override
	public ResponseEntity<Resource> onboardedGetManifestByVnfd(final UUID vnfdId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdArtifactsGet(final UUID vnfdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> U vnfPackagesVnfPkgVnfdIdGet(final UUID vnfdId, final Class<U> clazz) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return mapper.map(vnfPackage, clazz);
	}

}
