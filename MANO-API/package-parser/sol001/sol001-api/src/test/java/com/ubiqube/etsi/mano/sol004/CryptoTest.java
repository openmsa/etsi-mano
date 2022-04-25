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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.bc.BcPKCS12PBEInputDecryptorProviderBuilder;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.Store;
import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol004.crypto.SignatureInputStream;

@SuppressWarnings("static-method")
public class CryptoTest {

	@Test
	void testSign() throws Exception {
		final PrivateKey pk2 = CryptoUtils.pemPrivateFile(new File("/home/olivier/ovi.pem"), null);
		final X509Certificate pubx509 = CryptoUtils.pemPublicX509(new File("/home/olivier/ovi-vpn4.pem"));
		final Signature rsaSignature = Signature.getInstance("SHA256withRSA");
		rsaSignature.initSign(pk2);
		try (final InputStream is = this.getClass().getClassLoader().getResourceAsStream("manifest.mf")) {
			final SignatureInputStream sig = new SignatureInputStream(is, rsaSignature);
			final OutputStream out = new ByteArrayOutputStream();
			sig.transferTo(out);
		}
		final byte[] signed = rsaSignature.sign();
		System.out.println(bytesToHex(signed));
		final JcaSignerInfoGeneratorBuilder sigb = new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build());
		sigb.setDirectSignature(true);
		final CMSTypedData msg = new CMSProcessableByteArray(rsaSignature.sign());
		final List<X509Certificate> certList = new ArrayList<>();
		certList.add(pubx509);
		final JcaCertStore certs = new JcaCertStore(certList);
		final CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
		final ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA256withRSA").setProvider("BC").build(pk2);
		gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, pubx509));
		gen.addCertificates(certs);
		final CMSSignedData sigData = gen.generate(msg, false);
		final Writer out = new StringWriter();
		try (final JcaPEMWriter writer = new JcaPEMWriter(out)) {
			writer.writeObject(sigData.toASN1Structure());
		}
		System.out.println("writer : \n" + out.toString());
		assertTrue(true);
	}

	@Test
	void testCertificate() throws IOException, CertificateException {
		final File in = new File("/home/olivier/ovi-vpn4.pem");
		final Reader reader = new FileReader(in);
		final PEMParser pem = new PEMParser(reader);
		final Object obj = pem.readObject();
		if (obj instanceof final X509CertificateHolder x509) {
			System.out.println("" + x509.getSubject());
			final X509Certificate cert = new JcaX509CertificateConverter().getCertificate(x509);
			cert.checkValidity();
			final PublicKey pub = cert.getPublicKey();
		}
		System.out.println("" + obj.getClass());
		assertTrue(true);
	}

	static PrivateKeyInfo getPrivateKey(final Object obj, final char[] password) throws PKCSException {
		if (obj instanceof final PKCS8EncryptedPrivateKeyInfo pkcs) {
			final InputDecryptorProvider inputDecryptorProvider = new BcPKCS12PBEInputDecryptorProviderBuilder().build(password);
			return pkcs.decryptPrivateKeyInfo(inputDecryptorProvider);
		}
		if (obj instanceof final PEMKeyPair kp) {
			return kp.getPrivateKeyInfo();
		}
		throw new Sol004Exception("Unable to open " + obj.getClass());
	}

	public static String bytesToHex(final byte[] bytes) {
		final char[] hexArray = "0123456789abcdef".toCharArray();
		final char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			final int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	@Test
	void testSigLoad() throws Exception {
		final byte[] sigBytes = Files.readAllBytes(Paths.get("src/test/resources/tosca.csar.p7s"));
		CryptoUtils.pemSignature(sigBytes);
		assertTrue(true);
	}

	@Test
	void testSigCmsLoad2() throws Exception {
		final byte[] sigBytes = Files.readAllBytes(Paths.get("src/test/resources/test.pdf.pkcs7"));
		final byte[] certBytes = Files.readAllBytes(Paths.get("src/test/resources/server.cert"));
		final byte[] dataBytes = Files.readAllBytes(Paths.get("src/test/resources/tosca.csar"));
		final CMSSignedData cms = CryptoUtils.pemSignature(sigBytes);
		final X509Certificate fullCert = CryptoUtils.pemPublicKey(certBytes);
		final SignerInformation signer = (SignerInformation) cms.getSignerInfos().getSigners().iterator().next();
		extracted(dataBytes, signer, fullCert);
		assertTrue(true);
	}

	private static final String ALGO = "sha256WithRSA";

	@Test
	void test() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		final byte[] sigBytes = Files.readAllBytes(Paths.get("src/test/resources/test.pdf.p7s.2"));
		final byte[] certBytes = Files.readAllBytes(Paths.get("src/test/resources/server.cert"));
		final byte[] dataBytes = Files.readAllBytes(Paths.get("src/test/resources/tosca.csar"));
		final CMSSignedData cms = CryptoUtils.pemSignature(sigBytes);
		final X509Certificate fullCert = CryptoUtils.pemPublicKey(certBytes);
		final PrivateKey pk = CryptoUtils.pemPrivateFile(new File("src/test/resources/server.key"), null);
		final SignerInformation signer = (SignerInformation) cms.getSignerInfos().getSigners().iterator().next();
		final Signature sig = Signature.getInstance(ALGO);
		final AlgorithmParameters pss1 = sig.getParameters();
		final PSSParameterSpec spec1 = new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), 32, 1);
		// sig.setParameter(spec1);
		sig.initSign(pk);
		sig.update(dataBytes);
		final byte[] signature = sig.sign();
		System.out.println("sig algo: " + fullCert.getSigAlgName());
		System.out.println("Sig: " + signature.length + " " + ByteUtils.toHexString(signature));
		System.out.println("Si2: " + ByteUtils.toHexString(signer.getSignature()));
		// verifySignature.setParameter(spec1);
		boolean res = checkSignature(fullCert, dataBytes, signature);
		System.out.println("res: " + res);
		res = checkSignature(fullCert, dataBytes, cms.getEncoded());
		System.out.println("res: " + res);
		assertTrue(true);
	}

	static boolean checkSignature(final X509Certificate fullCert, final byte[] dataBytes, final byte[] signature) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		final Signature verifySignature = Signature.getInstance(ALGO, "BC");
		final AlgorithmParameters pss1 = verifySignature.getParameters();
		final PSSParameterSpec spec1 = new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), 32, 1);
		// verifySignature.setParameter(spec1);
		verifySignature.initVerify(fullCert);
		verifySignature.update(dataBytes);
		return verifySignature.verify(signature);
	}

	@Test
	void testSigCmsLoad() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		final byte[] sigBytes = Files.readAllBytes(Paths.get("src/test/resources/tosca.csar.cms"));
		final byte[] certBytes = Files.readAllBytes(Paths.get("src/test/resources/mano-qa-sol004.pub.pem"));
		final byte[] dataBytes = Files.readAllBytes(Paths.get("src/test/resources/tosca.csar"));
		final CMSSignedData cms = CryptoUtils.pemSignature(sigBytes);
		final X509Certificate fullCert = CryptoUtils.pemPublicKey(certBytes);
		final Store store = cms.getCertificates();
		final SignerInformationStore signers = cms.getSignerInfos();
		final Collection<SignerInformation> c = signers.getSigners();
		final Iterator<SignerInformation> it = c.iterator();
		while (it.hasNext()) {
			final SignerInformation signer = it.next();
			final Collection<X509CertificateHolder> certCollection = store.getMatches(signer.getSID());
			final Iterator<X509CertificateHolder> certIt = certCollection.iterator();
			final X509CertificateHolder certHolder = certIt.next();
			final X509Certificate cert = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certHolder);
			if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
				System.out.println("verified");
				extracted(dataBytes, signer, fullCert);
			}
		}
		System.out.println(">" + cms.getSignedContent());
		assertTrue(true);
	}

	private static void extracted(final byte[] dataBytes, final SignerInformation signer, final X509Certificate cert) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		System.out.println("" + cert.getSigAlgName());
		final Signature signature = Signature.getInstance(cert.getSigAlgName());
		signature.initVerify(cert);
		signature.update(dataBytes);
		if (signature.verify(signer.getSignature())) {
			System.out.println("> Verified data");
		} else {
			System.out.println("> Failed !!!");
		}
	}

}
