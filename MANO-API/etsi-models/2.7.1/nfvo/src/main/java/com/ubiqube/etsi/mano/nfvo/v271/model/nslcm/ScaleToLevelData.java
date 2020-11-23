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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type describes the information used to scale a VNF instance to a target
 * size. The target size is either expressed as an instantiation level of that
 * DF as defined in the VNFD, or given as a list of scale levels, one per
 * scaling aspect of that DF. Instantiation levels and scaling aspects are
 * declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel
 * operation towards the appropriate VNFM..
 */
@ApiModel(description = "This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM.. ")
@Validated
public class ScaleToLevelData {
	@JsonProperty("vnfInstantiationLevelId")
	private String vnfInstantiationLevelId = null;

	@JsonProperty("vnfScaleInfo")
	@Valid
	private List<VnfScaleInfo> vnfScaleInfo = null;

	@JsonProperty("additionalParams")
	private Map<String, Object> additionalParams = null;

	public ScaleToLevelData vnfInstantiationLevelId(final String vnfInstantiationLevelId) {
		this.vnfInstantiationLevelId = vnfInstantiationLevelId;
		return this;
	}

	/**
	 * Get vnfInstantiationLevelId
	 *
	 * @return vnfInstantiationLevelId
	 **/
	@ApiModelProperty(value = "")

	public String getVnfInstantiationLevelId() {
		return vnfInstantiationLevelId;
	}

	public void setVnfInstantiationLevelId(final String vnfInstantiationLevelId) {
		this.vnfInstantiationLevelId = vnfInstantiationLevelId;
	}

	public ScaleToLevelData vnfScaleInfo(final List<VnfScaleInfo> vnfScaleInfo) {
		this.vnfScaleInfo = vnfScaleInfo;
		return this;
	}

	public ScaleToLevelData addVnfScaleInfoItem(final VnfScaleInfo vnfScaleInfoItem) {
		if (this.vnfScaleInfo == null) {
			this.vnfScaleInfo = new ArrayList<>();
		}
		this.vnfScaleInfo.add(vnfScaleInfoItem);
		return this;
	}

	/**
	 * For each scaling aspect of the current deployment flavor, indicates the
	 * target scale level to which the VNF is to be scaled.
	 *
	 * @return vnfScaleInfo
	 **/
	@ApiModelProperty(value = "For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. ")
	@Valid
	public List<VnfScaleInfo> getVnfScaleInfo() {
		return vnfScaleInfo;
	}

	public void setVnfScaleInfo(final List<VnfScaleInfo> vnfScaleInfo) {
		this.vnfScaleInfo = vnfScaleInfo;
	}

	public ScaleToLevelData additionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleToLevelData scaleToLevelData = (ScaleToLevelData) o;
		return Objects.equals(this.vnfInstantiationLevelId, scaleToLevelData.vnfInstantiationLevelId) &&
				Objects.equals(this.vnfScaleInfo, scaleToLevelData.vnfScaleInfo) &&
				Objects.equals(this.additionalParams, scaleToLevelData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstantiationLevelId, vnfScaleInfo, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleToLevelData {\n");

		sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
		sb.append("    vnfScaleInfo: ").append(toIndentedString(vnfScaleInfo)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
