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
package com.ubiqube.etsi.mano.service.pkg.tosca.vnf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.sol004.CsarModeEnum;
import com.ubiqube.etsi.mano.sol004.Sol004Exception;

public class Sol004PreOnboarding {

	private final ManoResource resource;
	private CsarModeEnum mode;

	public Sol004PreOnboarding(final ManoResource mr) {
		this.resource = mr;
		final List<String> fileList = getFileList();
		final boolean haveCsarFile = fileList.stream().anyMatch(x -> x.endsWith(".csar"));
		if (haveCsarFile) {
			this.mode = CsarModeEnum.DOUBLE_ZIP;
			return;
		}
		if (checkMetaFolder(fileList) || checkSingleYaml(fileList)) {
			this.mode = CsarModeEnum.SINGLE_ZIP;
			return;
		}
		this.mode = CsarModeEnum.UNKNOWN;
	}

	public CsarModeEnum getMode() {
		return mode;
	}

	private static boolean checkSingleYaml(final List<String> fileList) {
		return fileList.stream().filter(x -> x.endsWith(".yml") || x.endsWith(".yaml")).toList().size() == 1;
	}

	private static boolean checkMetaFolder(final List<String> fileList) {
		return fileList.stream().anyMatch("TOSCA-Metadata/TOSCA.meta"::equals);
	}

	private List<String> getFileList() {
		try (final InputStream stream = resource.getInputStream()) {
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

}
