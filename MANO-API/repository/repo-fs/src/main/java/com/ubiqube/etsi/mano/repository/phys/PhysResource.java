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
package com.ubiqube.etsi.mano.repository.phys;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.RepositoryException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PhysResource implements ManoResource {
	private long size;
	private String fileName;

	public PhysResource(final long size, final String fileName) {
		super();
		this.size = size;
		this.fileName = fileName;
	}

	@Override
	public InputStream getInputStream() {
		try {
			return Files.newInputStream(Paths.get(fileName));
		} catch (final IOException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

}
