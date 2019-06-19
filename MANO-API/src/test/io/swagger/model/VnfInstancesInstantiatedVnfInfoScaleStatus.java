package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoScaleStatus {

	private @Valid String aspectId = null;
	private @Valid Integer scaleLevel = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesInstantiatedVnfInfoScaleStatus aspectId(String aspectId) {
		this.aspectId = aspectId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("aspectId")
	@NotNull
	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(String aspectId) {
		this.aspectId = aspectId;
	}

	/**
	 * Indicates the scale level. The minimum value shall be 0 and the maximum value
	 * shall be &lt;&#x3D; maxScaleLevel as described in the VNFD.
	 **/
	public VnfInstancesInstantiatedVnfInfoScaleStatus scaleLevel(Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
		return this;
	}

	@ApiModelProperty(required = true, value = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. ")
	@JsonProperty("scaleLevel")
	@NotNull
	public Integer getScaleLevel() {
		return scaleLevel;
	}

	public void setScaleLevel(Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoScaleStatus vnfInstancesInstantiatedVnfInfoScaleStatus = (VnfInstancesInstantiatedVnfInfoScaleStatus) o;
		return Objects.equals(aspectId, vnfInstancesInstantiatedVnfInfoScaleStatus.aspectId) &&
				Objects.equals(scaleLevel, vnfInstancesInstantiatedVnfInfoScaleStatus.scaleLevel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aspectId, scaleLevel);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoScaleStatus {\n");

		sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
		sb.append("    scaleLevel: ").append(toIndentedString(scaleLevel)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
