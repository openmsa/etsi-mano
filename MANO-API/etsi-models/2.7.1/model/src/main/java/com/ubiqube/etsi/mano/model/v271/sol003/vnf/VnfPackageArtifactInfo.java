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
package com.ubiqube.etsi.mano.model.v271.sol003.vnf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an artifact other than a software image which is contained in a VNF package.
 */
@ApiModel(description = "This type represents an artifact other than a software image which is contained in a VNF package. ")
@Validated
@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

public class VnfPackageArtifactInfo {
	@JsonProperty("artifactPath")
	private String artifactPath = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	@JsonProperty("isEncrypted")
	private Boolean isEncrypted = null;

	@JsonProperty("artifactURI")
	@Valid
	private String artifactURI = null;

	@JsonProperty("nonManoArtifactSetId")
	private String nonManoArtifactSetId = null;

	/**
	 * Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004
	 */
	public enum ArtifactClassificationEnum {
		HISTORY("HISTORY"),

		TESTING("TESTING"),

		LICENSE("LICENSE");

		private final String value;

		ArtifactClassificationEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ArtifactClassificationEnum fromValue(final String text) {
			for (final ArtifactClassificationEnum b : ArtifactClassificationEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("artifactClassification")
	private ArtifactClassificationEnum artifactClassification = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	public VnfPackageArtifactInfo artifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
		return this;
	}

	/**
	 * Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact. The For an artifact contained as a file in the VNF package, this attribute shall be present, and the value of this attribute shall start with the name of the first segment in the path in the package, i.e. it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/runm@ster.sh For an external artifact represented as a URI in the VNF descriptor, this
	 * attribute shall be present if the artifact has been downloaded by the NFVO and shall be absent otherwise. If present, it shall contain the artifactPath under which the artifact can be obtained using the \"Individual artifact in a VNF package\" resource defined in clause 10.4.6. It is the responsibility of the NFVO to synthesize this path in a manner that avoids any collision of the synthesized artifact path with the paths and names of artifacts included in the package.
	 *
	 * @return artifactPath
	 **/
	@ApiModelProperty(required = true, value = "Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact. The For an artifact contained as a file in the VNF package, this attribute shall be present, and the value of this attribute shall start with the name of the first segment in the path in the package, i.e. it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/runm@ster.sh For an external artifact represented as a URI in the VNF descriptor, this attribute shall be present if the artifact has been downloaded by the NFVO and shall be absent otherwise. If present, it shall contain the artifactPath under which the artifact can be obtained using the \"Individual artifact in a VNF package\" resource defined in clause 10.4.6. It is the responsibility of the NFVO to synthesize this path in a manner that avoids any collision of the synthesized artifact path with the paths and names of artifacts included in the package. ")
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

	public VnfPackageArtifactInfo isEncrypted(final Boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
		return this;
	}

	/**
	 * Reflects whether the artifact is encrypted (true) or not (false).
	 *
	 * @return isEncrypted
	 **/
	@ApiModelProperty(required = true, value = "Reflects whether the artifact is encrypted (true) or not (false). ")
	@NotNull

	public Boolean getIsEncrypted() {
		return isEncrypted;
	}

	public void setIsEncrypted(final Boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	public VnfPackageArtifactInfo artifactURI(final String artifactURI) {
		this.artifactURI = artifactURI;
		return this;
	}

	/**
	 * URI of the artifact as defined in the VNF package manifest. Shall be present if the artifact is external to the package and shall be absent otherwise. EXAMPLE: https://example.com/m%40ster.sh
	 *
	 * @return artifactURI
	 **/
	@ApiModelProperty(value = "URI of the artifact as defined in the VNF package manifest. Shall be present if the artifact is external to the package and shall be absent otherwise. EXAMPLE: https://example.com/m%40ster.sh ")

	public String getArtifactURI() {
		return artifactURI;
	}

	public void setArtifactURI(final String artifactURI) {
		this.artifactURI = artifactURI;
	}

	public VnfPackageArtifactInfo nonManoArtifactSetId(final String nonManoArtifactSetId) {
		this.nonManoArtifactSetId = nonManoArtifactSetId;
		return this;
	}

	/**
	 * Non-MANO artifact set identifier of the non-MANO artifact set to which the artifact belongs, as defined in clause 4.3.7 of ETSI GS NFV-SOL 004. Shall be provided if the artifact is a non-MANO artifact, and shall be omitted otherwise.
	 *
	 * @return nonManoArtifactSetId
	 **/
	@ApiModelProperty(value = "Non-MANO artifact set identifier of the non-MANO artifact set to which the artifact belongs, as defined in clause 4.3.7 of ETSI GS NFV-SOL 004. Shall be provided if the artifact is a non-MANO artifact, and shall be omitted otherwise. ")

	public String getNonManoArtifactSetId() {
		return nonManoArtifactSetId;
	}

	public void setNonManoArtifactSetId(final String nonManoArtifactSetId) {
		this.nonManoArtifactSetId = nonManoArtifactSetId;
	}

	public VnfPackageArtifactInfo artifactClassification(final ArtifactClassificationEnum artifactClassification) {
		this.artifactClassification = artifactClassification;
		return this;
	}

	/**
	 * Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004
	 *
	 * @return artifactClassification
	 **/
	@ApiModelProperty(value = "Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004 ")

	public ArtifactClassificationEnum getArtifactClassification() {
		return artifactClassification;
	}

	public void setArtifactClassification(final ArtifactClassificationEnum artifactClassification) {
		this.artifactClassification = artifactClassification;
	}

	public VnfPackageArtifactInfo metadata(final HashMap<String, Object> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc. ")

	@Valid

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, Object> metadata) {
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
				Objects.equals(this.isEncrypted, vnfPackageArtifactInfo.isEncrypted) &&
				Objects.equals(this.artifactURI, vnfPackageArtifactInfo.artifactURI) &&
				Objects.equals(this.nonManoArtifactSetId, vnfPackageArtifactInfo.nonManoArtifactSetId) &&
				Objects.equals(this.artifactClassification, vnfPackageArtifactInfo.artifactClassification) &&
				Objects.equals(this.metadata, vnfPackageArtifactInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifactPath, checksum, isEncrypted, artifactURI, nonManoArtifactSetId, artifactClassification, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageArtifactInfo {\n");

		sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
		sb.append("    artifactURI: ").append(toIndentedString(artifactURI)).append("\n");
		sb.append("    nonManoArtifactSetId: ").append(toIndentedString(nonManoArtifactSetId)).append("\n");
		sb.append("    artifactClassification: ").append(toIndentedString(artifactClassification)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
