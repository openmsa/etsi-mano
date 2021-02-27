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
package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.utils.MimeType;
import com.ubiqube.etsi.mano.utils.SpringUtil;

import ma.glasnost.orika.MapperFacade;

/**
 * This implementation cover VNFO + NFVM & VNFO only.
 *
 * @author ovi@ubiqube.com
 *
 */
@Service
public class VnfManagement extends SearchableService implements VnfPackageManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);

	private final VnfPackageRepository vnfPackageRepository;
	private final VnfPackageService vnfPackageService;
	private final MapperFacade mapper;

	public VnfManagement(final VnfPackageRepository _vnfPackageRepository, final MapperFacade _mapper, final VnfPackageService _vnfPackageService, final EntityManager _em, final ManoSearchResponseService searchService) {
		super(searchService, _em, VnfPackage.class);
		vnfPackageRepository = _vnfPackageRepository;
		mapper = _mapper;
		vnfPackageService = _vnfPackageService;
		LOG.info("Starting VNF Package Management For NFVO+VNFM or NFVO Only Management.");
	}

	@Override
	public <U> U vnfPackagesVnfPkgIdGet(final UUID vnfPkgId, final Class<U> u) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		return mapper.map(vnfPackage, u);
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
	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final UUID vnfPkgId, final String artifactPath, final String rangeHeader) {
		vnfPackageRepository.get(vnfPkgId);
		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");

		final InputStream bis = new ByteArrayInputStream(content);
		final ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (entry.getName().equals(artifactPath)) {
					return handleArtifact(zis, rangeHeader);

				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final UUID vnfPkgId) {
		vnfPackageRepository.get(vnfPkgId);

		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final String mime = MimeType.findMatch(content);
		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
		final BodyBuilder bodyBuilder = ResponseEntity.ok();
		handleMimeType(bodyBuilder, mime);
		return bodyBuilder.body(resource);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final UUID _vnfPkgId, final String _range) {
		vnfPackageRepository.get(_vnfPkgId);
		final byte[] bytes = vnfPackageRepository.getBinary(_vnfPkgId, "vnfd");
		return SpringUtil.handleBytes(bytes, _range);
	}

	private static ResponseEntity<List<ResourceRegion>> handleArtifact(final ZipInputStream zis, final String _range) throws IOException {
		final byte[] zcontent = StreamUtils.copyToByteArray(zis);
		return SpringUtil.handleBytes(zcontent, _range);
	}

	private static void handleMimeType(final BodyBuilder bodyBuilder, final String mime) {
		if (MediaType.APPLICATION_JSON_VALUE.contentEquals(mime)) {
			bodyBuilder.contentType(MediaType.APPLICATION_JSON);
		} else if (APPLICATION_ZIP.equals(mime)) {
			bodyBuilder.header("Content-Type", mime);
		} else {
			bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		}
	}

	@Override
	public ResponseEntity<Void> getPackageManifest(final UUID vnfPkgId, @Valid final String includeSignatures) {
		// TODO: Look inside csar manifest for any metadata entry.
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfdIdArtifactsArtifactPathGet(final UUID vnfdId, final String artifactPath, final String range) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPackage.getId(), artifactPath, range);
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(final UUID vnfdId, @Valid final String includeSignatures) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return getPackageManifest(vnfPackage.getId(), includeSignatures);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final UUID vnfdId, final String range) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdPackageContentGet(vnfPackage.getId(), range);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final UUID vnfdId, @Valid final String includeSignatures) {
		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(vnfdId);
		return vnfPackagesVnfPkgIdVnfdGet(vnfPackage.getId());
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
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsGet(final UUID vnfdId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

}
