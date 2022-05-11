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
package com.ubiqube.etsi.mano.service.pkg.tosca.vnf;

import com.ubiqube.etsi.mano.dao.mano.ContainerFormatType;
import com.ubiqube.etsi.mano.dao.mano.DiskFormatType;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import tosca.artifacts.nfv.SwImage;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SwImageMapper extends CustomMapper<SwImage, SoftwareImage> {

	@Override
	public void mapAtoB(final SwImage a, final SoftwareImage b, final MappingContext context) {
		b.setContainerFormat(ContainerFormatType.fromValue(a.getContainerFormat()));
		b.setDiskFormat(DiskFormatType.fromValue(a.getDiskFormat()));
		super.mapAtoB(a, b, context);
	}

}
