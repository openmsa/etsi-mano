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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.bc.BcPKCS12PBEInputDecryptorProviderBuilder;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class PemUtils {
	private PemUtils() {
		// Nothing
	}

	public static PrivateKey pemPrivateFile(final File file, final char[] password) {
		try {
			return pemPrivateFileEx(file, password);
		} catch (IOException | PKCSException e) {
			throw new Sol004Exception(e);
		}
	}

	public static PublicKey pemPublicKey(final File file) {
		try {
			return pemPublicKeyEx(file);
		} catch (CertificateException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static PublicKey pemPublicKeyEx(final File file) throws CertificateException, IOException {
		final X509Certificate cert = pemPublicX509Ex(file);
		cert.checkValidity();
		return cert.getPublicKey();
	}

	public static X509Certificate pemPublicX509(final File file) {
		try {
			return pemPublicX509Ex(file);
		} catch (CertificateException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	public static X509Certificate pemPublicKey(final byte[] cert) {
		final Reader reader = new InputStreamReader(new ByteArrayInputStream(cert));
		try {
			return pemPublicReader(reader);
		} catch (CertificateException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static X509Certificate pemPublicX509Ex(final File file) throws IOException, CertificateException {
		try (final Reader reader = new FileReader(file)) {
			return pemPublicReader(reader);
		}
	}

	private static X509Certificate pemPublicReader(final Reader reader) throws IOException, CertificateException {
		try (final PEMParser pem = new PEMParser(reader)) {
			final Object obj = pem.readObject();
			if (obj instanceof final X509CertificateHolder x509) {
				return new JcaX509CertificateConverter().getCertificate(x509);
			}
			throw new Sol004Exception("Unknown public key format: " + obj.getClass());
		}
	}

	public static PrivateKey pemPrivateFileEx(final File file, final char[] password) throws IOException, PKCSException {
		Security.addProvider(new BouncyCastleProvider());
		final Reader reader = new FileReader(file);
		try (final PEMParser pem = new PEMParser(reader)) {
			final Object obj = pem.readObject();
			final PrivateKeyInfo pk = getPrivateKey(obj, password);
			return new JcaPEMKeyConverter().getPrivateKey(pk);
		}
	}

	private static PrivateKeyInfo getPrivateKey(final Object obj, final char[] password) throws PKCSException {
		if (obj instanceof final PKCS8EncryptedPrivateKeyInfo pkcs) {
			final InputDecryptorProvider inputDecryptorProvider = new BcPKCS12PBEInputDecryptorProviderBuilder().build(password);
			return pkcs.decryptPrivateKeyInfo(inputDecryptorProvider);
		}
		if (obj instanceof final PEMKeyPair kp) {
			return kp.getPrivateKeyInfo();
		}
		if (obj instanceof final PrivateKeyInfo kp) {
			return kp;
		}
		throw new Sol004Exception("Unable to open " + obj.getClass());
	}

	public static CMSSignedData pemSignature(final byte[] sigBytes) {
		try {
			return pemSignatureEx(sigBytes);
		} catch (IOException | CMSException e) {
			throw new Sol004Exception(e);
		}
	}

	public static CMSSignedData pemSignatureEx(final byte[] sigBytes) throws IOException, CMSException {
		final Reader reader = new InputStreamReader(new ByteArrayInputStream(sigBytes));
		try (final PEMParser pem = new PEMParser(reader)) {
			final Object obj = pem.readObject();
			if (obj instanceof final ContentInfo cms) {
				return new CMSSignedData(cms);
			}
			throw new Sol004Exception("Unknown public key format: " + obj.getClass());
		}
	}
}
