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

package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents an artifact other than a software image which is
 * contained in a VNF package. It shall comply with provisions defined in Table
 * 9.5.3.3-1.
 */
@Schema(description = "This type represents an artifact other than a software image which is contained in a VNF package.  It shall comply with provisions defined in Table 9.5.3.3-1. ")
@Validated
public class VnfPackageArtifactInfo {
	@JsonProperty("artifactPath")
	private String artifactPath = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	public VnfPackageArtifactInfo artifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
		return this;
	}

	/**
	 * Path in the VNF package, which identifies the artifact and also allows to
	 * access a copy of the artifact. The value of this attribute shall start with
	 * the name of the first segment in the path, i.e. it shall not be prefixed by
	 * path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/run.sh
	 *
	 * @return artifactPath
	 **/
	@Schema(required = true, description = "Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact. The value of this attribute shall start with the name of the first segment in the path, i.e. it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/run.sh ")
	@NotNull

	public String getArtifactPath() {
		return artifactPath;
	}

	public void setArtifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
	}

	public VnfPackageArtifactInfo checksum(final Checksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Checksum of the artifact file.
	 *
	 * @return checksum
	 **/
	@Schema(required = true, description = "Checksum of the artifact file. ")
	@NotNull

	@Valid

	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public VnfPackageArtifactInfo metadata(final HashMap<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * The metadata of the artifact that are available in the VNF package, such as
	 * Content type, size, creation date, etc.
	 *
	 * @return metadata
	 **/
	@Schema(description = "The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc. ")

	@Valid

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfPackageArtifactInfo vnfPackageArtifactInfo = (VnfPackageArtifactInfo) o;
		return Objects.equals(this.artifactPath, vnfPackageArtifactInfo.artifactPath) &&
				Objects.equals(this.checksum, vnfPackageArtifactInfo.checksum) &&
				Objects.equals(this.metadata, vnfPackageArtifactInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifactPath, checksum, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageArtifactInfo {\n");

		sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
