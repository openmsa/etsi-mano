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
package com.ubiqube.etsi.mano.nfvo.controller.vnf;

import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfPackageController {

	VnfPackage vnfPackagesPost(Map<String, String> userData);

	void vnfPackagesVnfPkgIdDelete(UUID id);

	VnfPackage vnfPackagesVnfPkgIdPatch(UUID id, String body);

	void vnfPackagesVnfPkgIdPackageContentPut(UUID id, byte[] data, String accept);

	void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(UUID id, String contentType, String uri);

}