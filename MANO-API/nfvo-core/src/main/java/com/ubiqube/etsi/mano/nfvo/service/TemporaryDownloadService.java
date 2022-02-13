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

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload;
import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload.ObjectType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.nfvo.jpa.TemporaryDownloadJpa;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class TemporaryDownloadService {
	private final TemporaryDownloadJpa temporaryJpa;
	private final NsdRepository nsdRepository;
	private final VnfPackageRepository vnfRepository;

	public TemporaryDownloadService(final TemporaryDownloadJpa temporaryJpa, final NsdRepository nsdRepository, final VnfPackageRepository vnfRepository) {
		this.temporaryJpa = temporaryJpa;
		this.nsdRepository = nsdRepository;
		this.vnfRepository = vnfRepository;
	}

	public TemporaryDownload exposeDocument(final ObjectType objectType, final UUID id) {
		final TemporaryDownload td = new TemporaryDownload();
		td.setObjectId(id);
		td.setObjectType(objectType);
		final String str = generateEphemeralId();
		td.setId(str);

		td.setExpirationDate(Date.from(LocalDateTime.now().plusMinutes(25).atZone(ZoneId.systemDefault()).toInstant()));
		return temporaryJpa.save(td);
	}

	public ManoResource getDocument(final String id) {
		final Optional<TemporaryDownload> odoc = temporaryJpa.findByIdAndExpirationDateAfter(id, new Date());
		final TemporaryDownload doc = odoc.orElseThrow(() -> new NotFoundException("No temporary download available with id: " + id));
		final String objectId = doc.getObjectId().toString();
		if (doc.getObjectType() == ObjectType.NSD) {
			return nsdRepository.getBinary(getSafeUUID(objectId), "nsd");
		}
		if (doc.getObjectType() == ObjectType.VNFD) {
			return vnfRepository.getBinary(getSafeUUID(objectId), "vnfd");
		}
		throw new GenericException("Unknown objectType: " + doc.getObjectType());
	}

	private static String generateEphemeralId() {
		final SecureRandom rnd = new SecureRandom();
		final byte[] bytes = rnd.generateSeed(64);

		final MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		final byte[] hashbytes = digest.digest(bytes);
		return bytesToHex(hashbytes);
	}

	private static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
