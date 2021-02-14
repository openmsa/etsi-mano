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
package com.ubiqube.etsi.mec.meo.v211.controller;

import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mec.meo.v211.model.grant.Grant;
import com.ubiqube.etsi.mec.meo.v211.model.grant.SoftwareImages;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.FeatureDependency;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OrikaMec221Meo implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(FeatureDependency.class, com.ubiqube.etsi.mano.dao.mec.pkg.FeatureDependency.class)
				.field("featureName", "name")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(Grant.class, GrantResponse.class)
				.field("appInstanceId", "vnfInstanceId")
				.field("appLcmOpOccId", "vnfLcmOpOccId")
				.field("links.appLcmOpOcc.href", "lcmLink")
				.field("links.appInstance.href", "instanceLink")
				.field("vimAssets.vimFlavourId", "vimAssets.computeResourceFlavours").customize(new FlavourMapper())
				.field("vimAssets.softwareImages", "vimAssets.computeResourceFlavours")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(SoftwareImages.class, VimSoftwareImageEntity.class)
				.field("appDSoftwareImageId", "vnfdSoftwareImageId")
				.field("vimSoftwareImageId", "vimSoftwareImageId")
				.byDefault()
				.register();
	}

	public class FlavourMapper extends CustomMapper<Grant, GrantResponse> {

		@Override
		public void mapAtoB(final Grant a, final GrantResponse b, final MappingContext context) {
			final Set<VimComputeResourceFlavourEntity> flavors = b.getVimAssets().getComputeResourceFlavours();
			final VimComputeResourceFlavourEntity vimFlavor = new VimComputeResourceFlavourEntity();
			vimFlavor.setVimFlavourId(a.getVimAssets().getVimFlavourId());
			super.mapAtoB(a, b, context);
		}

		@Override
		public void mapBtoA(final GrantResponse b, final Grant a, final MappingContext context) {
			String ret = null;
			final Iterator<VimComputeResourceFlavourEntity> ite = b.getVimAssets().getComputeResourceFlavours().iterator();
			if (ite.hasNext()) {
				ret = ite.next().getVimFlavourId();
			}
			a.getVimAssets().setVimFlavourId(ret);
			super.mapBtoA(b, a, context);
		}

	}
}
