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

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.InputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader;
import com.ubiqube.etsi.mano.sol004.vfs.DirectVfs;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ArtefactVerifierTest {

	@Test
	void testName() throws Exception {
		final InputStream stream = getClass().getResourceAsStream("/manifest.mf");
		final Sol004ManifestReader mr = new Sol004ManifestReader(stream);
		final VirtualFileSystem vfs = new DirectVfs(Paths.get("src/test/resources/"));
		final ArtefactVerifier av = new ArtefactVerifier(mr, vfs);
		final InputStream content = getClass().getResourceAsStream("/key.pem");
		final boolean res = av.verifyArtefact(content, "MRF.yaml");
		assertFalse(res);
	}
}
