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

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AdditionalArtefactMapper extends CustomMapper<ArtefactInformations, AdditionalArtifact> {

	private static final Logger LOG = LoggerFactory.getLogger(AdditionalArtefactMapper.class);

	@Override
	public void mapAtoB(final ArtefactInformations a, final AdditionalArtifact b, final MappingContext context) {
		final String p = a.getPath();
		if (null != p) {
			if (isRemote(p)) {
				b.setArtifactURI(p);
				b.setArtifactPath(null);
			} else {
				b.setArtifactPath(p);
				b.setArtifactURI(null);
			}
		}
		super.mapAtoB(a, b, context);
	}

	private static boolean isRemote(final String p) {
		try {
			final URI uri = URI.create(p);
			return !uri.getScheme().isEmpty();
		} catch (final RuntimeException e) {
			LOG.trace("", e);
		}
		return false;
	}

}
