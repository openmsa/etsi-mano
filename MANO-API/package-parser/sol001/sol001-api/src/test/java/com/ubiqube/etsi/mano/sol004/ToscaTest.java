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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol004.vfs.DirectVfs;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZip;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

public class ToscaTest {

	@Test
	void testName() throws Exception {
		final Sol004Onboarding so = new Sol004Onboarding();
		so.isTosca("/home/olivier/workspace/workspace17.1.1/ubi-etsi-mano/package-parser/demo/ubi-vnffg-nsd.csar");
	}

	@Test
	void testFile() throws Exception {
		final Path root = Paths.get("/home/olivier/tmp/");
		Files.walk(root).forEach(x -> System.out.println(root.relativize(x).toString()));
	}

	@Test
	void testAll() {
		final VirtualFileSystem vfs = new DirectVfs(Paths.get("/home/olivier/workspace/workspace17.1.1/ubi-etsi-mano/package-parser/demo/vnffg-nsd"));
		final ToscaArchive ta = new ToscaArchive(vfs, "test.zip");
	}

	@Test
	void testFlat() {
		final VirtualFileSystem vfs = new DirectVfs(Paths.get("/home/olivier/workspace/workspace17.1.1/ubi-etsi-mano/package-parser/demo/sol004-single"));
		final ToscaArchive ta = new ToscaArchive(vfs, "test.zip");

	}

	@Test
	void testZip01() {
		final VirtualFileSystem vfs = new DirectZip(Paths.get("/home/olivier/workspace/workspace17.1.1/ubi-etsi-mano/package-parser/demo/ubi-scale-vnf.csar"));
		final ToscaArchive ta = new ToscaArchive(vfs, "ubi-vnffg-nsd.csar");
	}
}
