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

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.sys.SysImage;
import com.ubiqube.etsi.mano.vim.dto.SwImage;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Storage {
	void deleteStorage(String resourceId);

	void deleteObjectStorage(String resourceId);

	Optional<SwImage> getSwImageMatching(SoftwareImage img);

	SwImage uploadSoftwareImage(InputStream is, SoftwareImage softwareImage);

	String createStorage(VnfStorage vnfStorage, final String aliasName);

	String createObjectStorage(final VnfStorage vnfStorage);

	@Nonnull
	SysImage getImagesInformations(String name);

	List<SwImage> getImageList();

	SoftwareImage getImageDetail(String id);

}
