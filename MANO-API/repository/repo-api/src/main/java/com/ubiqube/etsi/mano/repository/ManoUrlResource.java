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

import java.io.InputStream;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoUrlResource implements ManoResource {
	private long size;
	private String fileName;
	private final HttpRequestor requestor;

	public ManoUrlResource(final long size, final String url, final HttpRequestor requestor) {
		this.size = size;
		this.fileName = url;
		this.requestor = requestor;
	}

	@Override
	public InputStream getInputStream() {
		return requestor.getInputStream();
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
