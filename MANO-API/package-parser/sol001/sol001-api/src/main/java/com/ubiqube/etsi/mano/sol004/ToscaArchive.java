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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.Certificate;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.SignatureElements;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMetaFile;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaArchive {
	private final String filename;
	private final VirtualFileSystem vfs;
	private List<Certificate> globalCertificate = new ArrayList<>();
	private List<SignatureElements> signatures = new ArrayList<>();

	/**
	 *
	 * @param vfs         A virtual file system implementation.
	 * @param zipFilename The file name of the zip.
	 */
	public ToscaArchive(final VirtualFileSystem vfs, final String zipFilename) {
		this.filename = zipFilename;
		this.vfs = vfs;
		open();
	}

	private void open() {
		final List<String> list = vfs.getFileMatching(".*");
		if (haveCsarFile(list)) {
			handleDoublePackage(list);
		}
		final Optional<String> metaFile = locateMetaFile(list);
		if (metaFile.isPresent()) {
			handleMetafile(metaFile.get());
		}
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
		final String csarFile = findCsarFile(list);
		final String certFilename = findCert(list).orElseThrow(() -> new Sol004Exception("Unable to find certificate."));
		final String signature = findFlatSig(list, csarFile);
		final SignatureElements se = new SignatureElements(csarFile, null, null, signature, certFilename);
		signatures.add(se);
	}

	private static String findFlatSig(final List<String> list, final String csarFile) {
		final Pattern p = Pattern.compile(csarFile + ".*.sig.*$");
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
		final ToscaMetaFile tmf;
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
			signatures = reader.getSigs();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static Optional<String> locateMetaFile(final List<String> list) {
		return list.stream().filter(x -> x.equals("TOSCA-Metadata/TOSCA.meta")).findFirst();
	}

}
