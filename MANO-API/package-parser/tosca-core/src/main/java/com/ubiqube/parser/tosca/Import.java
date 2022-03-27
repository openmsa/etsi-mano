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
package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@NoArgsConstructor
@Data
public class Import {
	@JsonProperty("file")
	private String url;
	private String name;
	/**
	 * Resolved path.
	 */
	private String resolved;
	@JsonProperty("namespace_prefix")
	private String namespacePrefix;
	@JsonProperty("namespace_uri")
	private String namespaceUri;

	public Import(final String name, final String url) {
		this.name = name;
		this.url = url;
	}
}
