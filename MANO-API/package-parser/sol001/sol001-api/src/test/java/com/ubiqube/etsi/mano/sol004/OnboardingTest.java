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

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
class OnboardingTest {
	private final Sol004Onboarding ob = new Sol004Onboarding();

	@Test
	void testName() throws Exception {
		final String fileName = "src/test/resources/tosca.csar";
		ob.preOnboard(fileName);
		final boolean yang = ob.isYang();
		if (yang) {
			throw new Sol004Exception("Ynag is not supported.");
		}
		final String entryFilename = ob.getToscaEntryPointFilename();
		if (isXml(entryFilename)) {
			throw new Sol004Exception("XML sol001 format not supported.");
		}
		try (final InputStream is = ob.getToscaEntryPoint()) {
			//
		}
		uploadToCluster(fileName);
		final List<CsarError> err = ob.validate();
		assertTrue(err.isEmpty());
	}

	private static boolean isXml(final String entryFilename) {
		return entryFilename.endsWith(".xml");
	}

	private void uploadToCluster(final String fileName) {
		final CsarModeEnum mode = ob.getToscaMode(fileName);
		if (mode == CsarModeEnum.DOUBLE_ZIP) {
			// Upload to Cluster.
		} else {
			// Upload the filename.
		}
		System.out.println("" + mode);
	}
}
