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
package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.VnfPackageFactory;
import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;

public class OrikaTest {

	private final DefaultMapperFactory mapperFactory;

	public OrikaTest() {
		final OrikaMapperVnfm261 orikaConfiguration = new OrikaMapperVnfm261();
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testMapVnfArtifactChecksum() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();

		final VnfPackage vnf = VnfPackageFactory.createVnfPkgInfo(new HashMap<String, String>());
		final AdditionalArtifact additionalArtifactsItem = new AdditionalArtifact();
		final Checksum checksum = new Checksum();
		checksum.setAlgorithm("SHA-512");
		checksum.setHash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.setArtifactPath("/path");
		additionalArtifactsItem.setChecksum(checksum);

		final Set<AdditionalArtifact> additionalArtifacts = new HashSet<>();
		additionalArtifacts.add(additionalArtifactsItem);
		vnf.setAdditionalArtifacts(additionalArtifacts);
		final VnfPackage cnv = mapper.map(vnf, VnfPackage.class);
		System.out.println("" + cnv);
		assertNotNull(cnv.getVnfCompute());
	}

}
