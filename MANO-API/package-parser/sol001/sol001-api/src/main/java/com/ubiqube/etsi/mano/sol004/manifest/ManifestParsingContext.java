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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 * Context for parsing manifest.mf files.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */

public class ManifestParsingContext {
	private final List<String> lines;
	private int currentLine;
	private List<String> stopWords;

	public ManifestParsingContext(final List<String> lines) {
		this.lines = lines;
	}

	public static ManifestParsingContext of(final ManifestParsingContext parentCtx) {
		final ManifestParsingContext newCtx = new ManifestParsingContext(parentCtx.lines);
		newCtx.currentLine = parentCtx.currentLine;
		return newCtx;
	}

	public int getMaxLine() {
		return lines.size();
	}

	public String getOneLine() {
		return lines.get(currentLine++);
	}

	public List<String> getStopWords() {
		return stopWords;
	}

	public String getNextLine() {
		for (int i = currentLine; i < lines.size(); i++) {
			final String line = lines.get(i);
			if (line.isBlank()) {
				continue;
			}
			currentLine = i + 1;
			return line;
		}
		return null;
	}

	public List<String> consumeUntil(final Predicate<String> predicate) {
		final List<String> ret = new ArrayList<>();
		while (currentLine < getMaxLine()) {
			final String nl = getNextLine();
			if (predicate.test(nl)) {
				return ret;
			}
			ret.add(nl);
		}
		return ret;
	}

	public BlockBuilder readBlock() {
		return new BlockBuilder(this);
	}

	class BlockBuilder {

		private List<String> elements;
		private String startWith;
		private final ManifestParsingContext mpc;

		public BlockBuilder(final ManifestParsingContext manifestParsingContext) {
			this.mpc = manifestParsingContext;
		}

		public BlockBuilder ofElement(final String... elems) {
			this.elements = Stream.of(elems).toList();
			return this;
		}

		public BlockBuilder startBy(final String start) {
			this.startWith = start;
			return this;
		}

		public List<String> build() {
			boolean haveStart = false;
			final List<String> ret = new ArrayList<>();
			while (currentLine < mpc.getMaxLine()) {
				final String line = mpc.getNextLine();
				if (null == line) {
					return ret;
				}
				if (!isValidElement(line, elements)) {
					mpc.popLine();
					return ret;
				}
				if (line.startsWith(startWith)) {
					if (haveStart) {
						mpc.popLine();
						return ret;
					}
					haveStart = true;
				}
				ret.add(line);
			}
			return ret;
		}

	}

	public void popLine() {
		this.currentLine--;
		if (currentLine < 0) {
			throw new Sol004Exception("Could not pop line.");
		}
	}

	private static boolean isValidElement(final String line, final List<String> elements) {
		return elements.stream().anyMatch(line::startsWith);
	}

	public boolean haveNext() {
		return currentLine != getMaxLine();
	}

}
