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
package com.ubiqube.etsi.mano.vim.aws;

import java.util.Optional;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.orchestrator.VimImage;
import com.ubiqube.etsi.mano.service.vim.Storage;

public class AwsStorage implements Storage {
	private static final long GIGA = 1024 * 1024 * 1024L;

	private final AmazonEC2 client;

	private final AWSCredentialsProvider credential;

	public AwsStorage(final AmazonEC2 connection, final AWSCredentialsProvider credential) {
		this.client = connection;
		this.credential = credential;
	}

	@Override
	public void deleteStorage(final String resourceId) {
		final DeleteVolumeRequest req = new DeleteVolumeRequest()
				.withVolumeId(resourceId)
				.withRequestCredentialsProvider(credential);
		client.deleteVolume(req);
	}

	@Override
	public void deleteObjectStorage(final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<SoftwareImage> getSwImageMatching(final SoftwareImage img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoftwareImage uploadSoftwareImage(final SoftwareImage img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createStorage(final VnfStorage vnfStorage, final String aliasName) {
		final CreateVolumeRequest req = new CreateVolumeRequest()
				.withSize((int) (vnfStorage.getSize() / GIGA))
				.withRequestCredentialsProvider(credential);
		return client.createVolume(req).getVolume().getVolumeId();
	}

	@Override
	public String createObjectStorage(final VnfStorage vnfStorage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VimImage getImagesInformations(final String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
