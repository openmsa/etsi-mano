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
package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VnfScaleInfo
 */
@Validated
public class VnfScaleInfo {
	@JsonProperty("aspectId")
	private String aspectId = null;

	@JsonProperty("scaleLevel")
	private Integer scaleLevel = null;

	public VnfScaleInfo aspectId(final String aspectId) {
		this.aspectId = aspectId;
		return this;
	}

	/**
	 * Identifier of the scaling aspect.
	 *
	 * @return aspectId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the scaling aspect. ")
	@NotNull

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	public VnfScaleInfo scaleLevel(final Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
		return this;
	}

	/**
	 * Indicates the scale level. The minimum value shall be 0 and the maximum value
	 * shall be <= maxScaleLevel as described in the VNFD.
	 *
	 * @return scaleLevel
	 **/
	@ApiModelProperty(required = true, value = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. ")
	@NotNull

	public Integer getScaleLevel() {
		return scaleLevel;
	}

	public void setScaleLevel(final Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfScaleInfo vnfScaleInfo = (VnfScaleInfo) o;
		return Objects.equals(this.aspectId, vnfScaleInfo.aspectId) &&
				Objects.equals(this.scaleLevel, vnfScaleInfo.scaleLevel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aspectId, scaleLevel);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfScaleInfo {\n");

		sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
		sb.append("    scaleLevel: ").append(toIndentedString(scaleLevel)).append("\n");
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
