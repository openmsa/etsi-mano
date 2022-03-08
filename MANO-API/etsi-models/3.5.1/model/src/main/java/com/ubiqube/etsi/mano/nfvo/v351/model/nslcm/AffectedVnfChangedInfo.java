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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ModificationsTriggeredByVnfPkgChange;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Information about the changed VNF instance information, including VNF
 * configurable properties,if applicable. When the \&quot;changedInfo\&quot;
 * attribute is present, either the \&quot;changedVnfInfo\&quot; attribute or
 * the \&quot;changedExtConnectivity\&quot; attribute or both shall be present.
 */
@Schema(description = "Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \"changedInfo\" attribute is present,  either the \"changedVnfInfo\" attribute or the \"changedExtConnectivity\" attribute or both shall be present. ")
@Validated

public class AffectedVnfChangedInfo {
	@JsonProperty("changedVnfInfo")
	private ModifyVnfInfoData changedVnfInfo = null;

	@JsonProperty("changedExtConnectivity")
	@Valid
	private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

	@JsonProperty("modificationsTriggeredByVnfPkgChange")
	private ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange = null;

	public AffectedVnfChangedInfo changedVnfInfo(final ModifyVnfInfoData changedVnfInfo) {
		this.changedVnfInfo = changedVnfInfo;
		return this;
	}

	/**
	 * Get changedVnfInfo
	 *
	 * @return changedVnfInfo
	 **/
	@Schema(description = "")

	@Valid
	public ModifyVnfInfoData getChangedVnfInfo() {
		return changedVnfInfo;
	}

	public void setChangedVnfInfo(final ModifyVnfInfoData changedVnfInfo) {
		this.changedVnfInfo = changedVnfInfo;
	}

	public AffectedVnfChangedInfo changedExtConnectivity(final List<ExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
		return this;
	}

	public AffectedVnfChangedInfo addChangedExtConnectivityItem(final ExtVirtualLinkInfo changedExtConnectivityItem) {
		if (this.changedExtConnectivity == null) {
			this.changedExtConnectivity = new ArrayList<>();
		}
		this.changedExtConnectivity.add(changedExtConnectivityItem);
		return this;
	}

	/**
	 * Information about changed external connectivity, if applicable. Only
	 * information about external VL instances that have been added or modified
	 * shall be provided. See note.
	 *
	 * @return changedExtConnectivity
	 **/
	@Schema(description = "Information about changed external connectivity, if applicable. Only information about external VL instances that have been added or modified shall be provided. See note. ")
	@Valid
	public List<ExtVirtualLinkInfo> getChangedExtConnectivity() {
		return changedExtConnectivity;
	}

	public void setChangedExtConnectivity(final List<ExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
	}

	public AffectedVnfChangedInfo modificationsTriggeredByVnfPkgChange(final ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
		this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
		return this;
	}

	/**
	 * Get modificationsTriggeredByVnfPkgChange
	 *
	 * @return modificationsTriggeredByVnfPkgChange
	 **/
	@Schema(description = "")

	@Valid
	public ModificationsTriggeredByVnfPkgChange getModificationsTriggeredByVnfPkgChange() {
		return modificationsTriggeredByVnfPkgChange;
	}

	public void setModificationsTriggeredByVnfPkgChange(final ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
		this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final AffectedVnfChangedInfo affectedVnfChangedInfo = (AffectedVnfChangedInfo) o;
		return Objects.equals(this.changedVnfInfo, affectedVnfChangedInfo.changedVnfInfo) &&
				Objects.equals(this.changedExtConnectivity, affectedVnfChangedInfo.changedExtConnectivity) &&
				Objects.equals(this.modificationsTriggeredByVnfPkgChange, affectedVnfChangedInfo.modificationsTriggeredByVnfPkgChange);
	}

	@Override
	public int hashCode() {
		return Objects.hash(changedVnfInfo, changedExtConnectivity, modificationsTriggeredByVnfPkgChange);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedVnfChangedInfo {\n");

		sb.append("    changedVnfInfo: ").append(toIndentedString(changedVnfInfo)).append("\n");
		sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
		sb.append("    modificationsTriggeredByVnfPkgChange: ").append(toIndentedString(modificationsTriggeredByVnfPkgChange)).append("\n");
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
