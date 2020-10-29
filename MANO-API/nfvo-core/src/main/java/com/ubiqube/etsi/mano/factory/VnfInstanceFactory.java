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
package com.ubiqube.etsi.mano.factory;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public class VnfInstanceFactory {
	private VnfInstanceFactory() {
		// Nothing
	}

	/**
	 * Duplicate of NsInstanceFactory.
	 *
	 * @param vnfInstance
	 * @param vimId
	 * @return
	 */
	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance vnfInstance, final String vimId) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setId(vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setVnfdId(vnfInstance.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceDescription(vnfInstance.getVnfInstanceDescription());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceName(vnfInstance.getVnfInstanceName());
		nsInstancesNsInstanceVnfInstance.setVnfPkg(vnfInstance.getVnfPkg());
		nsInstancesNsInstanceVnfInstance.setVnfProductName(vnfInstance.getVnfProductName());
		nsInstancesNsInstanceVnfInstance.setVnfProvider(vnfInstance.getVnfProvider());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(vnfInstance.getVnfdVersion());
		nsInstancesNsInstanceVnfInstance.setVnfSoftwareVersion(vnfInstance.getVnfSoftwareVersion());
		return nsInstancesNsInstanceVnfInstance;
	}
}
