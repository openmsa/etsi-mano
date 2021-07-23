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
package com.ubiqube.etsi.mano.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Physical driver properties.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "mano.repository")
public class ManoRepositoryProperties {
	/**
	 * Path on file system to store packages.
	 */
	@Value("${phys-root:/tmp/mano}")
	private String physRoot;

	public String getPhysRoot() {
		return physRoot;
	}

	public void setPhysRoot(final String physRoot) {
		this.physRoot = physRoot;
	}

}
