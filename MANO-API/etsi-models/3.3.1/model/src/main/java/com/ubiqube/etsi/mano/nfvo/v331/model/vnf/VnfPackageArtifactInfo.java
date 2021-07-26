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
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

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
 * This type represents an artifact other than a software image which is
 * contained in or external to a VNF package. It shall comply with provisions
 * defined in Table 9.5.3.3-1.
 */
@ApiModel(description = "This type represents an artifact other than a software image which is contained in or external to a VNF package. It shall comply with provisions defined in Table 9.5.3.3-1. ")
@Validated
public class VnfPackageArtifactInfo {
	@JsonProperty("artifactPath")
	private String artifactPath = null;

	@JsonProperty("artifactURI")
	private String artifactURI = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	/**
	 * Marks specific types of artifacts as defined in the VNF package. If none of
	 * the specific classes listed below applies, the attribute shall not be
	 * present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in
	 * ETSI GS NFV-SOL 004 [5] - TESTING: a testing artifact as per clause 4.3.4 in
	 * ETSI GS NFV-SOL 004 [5] - LICENSE: a license artifact as per clause 4.3.5 in
	 * ETSI GS NFV-SOL 004 [5]
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

	@JsonProperty("isEncrypted")
	private Boolean isEncrypted = null;

	@JsonProperty("nonManoArtifactSetId")
	private String nonManoArtifactSetId = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	public VnfPackageArtifactInfo artifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
		return this;
	}

	/**
	 * Get artifactPath
	 *
	 * @return artifactPath
	 **/
	@ApiModelProperty(value = "")

	public String getArtifactPath() {
		return artifactPath;
	}

	public void setArtifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
	}

	public VnfPackageArtifactInfo artifactURI(final String artifactURI) {
		this.artifactURI = artifactURI;
		return this;
	}

	/**
	 * Get artifactURI
	 *
	 * @return artifactURI
	 **/
	@ApiModelProperty(value = "")

	public String getArtifactURI() {
		return artifactURI;
	}

	public void setArtifactURI(final String artifactURI) {
		this.artifactURI = artifactURI;
	}

	public VnfPackageArtifactInfo checksum(final Checksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Get checksum
	 *
	 * @return checksum
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public VnfPackageArtifactInfo artifactClassification(final ArtifactClassificationEnum artifactClassification) {
		this.artifactClassification = artifactClassification;
		return this;
	}

	/**
	 * Marks specific types of artifacts as defined in the VNF package. If none of
	 * the specific classes listed below applies, the attribute shall not be
	 * present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in
	 * ETSI GS NFV-SOL 004 [5] - TESTING: a testing artifact as per clause 4.3.4 in
	 * ETSI GS NFV-SOL 004 [5] - LICENSE: a license artifact as per clause 4.3.5 in
	 * ETSI GS NFV-SOL 004 [5]
	 *
	 * @return artifactClassification
	 **/
	@ApiModelProperty(value = "Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 [5] - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 [5] - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004 [5] ")

	public ArtifactClassificationEnum getArtifactClassification() {
		return artifactClassification;
	}

	public void setArtifactClassification(final ArtifactClassificationEnum artifactClassification) {
		this.artifactClassification = artifactClassification;
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

	public Boolean isIsEncrypted() {
		return isEncrypted;
	}

	public void setIsEncrypted(final Boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	public VnfPackageArtifactInfo nonManoArtifactSetId(final String nonManoArtifactSetId) {
		this.nonManoArtifactSetId = nonManoArtifactSetId;
		return this;
	}

	/**
	 * Non-MANO artifact set identifier of the non-MANO artifact set to which the
	 * artifact belongs, as defined in clause 4.3.7 of ETSI GS NFV-SOL 004 [5].
	 * Shall be provided if the artifact is a non-MANO artifact, and shall be
	 * omitted otherwise.
	 *
	 * @return nonManoArtifactSetId
	 **/
	@ApiModelProperty(value = "Non-MANO artifact set identifier of the non-MANO artifact set to which the artifact belongs, as defined in clause 4.3.7 of ETSI GS NFV-SOL 004 [5]. Shall be provided if the artifact is a non-MANO artifact, and shall be omitted otherwise. ")

	public String getNonManoArtifactSetId() {
		return nonManoArtifactSetId;
	}

	public void setNonManoArtifactSetId(final String nonManoArtifactSetId) {
		this.nonManoArtifactSetId = nonManoArtifactSetId;
	}

	public VnfPackageArtifactInfo metadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(final KeyValuePairs metadata) {
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
				Objects.equals(this.artifactURI, vnfPackageArtifactInfo.artifactURI) &&
				Objects.equals(this.checksum, vnfPackageArtifactInfo.checksum) &&
				Objects.equals(this.artifactClassification, vnfPackageArtifactInfo.artifactClassification) &&
				Objects.equals(this.isEncrypted, vnfPackageArtifactInfo.isEncrypted) &&
				Objects.equals(this.nonManoArtifactSetId, vnfPackageArtifactInfo.nonManoArtifactSetId) &&
				Objects.equals(this.metadata, vnfPackageArtifactInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifactPath, artifactURI, checksum, artifactClassification, isEncrypted, nonManoArtifactSetId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageArtifactInfo {\n");

		sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
		sb.append("    artifactURI: ").append(toIndentedString(artifactURI)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    artifactClassification: ").append(toIndentedString(artifactClassification)).append("\n");
		sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
		sb.append("    nonManoArtifactSetId: ").append(toIndentedString(nonManoArtifactSetId)).append("\n");
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
