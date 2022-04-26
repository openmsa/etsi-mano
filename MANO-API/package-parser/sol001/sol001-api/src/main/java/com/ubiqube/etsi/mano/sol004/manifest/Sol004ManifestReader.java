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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol004ManifestReader {
	private static final Pattern CMS_PATTERN = Pattern.compile("----- BEGIN (?<crypto>[a-zA-Z0-9]*) -----");
	private static final String CERTIFICATE = "Certificate";
	private static final String SIGNATURE = "Signature";
	private static final String HASH = "Hash";
	private static final String ALGORITHM = "Algorithm";
	private final Map<String, String> keyValues = new HashMap<>();
	private final List<Certificate> cms = new ArrayList<>();
	private final List<ArtefactInformations> artefacts = new ArrayList<>();

	public Sol004ManifestReader(final String filename) {
		final List<String> lines = new ArrayList<>();
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		parseManifest(lines);
	}

	public Sol004ManifestReader(final InputStream stream) {
		final List<String> lines = new ArrayList<>();
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		parseManifest(lines);
	}

	public static Sol004ManifestReader of(final InputStream stream) {
		return new Sol004ManifestReader(stream);
	}

	public List<Certificate> getCms() {
		return cms;
	}

	public Map<String, String> getKeyValues() {
		return keyValues;
	}

	private void parseManifest(final List<String> lines) {
		final ManifestParsingContext rootCtx = new ManifestParsingContext(lines);
		while (rootCtx.haveNext()) {
			final String line = rootCtx.getNextLine();
			if (null == line || line.startsWith("#")) {
				return;
			}
			if (isSignatureToken(line)) {
				final List<String> cmsList = rootCtx.consumeUntil(Sol004ManifestReader::isEndSignatureToken);
				final Certificate cert = createCertificate(line, cmsList);
				cms.add(cert);
			} else if (isNonManoArtifact(line)) {
				consumeNonMano(rootCtx);
			} else {
				handleKeyValue(rootCtx, line);
			}
		}

	}

	/**
	 * Non mano artefact appear at the end of the manifest file.
	 * <ul>
	 * <li>Fist line is the Non mano registry tag.</li>
	 * <li>Second is Source:</li>
	 * <li>Until a new Source: tag</li>
	 * </ul>
	 *
	 * @param rootCtx
	 */
	private void consumeNonMano(final ManifestParsingContext rootCtx) {
		String currentRegistry = null;
		while (rootCtx.haveNext()) {
			final String line = rootCtx.getNextLine();
			if (null == line) {
				// It should be handled by hasNext().
				return;
			}
			final KeyValue kv = parseKeyValue(line);
			if (kv.value == null || kv.value.isEmpty()) {
				currentRegistry = kv.key;
			} else {
				if (!"Source".equals(kv.key)) {
					continue;
				}
				final ArtefactInformations ai = ArtefactInformations.builder()
						.path(kv.value)
						.nonManoSetIndentifier(currentRegistry)
						.build();
				artefacts.add(ai);
			}
		}
	}

	private static boolean isNonManoArtifact(final String line) {
		return line.startsWith("non_mano_artifact_sets:");
	}

	private static Certificate createCertificate(final String line, final List<String> cmsList) {
		final String b64 = cmsList.stream().collect(Collectors.joining());
		final String format = extractFormat(line);
		return new Certificate(format, Base64.getDecoder().decode(b64));
	}

	private static String extractFormat(final String line) {
		final Matcher m = CMS_PATTERN.matcher(line);
		if (!m.matches()) {
			throw new Sol004Exception("Unable to find a valid crypto algorithm in [" + line + "]");
		}
		return m.group("crypto");
	}

	private void handleKeyValue(final ManifestParsingContext rootCtx, final String line) {
		final KeyValue kv = parseKeyValue(line);
		if ("Source".equals(kv.key)) {
			// Beginning Signature parsing.
			rootCtx.popLine();
			final List<String> res = rootCtx.readBlock()
					.ofElement("Source: ", "Algorithm: ", "Hash: ", "Signature: ", "Certificate: ")
					.startBy("Source: ")
					.build();
			final Map<String, String> kv2 = res.stream().map(Sol004ManifestReader::parseKeyValue).collect(Collectors.toMap(x -> x.key, x -> x.value));
			final ArtefactInformations ai = ArtefactInformations.builder()
					.path(kv.value)
					.algorithm(kv2.get(ALGORITHM))
					.checksum(kv2.get(HASH))
					.signature(kv2.get(SIGNATURE))
					.certificate(kv2.get(CERTIFICATE))
					.build();
			artefacts.add(ai);
		} else {
			keyValues.put(kv.key, kv.value);
		}
	}

	public List<ArtefactInformations> getArtefacts() {
		return artefacts;
	}

	private static boolean isEndSignatureToken(final String x) {
		return x.startsWith("----- END ") && x.endsWith(" -----");
	}

	private static boolean isSignatureToken(final String line) {
		return line.startsWith("----- BEGIN ") && line.trim().endsWith(" -----");
	}

	private static KeyValue parseKeyValue(final String line) {
		final int i = line.indexOf(':');
		if (-1 == i) {
			throw new Sol004Exception("Unable to find ':' in current line [" + line + "]");
		}
		final String key = line.substring(0, i);
		final String value = line.substring(i + 1).trim();
		return new KeyValue(key, value);
	}

	public record KeyValue(String key, String value) {
		// Nothing.
	}

	public record Certificate(String algo, byte[] payload) {

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(payload);
			return prime * result + Objects.hash(algo);
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			final Certificate other = (Certificate) obj;
			return Objects.equals(algo, other.algo) && Arrays.equals(payload, other.payload);
		}

		@Override
		public String toString() {
			return "Certificate [algo=" + algo + ", payload=" + Arrays.toString(payload) + "]";
		}

	}

}
