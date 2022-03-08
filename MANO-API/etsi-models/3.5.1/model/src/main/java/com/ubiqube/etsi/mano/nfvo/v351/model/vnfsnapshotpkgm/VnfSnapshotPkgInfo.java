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
package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the information of a VNF snapshot package. It shall
 * comply with the provisions defined in table 11.5.2.3-1. NOTE: The attribute
 * shall not be present before the VNF snapshot package content has been
 * uploaded or built. Otherwise, this attribute shall be present unless it has
 * been requested to be excluded per attribute selector.
 */
@Schema(description = "This type represents the information of a VNF snapshot package. It shall comply with the provisions  defined in table 11.5.2.3-1. NOTE: The attribute shall not be present before the VNF snapshot package content has been uploaded or built. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")
@Validated

public class VnfSnapshotPkgInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfSnapshotPkgUniqueId")
	private String vnfSnapshotPkgUniqueId = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	@JsonProperty("createdAt")
	private OffsetDateTime createdAt = null;

	@JsonProperty("vnfSnapshotId")
	private String vnfSnapshotId = null;

	@JsonProperty("vnfcSnapshotInfoIds")
	@Valid
	private List<String> vnfcSnapshotInfoIds = null;

	@JsonProperty("isFullSnapshot")
	private Boolean isFullSnapshot = null;

	@JsonProperty("vnfdInfo")
	private VnfdInfo vnfdInfo = null;

	@JsonProperty("vnfsr")
	private VnfSnapshotRecord vnfsr = null;

	@JsonProperty("vnfcSnapshotImages")
	@Valid
	private List<VnfcSnapshotImageInfo> vnfcSnapshotImages = null;

	@JsonProperty("additionalArtifacts")
	@Valid
	private List<SnapshotPkgArtifactInfo> additionalArtifacts = null;

	/**
	 * State of the VNF snapshot package. Permitted values: - CREATED: the VNF
	 * snapshot package information has been created. - BUILDING: the VNF snapshot
	 * package is being built. - UPLOADING: the VNF snapshot package is being
	 * uploaded. - EXTRACTING: the VNF snapshot package’s content is being
	 * extracted. - AVAILABLE: the VNF snapshot package is available (i.e., build or
	 * upload is completed). - PROCESSING: the VNF snapshot package is being
	 * processed. - ERROR: failure during the VNF snapshot package building,
	 * uploading or processing. - ERROR_EXTRACTING: failure during the VNF snapshot
	 * package extraction task.
	 */
	public enum StateEnum {
		CREATED("CREATED"),

		BUILDING("BUILDING"),

		UPLOADING("UPLOADING"),

		EXTRACTING("EXTRACTING"),

		AVAILABLE("AVAILABLE"),

		PROCESSING("PROCESSING"),

		ERROR("ERROR"),

		ERROR_EXTRACTING("ERROR_EXTRACTING");

		private final String value;

		StateEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StateEnum fromValue(final String text) {
			for (final StateEnum b : StateEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("state")
	private StateEnum state = null;

	@JsonProperty("isCancelPending")
	private Boolean isCancelPending = null;

	@JsonProperty("failureDetails")
	private VnfSnapshotPkgInfoFailureDetails failureDetails = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	@JsonProperty("_links")
	private VnfSnapshotPkgInfoLinks _links = null;

	public VnfSnapshotPkgInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfSnapshotPkgInfo vnfSnapshotPkgUniqueId(final String vnfSnapshotPkgUniqueId) {
		this.vnfSnapshotPkgUniqueId = vnfSnapshotPkgUniqueId;
		return this;
	}

	/**
	 * Get vnfSnapshotPkgUniqueId
	 *
	 * @return vnfSnapshotPkgUniqueId
	 **/
	@Schema(description = "")

	public String getVnfSnapshotPkgUniqueId() {
		return vnfSnapshotPkgUniqueId;
	}

	public void setVnfSnapshotPkgUniqueId(final String vnfSnapshotPkgUniqueId) {
		this.vnfSnapshotPkgUniqueId = vnfSnapshotPkgUniqueId;
	}

	public VnfSnapshotPkgInfo name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human-readable name of the VNF snapshot package.
	 *
	 * @return name
	 **/
	@Schema(required = true, description = "Human-readable name of the VNF snapshot package. ")
	@NotNull

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public VnfSnapshotPkgInfo checksum(final Checksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Get checksum
	 *
	 * @return checksum
	 **/
	@Schema(description = "")

	@Valid
	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public VnfSnapshotPkgInfo createdAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * Get createdAt
	 *
	 * @return createdAt
	 **/
	@Schema(description = "")

	@Valid
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public VnfSnapshotPkgInfo vnfSnapshotId(final String vnfSnapshotId) {
		this.vnfSnapshotId = vnfSnapshotId;
		return this;
	}

	/**
	 * Get vnfSnapshotId
	 *
	 * @return vnfSnapshotId
	 **/
	@Schema(description = "")

	public String getVnfSnapshotId() {
		return vnfSnapshotId;
	}

	public void setVnfSnapshotId(final String vnfSnapshotId) {
		this.vnfSnapshotId = vnfSnapshotId;
	}

	public VnfSnapshotPkgInfo vnfcSnapshotInfoIds(final List<String> vnfcSnapshotInfoIds) {
		this.vnfcSnapshotInfoIds = vnfcSnapshotInfoIds;
		return this;
	}

	public VnfSnapshotPkgInfo addVnfcSnapshotInfoIdsItem(final String vnfcSnapshotInfoIdsItem) {
		if (this.vnfcSnapshotInfoIds == null) {
			this.vnfcSnapshotInfoIds = new ArrayList<>();
		}
		this.vnfcSnapshotInfoIds.add(vnfcSnapshotInfoIdsItem);
		return this;
	}

	/**
	 * Identifier of information held by the VNFM about specific VNFC snapshot(s)
	 * part of the VNF snapshot and contained in the VNF snapshot package. This
	 * identifier is allocated by the VNFM during the VNF snapshot creation. See
	 * note.
	 *
	 * @return vnfcSnapshotInfoIds
	 **/
	@Schema(description = "Identifier of information held by the VNFM about specific VNFC snapshot(s) part of the VNF  snapshot and contained in the VNF snapshot package. This identifier is allocated by the VNFM  during the VNF snapshot creation. See note. ")

	public List<String> getVnfcSnapshotInfoIds() {
		return vnfcSnapshotInfoIds;
	}

	public void setVnfcSnapshotInfoIds(final List<String> vnfcSnapshotInfoIds) {
		this.vnfcSnapshotInfoIds = vnfcSnapshotInfoIds;
	}

	public VnfSnapshotPkgInfo isFullSnapshot(final Boolean isFullSnapshot) {
		this.isFullSnapshot = isFullSnapshot;
		return this;
	}

	/**
	 * Get isFullSnapshot
	 *
	 * @return isFullSnapshot
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public Boolean getIsFullSnapshot() {
		return isFullSnapshot;
	}

	public void setIsFullSnapshot(final Boolean isFullSnapshot) {
		this.isFullSnapshot = isFullSnapshot;
	}

	public VnfSnapshotPkgInfo vnfdInfo(final VnfdInfo vnfdInfo) {
		this.vnfdInfo = vnfdInfo;
		return this;
	}

	/**
	 * Get vnfdInfo
	 *
	 * @return vnfdInfo
	 **/
	@Schema(description = "")

	@Valid
	public VnfdInfo getVnfdInfo() {
		return vnfdInfo;
	}

	public void setVnfdInfo(final VnfdInfo vnfdInfo) {
		this.vnfdInfo = vnfdInfo;
	}

	public VnfSnapshotPkgInfo vnfsr(final VnfSnapshotRecord vnfsr) {
		this.vnfsr = vnfsr;
		return this;
	}

	/**
	 * Get vnfsr
	 *
	 * @return vnfsr
	 **/
	@Schema(description = "")

	@Valid
	public VnfSnapshotRecord getVnfsr() {
		return vnfsr;
	}

	public void setVnfsr(final VnfSnapshotRecord vnfsr) {
		this.vnfsr = vnfsr;
	}

	public VnfSnapshotPkgInfo vnfcSnapshotImages(final List<VnfcSnapshotImageInfo> vnfcSnapshotImages) {
		this.vnfcSnapshotImages = vnfcSnapshotImages;
		return this;
	}

	public VnfSnapshotPkgInfo addVnfcSnapshotImagesItem(final VnfcSnapshotImageInfo vnfcSnapshotImagesItem) {
		if (this.vnfcSnapshotImages == null) {
			this.vnfcSnapshotImages = new ArrayList<>();
		}
		this.vnfcSnapshotImages.add(vnfcSnapshotImagesItem);
		return this;
	}

	/**
	 * Information about VNF snapshot artifacts that are VNFC snapshot images. Every
	 * local and external snapshot image shall be included. No other artifacts shall
	 * be included. See note.
	 *
	 * @return vnfcSnapshotImages
	 **/
	@Schema(description = "Information about VNF snapshot artifacts that are VNFC snapshot images. Every local and  external snapshot image shall be included. No other artifacts shall be included.  See note. ")
	@Valid
	public List<VnfcSnapshotImageInfo> getVnfcSnapshotImages() {
		return vnfcSnapshotImages;
	}

	public void setVnfcSnapshotImages(final List<VnfcSnapshotImageInfo> vnfcSnapshotImages) {
		this.vnfcSnapshotImages = vnfcSnapshotImages;
	}

	public VnfSnapshotPkgInfo additionalArtifacts(final List<SnapshotPkgArtifactInfo> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
		return this;
	}

	public VnfSnapshotPkgInfo addAdditionalArtifactsItem(final SnapshotPkgArtifactInfo additionalArtifactsItem) {
		if (this.additionalArtifacts == null) {
			this.additionalArtifacts = new ArrayList<>();
		}
		this.additionalArtifacts.add(additionalArtifactsItem);
		return this;
	}

	/**
	 * Information about VNF snapshot artifacts that are not VNFC snapshot images.
	 * The attribute shall not be present before the VNF snapshot package content
	 * has been uploaded or built. Otherwise, this attribute shall be present unless
	 * it has been requested to be excluded per attribute selector.
	 *
	 * @return additionalArtifacts
	 **/
	@Schema(description = "Information about VNF snapshot artifacts that are not VNFC snapshot images.  The attribute shall not be present before the VNF snapshot package content has been  uploaded or built. Otherwise, this attribute shall be present unless it has been requested  to be excluded per attribute selector. ")
	@Valid
	public List<SnapshotPkgArtifactInfo> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(final List<SnapshotPkgArtifactInfo> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public VnfSnapshotPkgInfo state(final StateEnum state) {
		this.state = state;
		return this;
	}

	/**
	 * State of the VNF snapshot package. Permitted values: - CREATED: the VNF
	 * snapshot package information has been created. - BUILDING: the VNF snapshot
	 * package is being built. - UPLOADING: the VNF snapshot package is being
	 * uploaded. - EXTRACTING: the VNF snapshot package’s content is being
	 * extracted. - AVAILABLE: the VNF snapshot package is available (i.e., build or
	 * upload is completed). - PROCESSING: the VNF snapshot package is being
	 * processed. - ERROR: failure during the VNF snapshot package building,
	 * uploading or processing. - ERROR_EXTRACTING: failure during the VNF snapshot
	 * package extraction task.
	 *
	 * @return state
	 **/
	@Schema(required = true, description = "State of the VNF snapshot package. Permitted values: - CREATED: the VNF snapshot package information has been created. - BUILDING: the VNF snapshot package is being built. - UPLOADING: the VNF snapshot package is being uploaded. - EXTRACTING: the VNF snapshot package’s content is being extracted. - AVAILABLE: the VNF snapshot package is available (i.e., build or upload is completed). - PROCESSING: the VNF snapshot package is being processed. - ERROR: failure during the VNF snapshot package building, uploading or processing. - ERROR_EXTRACTING: failure during the VNF snapshot package extraction task. ")
	@NotNull

	public StateEnum getState() {
		return state;
	}

	public void setState(final StateEnum state) {
		this.state = state;
	}

	public VnfSnapshotPkgInfo isCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
		return this;
	}

	/**
	 * Get isCancelPending
	 *
	 * @return isCancelPending
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public Boolean getIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	public VnfSnapshotPkgInfo failureDetails(final VnfSnapshotPkgInfoFailureDetails failureDetails) {
		this.failureDetails = failureDetails;
		return this;
	}

	/**
	 * Get failureDetails
	 *
	 * @return failureDetails
	 **/
	@Schema(description = "")

	@Valid
	public VnfSnapshotPkgInfoFailureDetails getFailureDetails() {
		return failureDetails;
	}

	public void setFailureDetails(final VnfSnapshotPkgInfoFailureDetails failureDetails) {
		this.failureDetails = failureDetails;
	}

	public VnfSnapshotPkgInfo userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 *
	 * @return userDefinedData
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public VnfSnapshotPkgInfo _links(final VnfSnapshotPkgInfoLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public VnfSnapshotPkgInfoLinks getLinks() {
		return _links;
	}

	public void setLinks(final VnfSnapshotPkgInfoLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfSnapshotPkgInfo vnfSnapshotPkgInfo = (VnfSnapshotPkgInfo) o;
		return Objects.equals(this.id, vnfSnapshotPkgInfo.id) &&
				Objects.equals(this.vnfSnapshotPkgUniqueId, vnfSnapshotPkgInfo.vnfSnapshotPkgUniqueId) &&
				Objects.equals(this.name, vnfSnapshotPkgInfo.name) &&
				Objects.equals(this.checksum, vnfSnapshotPkgInfo.checksum) &&
				Objects.equals(this.createdAt, vnfSnapshotPkgInfo.createdAt) &&
				Objects.equals(this.vnfSnapshotId, vnfSnapshotPkgInfo.vnfSnapshotId) &&
				Objects.equals(this.vnfcSnapshotInfoIds, vnfSnapshotPkgInfo.vnfcSnapshotInfoIds) &&
				Objects.equals(this.isFullSnapshot, vnfSnapshotPkgInfo.isFullSnapshot) &&
				Objects.equals(this.vnfdInfo, vnfSnapshotPkgInfo.vnfdInfo) &&
				Objects.equals(this.vnfsr, vnfSnapshotPkgInfo.vnfsr) &&
				Objects.equals(this.vnfcSnapshotImages, vnfSnapshotPkgInfo.vnfcSnapshotImages) &&
				Objects.equals(this.additionalArtifacts, vnfSnapshotPkgInfo.additionalArtifacts) &&
				Objects.equals(this.state, vnfSnapshotPkgInfo.state) &&
				Objects.equals(this.isCancelPending, vnfSnapshotPkgInfo.isCancelPending) &&
				Objects.equals(this.failureDetails, vnfSnapshotPkgInfo.failureDetails) &&
				Objects.equals(this.userDefinedData, vnfSnapshotPkgInfo.userDefinedData) &&
				Objects.equals(this._links, vnfSnapshotPkgInfo._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfSnapshotPkgUniqueId, name, checksum, createdAt, vnfSnapshotId, vnfcSnapshotInfoIds, isFullSnapshot, vnfdInfo, vnfsr, vnfcSnapshotImages, additionalArtifacts, state, isCancelPending, failureDetails, userDefinedData, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfSnapshotPkgInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfSnapshotPkgUniqueId: ").append(toIndentedString(vnfSnapshotPkgUniqueId)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
		sb.append("    vnfSnapshotId: ").append(toIndentedString(vnfSnapshotId)).append("\n");
		sb.append("    vnfcSnapshotInfoIds: ").append(toIndentedString(vnfcSnapshotInfoIds)).append("\n");
		sb.append("    isFullSnapshot: ").append(toIndentedString(isFullSnapshot)).append("\n");
		sb.append("    vnfdInfo: ").append(toIndentedString(vnfdInfo)).append("\n");
		sb.append("    vnfsr: ").append(toIndentedString(vnfsr)).append("\n");
		sb.append("    vnfcSnapshotImages: ").append(toIndentedString(vnfcSnapshotImages)).append("\n");
		sb.append("    additionalArtifacts: ").append(toIndentedString(additionalArtifacts)).append("\n");
		sb.append("    state: ").append(toIndentedString(state)).append("\n");
		sb.append("    isCancelPending: ").append(toIndentedString(isCancelPending)).append("\n");
		sb.append("    failureDetails: ").append(toIndentedString(failureDetails)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
