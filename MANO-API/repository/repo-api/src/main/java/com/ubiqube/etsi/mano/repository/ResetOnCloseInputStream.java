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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ResetOnCloseInputStream extends InputStream {

	private static final Logger LOG = LoggerFactory.getLogger(ResetOnCloseInputStream.class);

	private final InputStream decorated;

	public ResetOnCloseInputStream(final InputStream anInputStream) {
		if (!anInputStream.markSupported()) {
			LOG.warn("Packing {} into a buffered InputStream", anInputStream);
			final BufferedInputStream bis = new BufferedInputStream(anInputStream);
			this.decorated = bis;
		} else {
			this.decorated = anInputStream;
		}
		anInputStream.mark(1 << 24);
	}

	@Override
	public void close() throws IOException {
		decorated.reset();
	}

	@Override
	public int read() throws IOException {
		return decorated.read();
	}
}
