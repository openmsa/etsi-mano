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
package com.ubiqube.etsi.mano.service.event.images;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.VirtualFileSystem;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageManager;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageReader;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vim.dto.SwImage;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SoftwareImageService {

	private static final Logger LOG = LoggerFactory.getLogger(SoftwareImageService.class);

	private final VnfPackageRepository repository;
	private final VimManager vimManager;
	private final VnfPackageManager packageManager;

	public SoftwareImageService(final VnfPackageRepository repository, final VimManager vimManager, final VnfPackageManager packageManager) {
		super();
		this.repository = repository;
		this.vimManager = vimManager;
		this.packageManager = packageManager;
	}

	public List<SoftwareImage> getFullDetailImageList(final VimConnectionInformation vimConn) {
		final Vim vim = vimManager.getVimById(vimConn.getId());
		final List<SwImage> imgList = vim.storage(vimConn).getImageList();
		return imgList.stream().map(x -> vim.storage(vimConn).getImageDetail(x.getVimResourceId())).toList();
	}

	public SoftwareImage getImage(final List<SoftwareImage> sws, final SoftwareImage swIn, final VimConnectionInformation vimConn, @NotNull final UUID vnfPkgId) {
		final Optional<SoftwareImage> img = sws.stream().filter(x -> filterImage(x, swIn)).findFirst();
		if (img.isPresent()) {
			return img.get();
		}
		LOG.info("Uploading image: {}", swIn.getName());
		final VnfPackage vnfPkg = repository.get(vnfPkgId);
		final PackageDescriptor<VnfPackageReader> provider = packageManager.getProviderFor(vnfPkg.getPackageProvider());
		final ManoResource res = repository.getBinary(vnfPkgId, Constants.REPOSITORY_FILENAME_PACKAGE);
		final VirtualFileSystem vfs = provider.getFileSystem(res);
		return uploadImage(vfs, swIn, vimConn);
	}

	private SoftwareImage uploadImage(final VirtualFileSystem vfs, final SoftwareImage swIn, final VimConnectionInformation vimConn) {
		final Vim vim = vimManager.getVimById(vimConn.getId());
		try (InputStream is = vfs.getInputStream(swIn.getImagePath())) {
			vim.storage(vimConn).uploadSoftwareImage(is, swIn);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return swIn;
	}

	private static boolean filterImage(final SoftwareImage vimImage, final SoftwareImage swIn) {
		if (checksumComparable(vimImage.getChecksum(), swIn.getChecksum())) {
			return checkHash(vimImage.getChecksum(), swIn.getChecksum());
		}
		return vimImage.getName().equals(swIn.getName());
	}

	private static boolean checksumComparable(final Checksum checksum, final Checksum checksum2) {
		return null != checksum && checksum2 != null;
	}

	private static boolean checkHash(final Checksum checksum, final Checksum checksum2) {
		final String alg1 = checksum.getAlgorithm();
		final String alg2 = checksum2.getAlgorithm();
		if (alg1 == null || alg2 == null || !alg1.equals(alg2)) {
			return false;
		}
		return checksum.getHash().equals(checksum2.getHash());
	}

}
