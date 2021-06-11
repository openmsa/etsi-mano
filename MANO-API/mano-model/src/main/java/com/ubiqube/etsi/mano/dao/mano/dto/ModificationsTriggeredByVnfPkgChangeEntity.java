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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.util.Map;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Embeddable
public class ModificationsTriggeredByVnfPkgChangeEntity {
	private Map<String, String> vnfConfigurableProperties = null;

	private Map<String, String> metadata = null;

	private Map<String, String> extensions = null;

	private String vnfdId = null;

	private String vnfProvider = null;

	private String vnfProductName = null;

	private String vnfSoftwareVersion = null;

	private String vnfdVersion = null;

}
