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
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public class NsInstanceFactory {

	private NsInstanceFactory() {
		// Nothing.
	}

	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPackage _vnfPkgInfo) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		final VnfPackage vnfPackage = new VnfPackage();
		vnfPackage.setId(_vnfPkgInfo.getId());
		nsInstancesNsInstanceVnfInstance.setVnfPkg(vnfPackage);
		return nsInstancesNsInstanceVnfInstance;
	}
}
