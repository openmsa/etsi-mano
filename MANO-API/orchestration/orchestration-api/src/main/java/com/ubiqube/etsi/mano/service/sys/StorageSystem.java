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
package com.ubiqube.etsi.mano.service.sys;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

/**
 * Maybe we can split between ObjectStorage, and other Storages.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface StorageSystem extends System {
	void deleteStorage(String resourceId);

	void deleteObjectStorage(String resourceId);

	Optional<SoftwareImage> getSwImageMatching(SoftwareImage img);

	SoftwareImage uploadSoftwareImage(SoftwareImage img);

	String createStorage(VnfStorage vnfStorage, final String aliasName);

	String createObjectStorage(final VnfStorage vnfStorage);

	@Nonnull
	SysImage getImagesInformations(String name);
}
