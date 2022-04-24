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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.etsi.mano.sol004.metafile.ToscaMetaFile;
import com.ubiqube.etsi.mano.sol004.vfs.DirectVfs;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZip;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol004Onboarding {

	private static final Logger LOG = LoggerFactory.getLogger(Sol004Onboarding.class);

	private static final Pattern ZIP_MATCHER = Pattern.compile(".*\\.(zip)");
	private static final Pattern CSAR_MATCHER = Pattern.compile(".*\\.(csar)");
	private CsarArchive csar;
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	CsarModeEnum getToscaMode(final String fileName) {
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
		final boolean haveCsarFile = fileList.stream().anyMatch(x -> x.endsWith(".csar"));
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

	public void preOnboard(final String filename) {
		final File file = new File(filename);
		VirtualFileSystem vfs;
		if (file.isDirectory()) {
			vfs = new DirectVfs(Paths.get(filename));
		} else {
			vfs = new DirectZip(Paths.get(filename));
		}
		this.csar = new CsarArchive(vfs, filename);
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
		final ToscaMetaFile metafile = csar.getMetaFile();
		return metafile.getKey("yang_definitions") != null;
	}

	public InputStream getToscaEntryPoint() {
		return csar.getInputStream(csar.getMetaFile().getEntryDefinitionFileName());
	}

	public String getToscaEntryPointFilename() {
		return csar.getMetaFile().getEntryDefinitionFileName();
	}
}
