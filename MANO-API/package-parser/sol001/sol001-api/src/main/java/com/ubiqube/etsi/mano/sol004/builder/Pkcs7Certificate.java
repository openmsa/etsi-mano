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
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

import com.ubiqube.etsi.mano.sol004.CryptoUtils;
import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.sol004.crypto.SignatureInputStream;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Pkcs7Certificate implements CertificateSigner {

	private static final String SHA1WITH_RSA = "SHA1withRSA";
	private final PrivateKey privKey;
	private final X509Certificate pubKey;
	private final File publicFile;

	public Pkcs7Certificate(final File privateKey, final File publicKey) {
		this.publicFile = publicKey;
		privKey = CryptoUtils.pemPrivateFile(privateKey, new char[0]);
		pubKey = CryptoUtils.pemPublicX509(publicKey);
	}

	@Override
	public SignatureInputStream getInputStream(final InputStream input) {
		Signature rsaSignature;
		try {
			rsaSignature = Signature.getInstance(SHA1WITH_RSA);
			rsaSignature.initSign(privKey);
		} catch (final NoSuchAlgorithmException | InvalidKeyException e) {
			throw new Sol004Exception(e);
		}
		return new SignatureInputStream(input, rsaSignature);
	}

	@Override
	public String getExtension() {
		return ".p7c";
	}

	private SignerInfoGenerator createInfoGenerator() throws CertificateEncodingException, OperatorCreationException {
		final ContentSigner sha1Signer = new JcaContentSignerBuilder(SHA1WITH_RSA).setProvider("BC").build(privKey);
		return new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, pubKey);
	}

	private JcaCertStore createcerts() throws CertificateEncodingException {
		final List<X509Certificate> certList = new ArrayList<>();
		certList.add(pubKey);
		return new JcaCertStore(certList);
	}

	@Override
	public File getPublicFile() {
		return publicFile;
	}

	@Override
	public String getX509Signature(final SignatureInputStream sis) {
		final Signature rsaSignature = sis.getSig();
		try {
			return CryptoUtils.signData(rsaSignature.sign(), pubKey, privKey);
		} catch (final SignatureException e) {
			throw new Sol004Exception(e);
		}
	}

}
