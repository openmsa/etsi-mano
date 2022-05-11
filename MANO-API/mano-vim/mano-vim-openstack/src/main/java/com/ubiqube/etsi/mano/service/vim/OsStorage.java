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
package com.ubiqube.etsi.mano.service.vim;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.builder.ImageBuilder;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.Volume.Status;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ContainerFormatType;
import com.ubiqube.etsi.mano.dao.mano.DiskFormatType;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.service.sys.SysImage;
import com.ubiqube.etsi.mano.vim.dto.SwImage;

import ma.glasnost.orika.MapperFacade;

public class OsStorage implements Storage {

	private static final Logger LOG = LoggerFactory.getLogger(OsStorage.class);

	private static final long GIGA = 1024 * 1024 * 1024L;

	private final OSClientV3 os;

	private final MapperFacade mapper;

	public OsStorage(final OSClientV3 os, final MapperFacade mapper) {
		this.os = os;
		this.mapper = mapper;
	}

	@Override
	public void deleteStorage(final String resourceId) {
		os.blockStorage().volumes().resetState(resourceId, Status.AVAILABLE);
		checkResult(os.blockStorage().volumes().delete(resourceId));
	}

	@Override
	public void deleteObjectStorage(final String resourceId) {
		checkResult(os.objectStorage().containers().delete(resourceId));
	}

	@Override
	public Optional<SwImage> getSwImageMatching(final SoftwareImage img) {
		// XXX: Checksum is not checked, and checksum exist in os.image()
		final Optional<? extends Image> image = os.imagesV2().list().stream()
				.filter(x -> x.getName().equals(img.getName()))
				.findFirst();
		if (image.isPresent()) {
			final SwImage swImage = new SwImage(image.get().getId());
			return Optional.of(swImage);
		}
		return Optional.empty();
	}

	@Override
	public SwImage uploadSoftwareImage(final InputStream is, final SoftwareImage softwareImage) {
		try (Payload<InputStream> payload = Payloads.create(is)) {
			return doUpload(softwareImage, payload);
		} catch (final IOException e) {
			throw new VimException(e);
		}
	}

	private SwImage doUpload(final SoftwareImage img, final Payload<?> payload) {
		final ImageBuilder bImg = Builders.imageV2()
				.containerFormat(ContainerFormat.valueOf(img.getContainerFormat().toString()))
				.diskFormat(DiskFormat.valueOf(img.getDiskFormat().toString()))
				.minDisk(img.getMinDisk())
				.minRam(img.getMinRam())
				.name(img.getName());
		final org.openstack4j.model.image.v2.Image osImage = os.imagesV2().create(bImg.build());
		img.setProvider(img.getProvider());
		img.setVimId(osImage.getId());
		os.imagesV2().upload(osImage.getId(), payload, bImg.build());
		return new SwImage(osImage.getId());
	}

	@Override
	public String createStorage(final VnfStorage vnfStorage, final String aliasName) {
		final Object imgName = vnfStorage.getSoftwareImage().getName();
		final Image image = os.imagesV2().list().stream()
				.filter(x -> x.getName().equals(imgName) || x.getId().equals(imgName))
				.findFirst()
				.orElseThrow(() -> new VimException("Image " + vnfStorage.getSoftwareImage().getName() + " not found"));
		final VolumeBuilder bv = Builders.volume();
		bv.size((int) (vnfStorage.getSize() / GIGA));
		bv.name(aliasName);
		bv.imageRef(image.getId());
		final Volume res = os.blockStorage().volumes().create(bv.build());
		waitForVolumeCompletion(os.blockStorage().volumes(), res);
		return res.getId();
	}

	@Override
	public String createObjectStorage(final VnfStorage vnfStorage) {
		final ActionResponse res = os.objectStorage().containers().create(vnfStorage.getToscaName());
		LOG.debug("Object storage result success ? {}", res.isSuccess());
		return vnfStorage.getToscaName();
	}

	@Override
	@Nonnull
	public SysImage getImagesInformations(final String name) {
		final List<? extends Image> images = os.imagesV2().list();
		final Image image = images.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new VimException("Image " + name + " Cannot be found on Vim."));
		return mapper.map(image, SysImage.class);
	}

	private static void checkResult(final ActionResponse action) {
		if (!action.isSuccess() && action.getCode() != 404) {
			throw new VimException(action.getCode() + " " + action.getFault());
		}
	}

	private static void waitForVolumeCompletion(final BlockVolumeService volumes, final Volume volume) {
		Volume localVolume = volume;
		while (localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.CREATING || localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.DOWNLOADING) {
			LOG.info("Waiting for volume: {}", volume.getId());
			try {
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				LOG.error("", e);
				Thread.currentThread().interrupt();
			}
			localVolume = volumes.get(volume.getId());
		}
		LOG.info("Volume {} Done with status: {}", localVolume.getId(), localVolume.getStatus());
	}

	@Override
	public List<SwImage> getImageList() {
		final List<? extends Image> images = os.imagesV2().list();
		return images.stream().map(x -> new SwImage(x.getId())).toList();
	}

	@Override
	public SoftwareImage getImageDetail(final String id) {
		final Image image = os.imagesV2().get(id);
		final SoftwareImage si = new SoftwareImage();
		si.setArchitecture(image.getArchitecture());
		final Checksum ck = Checksum.builder()
				.algorithm(getAlgorithm(image.getChecksum()))
				.hash(image.getChecksum())
				.build();
		si.setChecksum(ck);
		si.setContainerFormat(ContainerFormatType.fromValue(image.getContainerFormat().toString()));
		si.setDiskFormat(DiskFormatType.fromValue(image.getDiskFormat().toString()));
		si.setVimId(image.getId());
		si.setMinDisk(image.getMinDisk());
		si.setMinRam(image.getMinRam());
		si.setName(image.getName());
		si.setSize(image.getSize());
		si.setVimId(id);
		return si;
	}

	private static String getAlgorithm(final String checksum) {
		final int l = checksum.length();
		return switch (l) {
		case 32 -> "MD5";
		case 40 -> "SHA-1";
		case 56 -> "SHA-224";
		case 64 -> "SHA-256";
		case 98 -> "SHA-384";
		case 128 -> "SHA-512";
		default -> null;
		};
	}

}
