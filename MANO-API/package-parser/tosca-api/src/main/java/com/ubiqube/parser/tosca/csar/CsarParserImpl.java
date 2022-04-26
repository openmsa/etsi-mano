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
package com.ubiqube.parser.tosca.csar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.VfsResolver;
import com.ubiqube.parser.tosca.ParseException;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class CsarParserImpl implements CsarParser {
	private final FileObject csar;
	private Properties props;
	private VfsResolver resolver;

	public CsarParserImpl(final File filename) {
		FileSystemManager fsManager;
		try {
			fsManager = VFS.getManager();
			csar = fsManager.resolveFile(computeFilename(filename.toString()));
			resolver = new VfsResolver();
			props = getMetaFileContent(csar);

		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static String computeFilename(final String filename) {
		if (filename.startsWith("zip:")) {
			return filename;
		}
		// In case of files
		if (filename.startsWith("/")) {
			return "zip:" + filename;
		}
		final File file = new File(filename);
		return "zip:" + file.getAbsolutePath();
	}

	@Override
	public String getEntryDefinition() {
		try {
			return innerGetEntryDefinition();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private String innerGetEntryDefinition() throws IOException {
		final String entry = getEntryDefinitionFileName();
		final FileObject res2 = csar.resolveFile(entry);
		resolver.setParent(res2.getParent());
		return res2.getContent().getString(Charset.defaultCharset());
	}

	private static Properties getMetaFileContent(final FileObject fileObject) throws IOException {
		final Properties props = new Properties();
		final FileObject dir = fileObject.getChild("TOSCA-Metadata");
		final FileObject fil = dir.getChild("TOSCA.meta");
		final FileContent cont = fil.getContent();
		final byte[] bytes = cont.getByteArray();
		props.load(new ByteArrayInputStream(bytes));
		return props;
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	@NotNull
	public List<ArtefactInformations> getFiles() {
		final List<ArtefactInformations> ret = new ArrayList<>();
		try {
			ret.addAll(recurse(csar));
		} catch (final IOException | NoSuchAlgorithmException e) {
			throw new ParseException(e);
		}
		return ret;
	}

	private static List<ArtefactInformations> recurse(final FileObject fileObject) throws IOException, NoSuchAlgorithmException {
		final List<ArtefactInformations> ret = new ArrayList<>();
		if (fileObject.isFolder()) {
			final FileObject[] child = fileObject.getChildren();
			for (final FileObject fileObject2 : child) {
				final List<ArtefactInformations> l = recurse(fileObject2);
				ret.addAll(l);
			}
		} else {
			ret.add(convert(fileObject));
		}
		return ret;
	}

	private static ArtefactInformations convert(final FileObject fileObject) throws IOException, NoSuchAlgorithmException {
		final ArtefactInformations artefact = new ArtefactInformations();
		final String hash = doHash(fileObject.getContent().getByteArray());
		artefact.setChecksum(hash);
		artefact.setAlgorithm("SHA-512");
		artefact.setPath(doFriendlyName(fileObject.toString()));
		final Map<String, String> meta = extractMetadata(fileObject);
		artefact.setMetadata(meta);
		return artefact;
	}

	private static String doFriendlyName(final String filename) {
		// XXX: I don'tkown how to get the original file name.
		final int idx = filename.indexOf("!/");
		if (idx > 0) {
			return filename.substring(idx + 2);
		}
		return filename;
	}

	private static Map<String, String> extractMetadata(final FileObject fileObject) throws FileSystemException {
		final Map<String, String> meta = new HashMap<>();
		meta.put("last-modified", String.valueOf(fileObject.getContent().getLastModifiedTime()));
		meta.put("size", String.valueOf(fileObject.getContent().getSize()));
		return meta;
	}

	private static String doHash(final byte[] bytes) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-512");
		final byte[] hashbytes = digest.digest(bytes);
		return bytesToHex(hashbytes);
	}

	private static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	@Override
	public String getEntryDefinitionFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Definitions"))
				.orElseGet(() -> props.get("Entry-Definitions"));
	}

	public String getManifestFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Manifest"))
				.orElseGet(() -> props.get("Entry-Manifest"));
	}

	@Override
	public String getManifestContent() {
		final String fileName = getManifestFileName();
		return new String(getFileContent(fileName));
	}

	@Override
	public byte[] getFileContent(final String fileName) {
		FileObject res2;
		try {
			res2 = csar.resolveFile(fileName);
			resolver.setParent(res2.getParent());
			return res2.getContent().getByteArray();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}
}
