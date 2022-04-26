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

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.bouncycastle.cms.CMSSignedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.Certificate;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMeta;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMetaFile;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMetaFlat;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations.ArtefactInformationsBuilder;
import com.ubiqube.etsi.mano.tosca.IResolver;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class CsarArchive {
	private static final Logger LOG = LoggerFactory.getLogger(CsarArchive.class);
	private final VirtualFileSystem vfs;
	private List<Certificate> globalCertificate = new ArrayList<>();
	private List<ArtefactInformations> signatures = new ArrayList<>();
	private CsarModeEnum csarMode;

	private String csarFile;

	private ToscaMeta tmf;

	/**
	 *
	 * @param vfs         A virtual file system implementation.
	 * @param zipFilename The file name of the zip.
	 */
	public CsarArchive(final VirtualFileSystem vfs, final String zipFilename) {
		this.vfs = vfs;
		open();
	}

	private void open() {
		final List<String> list = vfs.getFileMatching(".*");
		if (haveCsarFile(list)) {
			handleDoublePackage(list);
			return;
		}
		this.csarMode = CsarModeEnum.SINGLE_ZIP;
		final Optional<String> metaFile = locateMetaFile(list);
		if (metaFile.isPresent()) {
			handleMetafile(metaFile.get());
			return;
		}
		tmf = new ToscaMetaFlat(list);
		registerGlobalCertificate(list);
		LOG.warn("Could not find CSAR metafile.");
	}

	private void registerGlobalCertificate(final List<String> list) {
		final List<String> l = list.stream().filter(x -> Sol004Utils.getSignaturePattern(x).matcher(x).find()).toList();
		if (l.isEmpty()) {
			return;
		}

		final byte[] content = vfs.getFileContent(l.get(0));
		globalCertificate.add(new Certificate("algo", content));
	}

	public List<CsarError> validate() {
		return signatures.stream().map(this::checkSignature).filter(Objects::nonNull).toList();
	}

	private CsarError checkSignature(final ArtefactInformations sig) {
		if (sig.getChecksum() != null) {
			try (InputStream stream = vfs.getInputStream(sig.getPath())) {
				final String algorithm = sig.getAlgorithm();
				final String hash = sig.getChecksum();
				final boolean res = CryptoUtils.checkHash(stream, algorithm, hash);
				if (!res) {
					return new CsarError(sig.getPath());
				}
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		if (sig.getCertificate() != null) {
			try (InputStream stream = vfs.getInputStream(sig.getPath())) {
				final byte[] cert = vfs.getFileContent(sig.getCertificate());
				final X509Certificate certPem = PemUtils.pemPublicKey(cert);
				final byte[] sigBytes = vfs.getFileContent(sig.getSignature());
				final CMSSignedData sigPem = PemUtils.pemSignature(sigBytes);
				final boolean res = CryptoUtils.verify(stream, certPem.getPublicKey().getEncoded(), sigPem.getEncoded());
				if (!res) {
					return new CsarError(sig.getPath());
				}

			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		return null;
	}

	/**
	 * Double Zipped archive mean one zip with csar file in it. The certificate can
	 * be on root, or in the manifest file. The signature can be in the manifest or
	 * in a single file with the same name as the signed file.
	 *
	 * @param list List of file names in the Zip.
	 */
	private void handleDoublePackage(final List<String> list) {
		final Optional<String> metaFile = locateMetaFile(list);
		if (metaFile.isPresent()) {
			handleMetafile(metaFile.get());
		}
		this.csarFile = findCsarFile(list);
		this.csarMode = CsarModeEnum.DOUBLE_ZIP;
		final String certFilename = findCert(list).orElseThrow(() -> new Sol004Exception("Unable to find certificate."));
		final String signature = findFlatSig(list, csarFile);
		final ArtefactInformations ai = ArtefactInformations.builder()
				.path(csarFile)
				.signature(signature)
				.certificate(certFilename)
				.build();
		signatures.add(ai);
	}

	private static String findFlatSig(final List<String> list, final String csarFile) {
		final Pattern p = Sol004Utils.getSignaturePattern(csarFile);
		return list.stream()
				.filter(x -> p.matcher(x).find())
				.findFirst()
				.orElseThrow(() -> new Sol004Exception("Unable to find flat signature."));
	}

	private static String findCsarFile(final List<String> list) {
		return list.stream()
				.filter(x -> x.endsWith(".csar"))
				.findAny()
				.orElseThrow();
	}

	private static Optional<String> findCert(final List<String> list) {
		return list.stream()
				.filter(x -> x.endsWith(".cert"))
				.findFirst();
	}

	private static boolean haveCsarFile(final List<String> list) {
		return list.stream()
				.anyMatch(x -> x.endsWith(".csar"));
	}

	private void handleMetafile(final String metaFilename) {
		try (final InputStream stream = vfs.getInputStream(metaFilename)) {
			tmf = ToscaMetaFile.of(stream);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		final String manifestFilename = tmf.getManifestFileName();
		if (null == manifestFilename) {
			return;
		}
		try (final InputStream manifestStream = vfs.getInputStream(manifestFilename)) {
			final Sol004ManifestReader reader = Sol004ManifestReader.of(manifestStream);
			globalCertificate = reader.getCms();
			signatures = reader.getArtefacts();
			rebuildCert();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	/**
	 * Always overwrite certificate in metafile if a local one exist.
	 */
	private void rebuildCert() {
		signatures.stream()
				.filter(x -> x.getCertificate() != null)
				.forEach(x -> {
					final List<String> res = vfs.getFileMatching(Sol004Utils.getSignatureWildcard(x.getPath()));
					if (res.isEmpty()) {
						return;
					}
					if (res.size() > 1) {
						throw new Sol004Exception("Multiples signature match the current source " + x.getPath() + " => " + res);
					}
					x.setSignature(res.get(0));
				});
	}

	private static Optional<String> locateMetaFile(final List<String> list) {
		return list.stream().filter(x -> x.equals("TOSCA-Metadata/TOSCA.meta")).findFirst();
	}

	public InputStream getCsarFile() {
		if (csarMode == CsarModeEnum.DOUBLE_ZIP) {
			return vfs.getInputStream(csarFile);
		}
		throw new Sol004Exception("Unsupported operation: " + csarMode);
	}

	public ToscaMeta getMetaFile() {
		return this.tmf;
	}

	public InputStream getInputStream(final String fileName) {
		return vfs.getInputStream(fileName);
	}

	public byte[] getContent(final String fileName) {
		return vfs.getFileContent(fileName);
	}

	public IResolver getResolver() {
		return vfs.getResolver();
	}

	public @NotNull List<ArtefactInformations> getArtefactList() {
		final List<String> files = vfs.getFileMatching(".*");
		return files.stream()
				.filter(x -> !isCert(x))
				.filter(x -> !isSignature(x))
				.map(x -> map(x, files))
				.toList();
	}

	private ArtefactInformations map(final String fileName, final List<String> files) {
		final ArtefactInformationsBuilder ret = ArtefactInformations.builder();
		ret.path(fileName);
		return signatures.stream()
				.filter(x -> x.equals(fileName))
				.findFirst().orElse(null);
	}

	private static boolean isSignature(final String x) {
		return x.contains(".sig.");
	}

	private static boolean isCert(final String x) {
		return x.endsWith(".cert");
	}
}
