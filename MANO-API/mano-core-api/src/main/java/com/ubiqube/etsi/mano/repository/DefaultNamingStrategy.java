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
package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.config.properties.ManoRepositoryProperties;

@Service
public class DefaultNamingStrategy implements NamingStrategy {
	private static final ClassPathConverter CP_CONVERTER = new ClassPathConverter();
	private final String root;

	public DefaultNamingStrategy(final ManoRepositoryProperties props) {
		root = props.getPhysRoot();
	}

	protected static final String sanitize(final String filename) {
		// It's ok for path segment not for a full path.
		return filename.replaceAll("\\.+", ".");
	}

	@Override
	public Path getRoot(final Class<?> clazz) {
		return Paths.get(root, CP_CONVERTER.convert(clazz));
	}

	@Override
	public Path getRoot(final Class<?> clazz, final UUID id) {
		return Paths.get(root, CP_CONVERTER.convert(clazz), id.toString());
	}

	@Override
	public Path getRoot(final Class<?> clazz, final UUID id, final String filename) {
		return Paths.get(root, CP_CONVERTER.convert(clazz), id.toString(), sanitize(filename));
	}

	@Override
	public Path getRoot() {
		return Paths.get(root);
	}

}
