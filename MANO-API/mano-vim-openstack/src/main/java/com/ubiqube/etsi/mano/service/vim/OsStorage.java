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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.image.ContainerFormat;
import org.openstack4j.model.image.DiskFormat;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

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
		checkResult(os.blockStorage().volumes().delete(resourceId));
	}

	@Override
	public void deleteObjectStorage(final String resourceId) {
		checkResult(os.objectStorage().containers().delete(resourceId));
	}

	@Override
	public Optional<SoftwareImage> getSwImageMatching(final SoftwareImage img) {
		// XXX: Checksum is not comparated, and checksum exist in os.image()
		final Optional<? extends Image> image = os.compute().images().list().stream()
				.filter(x -> x.getName().equals(img.getName()))
				.findFirst();
		if (image.isPresent()) {
			final SoftwareImage swImage = mapper.map(image.get(), SoftwareImage.class);
			return Optional.of(swImage);
		}
		return Optional.empty();
	}

	@Override
	public SoftwareImage uploadSoftwareImage(final SoftwareImage img) {
		final String imagePath = img.getImagePath();
		// XXX A little bit simple.
		if (imagePath.startsWith("http")) {
			try (Payload<URL> payload = Payloads.create(new URL(imagePath))) {
				return doUpload(img, payload);
			} catch (final IOException e) {
				throw new VimException(e);
			}
		}
		try (Payload<File> payload = Payloads.create(new File(imagePath))) {
			return doUpload(img, payload);
		} catch (final IOException e) {
			throw new VimException(e);
		}
	}

	private SoftwareImage doUpload(final SoftwareImage img, final Payload<?> payload) {
		final ImageBuilder bImg = Builders.image()
				.containerFormat(ContainerFormat.valueOf(img.getContainerFormat())).diskFormat(DiskFormat.valueOf(img.getDiskFormat()))
				.minDisk(img.getMinDisk())
				.minRam(img.getMinRam())
				.size(img.getSize());
		if (null != img.getChecksum()) {
			bImg.checksum(img.getChecksum().getHash());
		}
		final org.openstack4j.model.image.Image osImage = os.images().create(bImg.build(), payload);
		img.setProvider(img.getProvider());
		img.setVimId(osImage.getId());
		return img;
	}

	@Override
	public String createStorage(final VnfStorage vnfStorage, final String aliasName) {
		final Object imgName = vnfStorage.getSoftwareImage().getName();
		final Image image = os.compute().images().list().stream()
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
	public VimImage getImagesInformations(final String name) {
		final List<? extends Image> images = os.compute().images().list();
		final Image image = images.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new VimException("Image " + name + " Cannot be found on Vim."));
		return mapper.map(image, VimImage.class);
	}

	private static void checkResult(final ActionResponse action) {
		if (!action.isSuccess() && (action.getCode() != 404)) {
			throw new VimException(action.getCode() + " " + action.getFault());
		}
	}

	private static void waitForVolumeCompletion(final BlockVolumeService volumes, final Volume volume) {
		Volume localVolume = volume;
		while ((localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.CREATING) || (localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.DOWNLOADING)) {
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

}
