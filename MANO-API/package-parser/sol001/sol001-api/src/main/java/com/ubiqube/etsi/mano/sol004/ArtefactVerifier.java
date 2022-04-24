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
package com.ubiqube.etsi.mano.sol004;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.sol004.manifest.SignatureElements;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.Certificate;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ArtefactVerifier {

	private final Sol004ManifestReader mr;
	private VirtualFileSystem vfs;

	public ArtefactVerifier(final Sol004ManifestReader mr) {
		this.mr = mr;
	}

	public boolean verifyArtefact(final InputStream content, final String filename) {
		final byte[] fileCert = findCert(filename);
		final Optional<SignatureElements> sig = find(filename);
		if (sig.isPresent()) {
			return handleSig(sig.get(), content);
		}
		if (!mr.getCms().isEmpty()) {
			return handleSignature(mr.getCms().get(0), content);
		}
		return false;
	}

	private byte @NotNull [] findCert(final String filename) {
		final byte[] b = getCertificateFromVfs(filename);
		if (b.length != 0) {
			return b;
		}
		final Optional<SignatureElements> sig = find(filename);
		if (sig.isPresent()) {
			return getCCertificate(sig.get());
		}
		return new byte[0];
	}

	private byte @NotNull [] getCertificateFromVfs(final String filename) {
		final String fn1 = filename + ".sig.*";
		final List<String> l = vfs.getFileMatching(fn1);
		if (!l.isEmpty()) {
			return vfs.getFileContent(l.get(0));
		}
		final int idx = filename.lastIndexOf('.');
		if (-1 == idx) {
			return new byte[0];
		}
		final String base = filename.subSequence(0, idx) + ".sig.*";
		final List<String> l2 = vfs.getFileMatching(base);
		if (!l2.isEmpty()) {
			return vfs.getFileContent(l2.get(0));
		}
		return new byte[0];
	}

	private static boolean handleSignature(final Certificate certificate, final InputStream content) {
		return CryptoUtils.verify(content, certificate.payload(), null);
	}

	private boolean handleSig(final SignatureElements signatureElements, final InputStream content) {
		boolean result = true;
		boolean haveCheck = false;
		if (signatureElements.getAlgorithm() != null && signatureElements.getHash() != null) {
			result &= CryptoUtils.checkHash(content, signatureElements.getAlgorithm(), signatureElements.getHash());
			haveCheck = true;
		}
		final byte[] cert = getCCertificate(signatureElements);
		if (cert.length != 0 && signatureElements.getSignature() != null) {
			result &= checkSignature(content, cert, signatureElements.getSignature());
			haveCheck = true;
		}
		if (!haveCheck) {
			throw new Sol004Exception("Unable to check element.");
		}
		return result;
	}

	private byte @NotNull [] getCCertificate(final SignatureElements signatureElements) {
		if (signatureElements.getCertificate() != null) {
			return vfs.getFileContent(signatureElements.getCertificate());
		}
		if (mr.getCms().isEmpty()) {
			return new byte[0];
		}
		return mr.getCms().get(0).payload();
	}

	private boolean checkSignature(final InputStream stream, final byte[] cert, final String signature) {
		final byte[] sig = vfs.getFileContent(signature);
		return CryptoUtils.verify(stream, cert, sig);
	}

	private Optional<SignatureElements> find(final String filename) {
		return mr.getSigs().stream().filter(x -> x.getSource().equals(filename)).findFirst();
	}
}
