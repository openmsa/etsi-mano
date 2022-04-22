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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ubiqube.etsi.mano.sol004.CryptoUtils;
import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.sol004.crypto.SignatureInputStream;
import com.ubiqube.etsi.mano.sol004.manifest.ManifestWriter;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.Certificate;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.SignatureElements;
import com.ubiqube.etsi.mano.sol004.metafile.MetaFileWriter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class DoubleZipBuilder {
	private static final String DOT_CERT = ".cert";
	private CertificateSigner cert;
	private final List<Sol004Entry> entries;
	private HashAlgorithm defaultHash = HashAlgorithm.SHA_256;

	public DoubleZipBuilder(final CsarBuilder csarBuilder) {
		this.entries = new ArrayList<>();
	}

	public DoubleZipBuilder certificate(final CertificateSigner certificateSigner) {
		this.cert = certificateSigner;
		return this;
	}

	public DoubleZipBuilder defaultHash(final HashAlgorithm def) {
		this.defaultHash = def;
		return this;
	}

	public DoubleZipBuilder addEntry(final File file, final String zipName) {
		entries.add(new Sol004Entry(file, zipName, defaultHash));
		return this;
	}

	public void build(final File target) {
		if (entries.isEmpty()) {
			throw new Sol004Exception("At least one entry must be present.");
		}
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target))) {
			if (cert != null) {
				addCerts(out);
			}
			entries.forEach(x -> addEntry(out, x));
			writeMetaFiles(out, entries);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private void writeMetaFiles(final ZipOutputStream out, final List<Sol004Entry> entriez) throws IOException {
		final boolean needMetafile = entriez.stream().anyMatch(x -> x.getHash() != null);
		if (!needMetafile) {
			return;
		}
		mkdir(out, "TOSCA-Metadata/");
		writeMetaFile(out);
		writeManifest(out);
	}

	private static void mkdir(final ZipOutputStream out, final String name) throws IOException {
		if (!name.endsWith("/")) {
			throw new Sol004Exception("A zip directory must be terminated by '/'.");
		}
		out.putNextEntry(new ZipEntry(name));
		out.closeEntry();
	}

	private void writeManifest(final ZipOutputStream out) throws IOException {
		final List<SignatureElements> sigsElements = convertSig();
		final List<Certificate> cms = new ArrayList<>();
		out.putNextEntry(new ZipEntry("TOSCA-Metadata/manifest.mf"));
		final StringWriter sw = new StringWriter();
		final ManifestWriter manifestWriter = new ManifestWriter(Map.of(), sigsElements, cms);
		manifestWriter.write(sw);
		out.write(sw.toString().getBytes());
		out.closeEntry();
	}

	private boolean isYamlFile(final String entry) {
		return entry.endsWith(".yaml") || entry.endsWith(".yml");
	}

	private void writeMetaFile(final ZipOutputStream out) throws IOException {
		final String entryDefinition = findEntryPoint();
		final MetaFileWriter metaWriter = new MetaFileWriter(entryDefinition, "TOSCA-Metadata/manifest.mf");
		final Writer writer = new StringWriter();
		metaWriter.write(writer);
		final Sol004Entry s4Entry = new Sol004Entry(new File(""), "TOSCA-Metadata/TOSCA.meta", defaultHash);
		addEntry(out, writer.toString(), s4Entry);
		entries.add(s4Entry);
		out.closeEntry();
	}

	private String findEntryPoint() {
		return entries.stream()
				.map(Sol004Entry::getFile)
				.map(File::getName)
				.filter(this::isYamlFile)
				.findFirst().orElse(null);
	}

	private List<SignatureElements> convertSig() {
		return entries.stream().map(this::map).toList();
	}

	private SignatureElements map(final Sol004Entry entry) {
		final String pubCert = Optional.ofNullable(cert).map(CertificateSigner::getPublicFile).map(File::getName).map(x -> x + DOT_CERT).orElse(null);
		return new SignatureElements(entry.getZipName(), entry.getHashAlgorithm().toString(), entry.getHash(), entry.getSignaturePath(), pubCert);
	}

	private void addEntry(final ZipOutputStream out, final Sol004Entry entry) {
		try {
			addEntryEx(out, entry);
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private void addEntry(final ZipOutputStream out, final String content, final Sol004Entry entry) {
		final InputStream is = new ByteArrayInputStream(content.getBytes());
		try {
			addEntryEx(out, is, entry);
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@SuppressWarnings("resource")
	private void addEntryEx(final ZipOutputStream out, final InputStream is, final Sol004Entry entry) throws IOException, NoSuchAlgorithmException {
		out.putNextEntry(new ZipEntry(entry.getZipName()));
		final InputStreamHolder ish = buildInputStream(entry, is);
		ish.getLast().transferTo(out);
		ish.close();
		out.closeEntry();
		setEntry(ish, entry, out);
	}

	@SuppressWarnings("resource")
	private void addEntryEx(final ZipOutputStream out, final Sol004Entry entry) throws NoSuchAlgorithmException, IOException {
		final InputStream is = new FileInputStream(entry.getFile());
		addEntryEx(out, is, entry);
	}

	@SuppressWarnings("resource")
	private void setEntry(final InputStreamHolder ish, final Sol004Entry entry, final ZipOutputStream out) throws IOException {
		if (ish.getSignature() != null) {
			addSignatureEntry(out, ish.getSignature(), entry);
		}
		if (ish.getHash() != null) {
			addHashEntry(ish.getHash(), entry);
		}
	}

	private static void addHashEntry(final DigestInputStream hash, final Sol004Entry entry) {
		final MessageDigest digest = hash.getMessageDigest();
		final byte[] bytes = digest.digest();
		final String hashStr = CryptoUtils.bytesToHex(bytes);
		entry.setHash(hashStr);
	}

	private void addSignatureEntry(final ZipOutputStream out, final SignatureInputStream signatureInputStream, final Sol004Entry entry) throws IOException {
		final String pubx509 = cert.getX509Signature(signatureInputStream);
		final String certFileName = entry.getZipName() + ".p7s";
		out.putNextEntry(new ZipEntry(certFileName));
		out.write(pubx509.getBytes());
		out.closeEntry();
	}

	@SuppressWarnings("resource")
	private InputStreamHolder buildInputStream(final Sol004Entry entry, final InputStream is) throws NoSuchAlgorithmException {
		final InputStreamHolder ish = new InputStreamHolder();
		ish.setRoot(is);
		InputStream last = is;
		if (cert != null) {
			final SignatureInputStream certIs = cert.getInputStream(last);
			ish.setSignature(certIs);
			last = certIs;
		}
		if (entry.getHashAlgorithm() != null) {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final DigestInputStream hashIs = new DigestInputStream(last, digest);
			ish.setHash(hashIs);
			last = hashIs;
		}
		ish.setLast(last);
		return ish;
	}

	private void addCerts(final ZipOutputStream out) throws IOException {
		final File pub = cert.getPublicFile();
		final String name = buildCertificateName(pub);
		final ZipEntry certZe = new ZipEntry(name);
		out.putNextEntry(certZe);
		try (FileInputStream fis = new FileInputStream(pub)) {
			fis.transferTo(out);
		}
		out.closeEntry();
	}

	private static String buildCertificateName(final File pub) {
		final String name = pub.getName();
		if (name.endsWith(DOT_CERT)) {
			return name;
		}
		return name + DOT_CERT;
	}

}
