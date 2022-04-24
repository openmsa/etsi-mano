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
package com.ubiqube.etsi.mano.sol004.manifest;

import java.io.IOException;
import java.io.Writer;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.sol004.manifest.Sol004ManifestReader.Certificate;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManifestWriter {
	private final Map<String, String> keyValues;
	private final List<SignatureElements> sigs;
	private final List<Certificate> cms;

	public ManifestWriter(final Map<String, String> keyValues, final List<SignatureElements> sigs, final List<Certificate> cms) {
		super();
		this.keyValues = keyValues;
		this.sigs = sigs;
		this.cms = cms;
	}

	public void write(final Writer writer) throws IOException {
		writer.write("metadata:\n");
		if (!keyValues.isEmpty()) {
			keyValues.entrySet().forEach(x -> writeKeyValue(writer, x));
		}
		sigs.forEach(x -> writeSignature(writer, x));
		cms.forEach(x -> writeCertificates(writer, x));
	}

	private static void writeCertificates(final Writer writer, final Certificate x) {
		try {
			writer.write(String.format("----- BEGIN %s -----%n", x.algo()));
			writer.write(String.format("%s%n", Base64.getEncoder().encodeToString(x.payload())));
			writer.write(String.format("----- END %s -----%n", x.algo()));
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static void writeSignature(final Writer writer, final SignatureElements x) {
		try {
			writer.write(String.format("Source: %s%n", x.getSource()));
			Optional.ofNullable(x.getAlgorithm()).ifPresent(y -> write(writer, "Algorithm: %s%n", y));
			Optional.ofNullable(x.getHash()).ifPresent(y -> write(writer, "Hash: %s%n", y));
			Optional.ofNullable(x.getCertificate()).ifPresent(y -> write(writer, "Certificate: %s%n", y));
			Optional.ofNullable(x.getSignature()).ifPresent(y -> write(writer, "Signature: %s%n", y));
			writer.write("\n");
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static void write(final Writer writer, final String format, final String value) {
		try {
			writer.write(String.format(format, value));
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static void writeKeyValue(final Writer writer, final Entry<String, String> x) {
		try {
			writer.write(String.format("%s: %s%n", x.getKey(), x.getValue()));
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}
}
