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
package com.ubiqube.etsi.mano.sol004.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SignatureInputStream extends InputStream {

	private final Signature sig;

	private final InputStream is;

	public SignatureInputStream(final InputStream is, final Signature sig) {
		this.is = is;
		this.sig = sig;

	}

	public Signature getSig() {
		return sig;
	}

	@Override
	public int hashCode() {
		return is.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return is.equals(obj);
	}

	@Override
	public int read() throws IOException {
		final int r = is.read();
		updateSignature(r);
		return r;
	}

	private void updateSignature(final int r) {
		if (r != -1) {
			try {
				sig.update((byte) r);
			} catch (final SignatureException e) {
				throw new Sol004Exception(e);
			}
		}
	}

	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException {
		final int r = is.read(b, off, len);
		updateSignature(b, off, r);
		return r;
	}

	private void updateSignature(final byte[] b, final int off, final int r) {
		if (r == -1) {
			return;
		}
		try {
			sig.update(b, off, r);
		} catch (final SignatureException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public String toString() {
		return is.toString();
	}

	@Override
	public byte[] readAllBytes() throws IOException {
		return is.readAllBytes();
	}

	@Override
	public byte[] readNBytes(final int len) throws IOException {
		return is.readNBytes(len);
	}

	@Override
	public int readNBytes(final byte[] b, final int off, final int len) throws IOException {
		return is.readNBytes(b, off, len);
	}

	@Override
	public long skip(final long n) throws IOException {
		return is.skip(n);
	}

	@Override
	public void skipNBytes(final long n) throws IOException {
		is.skipNBytes(n);
	}

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	@Override
	public synchronized void mark(final int readlimit) {
		is.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		is.reset();
	}

	@Override
	public boolean markSupported() {
		return is.markSupported();
	}

	@Override
	public long transferTo(final OutputStream out) throws IOException {
		return is.transferTo(out);
	}

}
