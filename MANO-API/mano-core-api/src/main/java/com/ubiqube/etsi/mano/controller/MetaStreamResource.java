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

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.Nullable;

import com.ubiqube.etsi.mano.repository.MetaStream;

public class MetaStreamResource extends AbstractResource {

	private final MetaStream metaStream;

	public MetaStreamResource(final MetaStream ms) {
		this.metaStream = ms;
	}

	@Override
	public String getDescription() {
		return "InputStream resource [" + this.metaStream + "]";
	}

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean exists() {
		return true;
	}

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return metaStream.is();
	}

	/**
	 * This implementation compares the underlying InputStream.
	 */
	@SuppressWarnings("resource")
	@Override
	public boolean equals(@Nullable final Object other) {
		return this == other || other instanceof final InputStreamResource ot &&
				ot.equals(this.metaStream.is());
	}

	/**
	 * This implementation returns the hash code of the underlying InputStream.
	 */
	@SuppressWarnings("resource")
	@Override
	public int hashCode() {
		return this.metaStream.is().hashCode();
	}

}
