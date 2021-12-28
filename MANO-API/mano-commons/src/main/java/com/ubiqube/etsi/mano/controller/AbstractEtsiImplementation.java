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
package com.ubiqube.etsi.mano.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractEtsiImplementation implements EtsiImplementation {
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public List<Protocol> getProtocols() {
		final Path path = Paths.get("/", getVersion(), "mano-versions.json");
		try (final InputStream in = this.getClass().getResourceAsStream(path.toString())) {
			final TypeReference<List<Protocol>> tr = new TypeReference<>() {
				//
			};
			return mapper.readValue(in, tr);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}
}
