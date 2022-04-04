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
package com.ubiqube.etsi.mano.repository.msa;

import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.ManoResource;

@Service
public class ContentManagerMsa implements ContentManager {
	private final Low lowDriver;

	public ContentManagerMsa(final Low lowDriver) {
		super();
		this.lowDriver = lowDriver;
	}

	@Override
	public void store(final Path filename, final InputStream stream) {
		lowDriver.add(filename.toString(), stream);
	}

	@Override
	public ManoResource load(final Path filename) {
		return lowDriver.get(filename.toString());
	}

	@Override
	public void mkdir(final Path path) {
		lowDriver.mkdir(path.toString());
	}

	@Override
	public void delete(final Path path) {
		lowDriver.delete(path.toString());
	}

}
