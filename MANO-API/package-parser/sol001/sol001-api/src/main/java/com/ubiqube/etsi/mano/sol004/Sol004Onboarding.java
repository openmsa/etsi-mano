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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMeta;
import com.ubiqube.etsi.mano.sol004.vfs.DirectVfs;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZip;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZipMr;
import com.ubiqube.etsi.mano.sol004.vfs.DoubleZip;
import com.ubiqube.etsi.mano.sol004.vfs.DoubleZipMr;
import com.ubiqube.etsi.mano.sol004.vfs.SingleVfs;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.Sol001Version;
import com.ubiqube.etsi.mano.tosca.ToscaVersion;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol004Onboarding {

	private static final String DOT_CSAR = ".csar";

	private static final Logger LOG = LoggerFactory.getLogger(Sol004Onboarding.class);

	private static final Pattern ZIP_MATCHER = Pattern.compile(".*\\.(zip)");
	private static final Pattern CSAR_MATCHER = Pattern.compile(".*\\.(csar)");
	private CsarArchive csar;
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	private ToscaVersion toscaVersion;

	private Optional<Sol001Version> sol001Version;

	public CsarModeEnum getToscaMode(final String fileName) {
		if (isCsarExtension(fileName)) {
			return CsarModeEnum.SINGLE_ZIP;
		}
		if (!isZipExtension(fileName)) {
			if (tryYaml(fileName)) {
				return CsarModeEnum.SINGLE_FILE;
			}
			return CsarModeEnum.UNKNOWN;
		}
		final List<String> fileList = getFileList(fileName);
		final boolean haveCsarFile = fileList.stream().anyMatch(x -> x.endsWith(DOT_CSAR));
		if (haveCsarFile) {
			return CsarModeEnum.DOUBLE_ZIP;
		}
		if (checkMetaFolder(fileList)) {
			return CsarModeEnum.SINGLE_ZIP;
		}

		return CsarModeEnum.UNKNOWN;
	}

	private static boolean checkMetaFolder(final List<String> fileList) {
		return fileList.stream().anyMatch("TOSCA-Metadata/TOSCA.meta"::equals);
	}

	private boolean tryYaml(final String fileName) {
		try {
			final JsonNode tree = mapper.readTree(new File(fileName));
			final Optional<JsonNode> value = Optional.ofNullable(tree.findValue("tosca_definitions_version"));
			return value.isPresent();
		} catch (final IOException e) {
			LOG.trace("", e);
			LOG.info("Not a CSAR file.");
			return false;
		}
	}

	public CsarModeEnum identify(final ManoResource mr) {
		List<String> fileList;
		try (InputStream is = mr.getInputStream()) {
			fileList = getZipFileList(is);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		final boolean haveCsarFile = fileList.stream().anyMatch(x -> x.endsWith(DOT_CSAR));
		if (haveCsarFile) {
			return CsarModeEnum.DOUBLE_ZIP;
		}
		if (checkMetaFolder(fileList)) {
			return CsarModeEnum.SINGLE_ZIP;
		}

		return CsarModeEnum.UNKNOWN;

	}

	public void preOnboard(final String filename) {
		final File file = new File(filename);
		VirtualFileSystem vfs;
		if (file.isDirectory()) {
			vfs = new DirectVfs(Paths.get(filename));
		} else {
			final CsarModeEnum mode = getToscaMode(filename);
			if (mode == CsarModeEnum.DOUBLE_ZIP) {
				vfs = new DoubleZip(file, findCsarFile(filename));
			} else if (mode == CsarModeEnum.SINGLE_ZIP) {
				vfs = new DirectZip(Paths.get(filename));
			} else if (mode == CsarModeEnum.SINGLE_FILE) {
				vfs = new SingleVfs(filename);
			} else {
				throw new Sol004Exception("Unknown mode: " + mode);
			}
		}
		this.csar = new CsarArchive(vfs, filename);
		final byte[] content = csar.getContent(getToscaEntryPointFilename());
		detectVersion(new String(content, Charset.defaultCharset()));
	}

	private static String findCsarFile(final String filename) {
		try (ZipFile zip = new ZipFile(filename)) {
			final List<String> l = zip.stream().filter(x -> x.getName().endsWith(DOT_CSAR)).map(ZipEntry::getName).toList();
			if (l.isEmpty() || l.size() > 1) {
				throw new Sol004Exception("Could not find csar file " + l.size());
			}
			return l.get(0);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	public List<CsarError> validate() {
		return csar.validate();
	}

	private static List<String> getFileList(final String fileName) {
		try (final InputStream stream = new FileInputStream(fileName)) {
			return getZipFileList(stream);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static List<String> getZipFileList(final InputStream stream) {
		final List<String> ret = new ArrayList<>();
		final ZipInputStream zis = new ZipInputStream(stream);
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				ret.add(entry.getName());
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		return ret;
	}

	private static boolean isCsarExtension(final String fileName) {
		return CSAR_MATCHER.matcher(fileName).find();
	}

	private static boolean isZipExtension(final String fileName) {
		return ZIP_MATCHER.matcher(fileName).find();
	}

	public boolean isYang() {
		final ToscaMeta metafile = csar.getMetaFile();
		return metafile.getKey("yang_definitions") != null;
	}

	public InputStream getToscaEntryPoint() {
		return csar.getInputStream(getToscaEntryPointFilename());
	}

	public String getToscaEntryPointFilename() {
		if (null != csar.getMetaFile()) {
			return csar.getMetaFile().getEntryDefinitionFileName();
		}
		return null;
	}

	public ToscaVersion getToscaVersion() {
		return toscaVersion;
	}

	private void detectVersion(final String content) {
		JsonNode doc;
		try {
			doc = mapper.readTree(content);
		} catch (final JsonProcessingException e) {
			throw new Sol004Exception(e);
		}
		toscaVersion = findToscaVersion(doc).orElseThrow(() -> new Sol004Exception("Unable to find a valid TOSCA version."));
		sol001Version = findMetadataVersion(doc);
	}

	private static Optional<ToscaVersion> findToscaVersion(final JsonNode doc) {
		return Optional.ofNullable(doc.findValue("tosca_definitions_version"))
				.map(JsonNode::asText)
				.map(ToscaVersion::fromValue);
	}

	private static Optional<Sol001Version> findMetadataVersion(final JsonNode doc) {
		return Optional.ofNullable(doc.findValue("metadata"))
				.map(x -> x.findValue("template_version"))
				.map(JsonNode::asText)
				.map(Sol001Version::fromValue);
	}

	public Optional<Sol001Version> getSol001Version() {
		return sol001Version;
	}

	public IResolver getResolver() {
		return csar.getResolver();
	}

	public String getManifestContent() {
		final String manifest = csar.getMetaFile().getManifestFileName();
		if (null == manifest) {
			return null;
		}
		return new String(csar.getContent(manifest), Charset.defaultCharset());
	}

	public byte[] getFileContent(final String fileName) {
		return csar.getContent(fileName);
	}

	public @NotNull List<ArtefactInformations> getFiles() {
		return csar.getArtefactList();
	}

	public InputStream getCsarInputStream() {
		return csar.getCsarFile();
	}

	public com.ubiqube.etsi.mano.repository.VirtualFileSystem getVirtualFileSystem(final ManoResource vnfd) {
		final CsarModeEnum mode = identify(vnfd);
		if (mode == CsarModeEnum.DOUBLE_ZIP) {
			return new FileSystemAdapter(new DoubleZipMr(vnfd));
		}
		return new FileSystemAdapter(new DirectZipMr(vnfd));
	}

}
