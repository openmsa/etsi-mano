/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an artifact other than a software image which is
 * contained in a VNF package. It shall comply with provisions defined in Table
 * 9.5.3.3-1.
 */
@ApiModel(description = "This type represents an artifact other than a software image which is contained in a VNF package.  It shall comply with provisions defined in Table 9.5.3.3-1. ")
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
	@ApiModelProperty(required = true, value = "Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact. The value of this attribute shall start with the name of the first segment in the path, i.e. it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/run.sh ")
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
	@ApiModelProperty(required = true, value = "Checksum of the artifact file. ")
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
	@ApiModelProperty(value = "The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc. ")

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
