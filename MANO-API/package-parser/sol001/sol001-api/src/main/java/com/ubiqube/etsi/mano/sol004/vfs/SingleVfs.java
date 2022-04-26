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
package com.ubiqube.etsi.mano.sol004.vfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.Resolver;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SingleVfs implements VirtualFileSystem {

	private final String filename;
	private final Resolver resolver;

	public SingleVfs(final String filename) {
		this.filename = filename;
		this.resolver = new Resolver(new File(filename));
	}

	@Override
	public byte[] getFileContent(final String filename) {
		if (!filename.equals(this.filename)) {
			return new byte[0];
		}
		return readFile();
	}

	private byte[] readFile() {
		try (FileInputStream fis = new FileInputStream(this.filename)) {
			return fis.readAllBytes();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public boolean exist(final String filename) {
		return false;
	}

	@Override
	public List<String> getFileMatching(final String filenameWildcard) {
		final Pattern p = Pattern.compile(filenameWildcard);
		if (p.matcher(filename).find()) {
			return List.of(filename);
		}
		return List.of();
	}

	@Override
	public InputStream getInputStream(final String fileName) {
		if (!filename.equals(this.filename)) {
			return null;
		}
		try {
			return new FileInputStream(fileName);
		} catch (final FileNotFoundException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	public Map<String, String> getMetaInfo(final String path) {
		return Map.of();
	}

}
