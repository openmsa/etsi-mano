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
package com.ubiqube.etsi.mano.repository.gridfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.RepositoryException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class LowGridFs implements Low {
	private final GridFsTemplate gridFsTemplate;

	public LowGridFs(final GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}

	@Override
	public boolean exist(final String path) {
		return null != gridFsTemplate.getResource(path);
	}

	@Override
	public void mkdir(final String path) {
		// Nothing.
	}

	@Override
	public void add(final String path, final byte[] content) {
		gridFsTemplate.store(new ByteArrayInputStream(content), path);
	}

	@Override
	public void add(final String path, final InputStream stream) {
		gridFsTemplate.store(stream, path);
	}

	@Override
	public void delete(final String path) {
		gridFsTemplate.delete(new Query(GridFsCriteria.whereFilename().is(path)));
	}

	@Override
	public List<String> find(final String path, final String pattern) {
		final GridFsResource[] res = gridFsTemplate.getResources(pattern);
		return Arrays.stream(res).map(GridFsResource::getFilename).toList();
	}

	@Override
	public boolean isDirectory(final String path) {
		return false;
	}

	@SuppressWarnings("resource")
	@Override
	public ManoResource get(final String path, final int min, final Long max) {
		final GridFsResource file = gridFsTemplate.getResource(path);
		try {
			return new ManoGridFsResource(file.contentLength(), file.getContent(), file.getFilename());
		} catch (IllegalStateException | IOException e) {
			throw new RepositoryException(e);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public ManoResource get(final String path) {
		final GridFsResource file = gridFsTemplate.getResource(path);
		try {
			return new ManoGridFsResource(file.contentLength(), file.getContent(), file.getFilename());
		} catch (IllegalStateException | IOException e) {
			throw new RepositoryException(e);
		}
	}

}
