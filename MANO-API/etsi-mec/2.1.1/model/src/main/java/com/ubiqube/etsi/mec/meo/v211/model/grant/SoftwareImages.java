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
package com.ubiqube.etsi.mec.meo.v211.model.grant;

import org.springframework.validation.annotation.Validated;

/**
 * SoftwareImages
 */
@Validated
public class SoftwareImages {

	private String vimConnectionId;

	private String appDSoftwareImageId;

	private String vimSoftwareImageId;

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getAppDSoftwareImageId() {
		return appDSoftwareImageId;
	}

	public void setAppDSoftwareImageId(final String appDSoftwareImageId) {
		this.appDSoftwareImageId = appDSoftwareImageId;
	}

	public String getVimSoftwareImageId() {
		return vimSoftwareImageId;
	}

	public void setVimSoftwareImageId(final String vimSoftwareImageId) {
		this.vimSoftwareImageId = vimSoftwareImageId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SoftwareImages {\n");

		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
