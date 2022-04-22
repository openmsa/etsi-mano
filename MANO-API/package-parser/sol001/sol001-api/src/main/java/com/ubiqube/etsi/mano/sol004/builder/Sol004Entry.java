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
package com.ubiqube.etsi.mano.sol004.builder;

import java.io.File;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol004Entry {
	private File file;
	private String zipName;
	private HashAlgorithm hashAlgorithm;
	private String hash;
	private String signaturePath;

	public Sol004Entry(final File file, final String zipName, final HashAlgorithm hashAlgorithm) {
		super();
		this.file = file;
		this.zipName = zipName;
		this.hashAlgorithm = hashAlgorithm;
	}

	public File getFile() {
		return file;
	}

	public void setFile(final File file) {
		this.file = file;
	}

	public String getZipName() {
		return zipName;
	}

	public void setZipName(final String zipName) {
		this.zipName = zipName;
	}

	public HashAlgorithm getHashAlgorithm() {
		return hashAlgorithm;
	}

	public void setHashAlgorithm(final HashAlgorithm hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(final String signaturePath) {
		this.signaturePath = signaturePath;
	}

}
