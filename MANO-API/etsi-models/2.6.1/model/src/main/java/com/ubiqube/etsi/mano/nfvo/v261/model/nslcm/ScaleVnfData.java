package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

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
 * This type represents defines the information to scale a VNF instance to a
 * given level, or to scale a VNF instance by steps.
 */
@ApiModel(description = "This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. ")
@Validated


public class ScaleVnfData {
	@JsonProperty("vnfInstanceid")
	private String vnfInstanceid = null;

	/**
	 * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT -
	 * SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of
	 * types actually supported depends on the capabilities of the VNF being
	 * managed.
	 */
	public enum ScaleVnfTypeEnum {
		OUT("SCALE_OUT"),

		IN("SCALE_IN"),

		TO_INSTANTIATION_LEVEL("SCALE_TO_INSTANTIATION_LEVEL"),

		TO_SCALE_LEVEL_S_("SCALE_TO_SCALE_LEVEL(S)");

		private final String value;

		ScaleVnfTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ScaleVnfTypeEnum fromValue(final String text) {
			for (final ScaleVnfTypeEnum b : ScaleVnfTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("scaleVnfType")
	private ScaleVnfTypeEnum scaleVnfType = null;

	@JsonProperty("scaleToLevelData")
	private ScaleToLevelData scaleToLevelData = null;

	@JsonProperty("scaleByStepData")
	private ScaleByStepData scaleByStepData = null;

	public ScaleVnfData vnfInstanceid(final String vnfInstanceid) {
		this.vnfInstanceid = vnfInstanceid;
		return this;
	}

	/**
	 * Identifier of the VNF instance being scaled.
	 * 
	 * @return vnfInstanceid
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance being scaled. ")
	@NotNull

	public String getVnfInstanceid() {
		return vnfInstanceid;
	}

	public void setVnfInstanceid(final String vnfInstanceid) {
		this.vnfInstanceid = vnfInstanceid;
	}

	public ScaleVnfData scaleVnfType(final ScaleVnfTypeEnum scaleVnfType) {
		this.scaleVnfType = scaleVnfType;
		return this;
	}

	/**
	 * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT -
	 * SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of
	 * types actually supported depends on the capabilities of the VNF being
	 * managed.
	 * 
	 * @return scaleVnfType
	 **/
	@ApiModelProperty(required = true, value = "Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. ")
	@NotNull

	public ScaleVnfTypeEnum getScaleVnfType() {
		return scaleVnfType;
	}

	public void setScaleVnfType(final ScaleVnfTypeEnum scaleVnfType) {
		this.scaleVnfType = scaleVnfType;
	}

	public ScaleVnfData scaleToLevelData(final ScaleToLevelData scaleToLevelData) {
		this.scaleToLevelData = scaleToLevelData;
		return this;
	}

	/**
	 * The information used for scaling to a given level.
	 * 
	 * @return scaleToLevelData
	 **/
	@ApiModelProperty(value = "The information used for scaling to a given level. ")

	@Valid

	public ScaleToLevelData getScaleToLevelData() {
		return scaleToLevelData;
	}

	public void setScaleToLevelData(final ScaleToLevelData scaleToLevelData) {
		this.scaleToLevelData = scaleToLevelData;
	}

	public ScaleVnfData scaleByStepData(final ScaleByStepData scaleByStepData) {
		this.scaleByStepData = scaleByStepData;
		return this;
	}

	/**
	 * The information used for scaling by steps.
	 * 
	 * @return scaleByStepData
	 **/
	@ApiModelProperty(value = "The information used for scaling by steps. ")

	@Valid

	public ScaleByStepData getScaleByStepData() {
		return scaleByStepData;
	}

	public void setScaleByStepData(final ScaleByStepData scaleByStepData) {
		this.scaleByStepData = scaleByStepData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleVnfData scaleVnfData = (ScaleVnfData) o;
		return Objects.equals(this.vnfInstanceid, scaleVnfData.vnfInstanceid) &&
				Objects.equals(this.scaleVnfType, scaleVnfData.scaleVnfType) &&
				Objects.equals(this.scaleToLevelData, scaleVnfData.scaleToLevelData) &&
				Objects.equals(this.scaleByStepData, scaleVnfData.scaleByStepData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceid, scaleVnfType, scaleToLevelData, scaleByStepData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleVnfData {\n");

		sb.append("    vnfInstanceid: ").append(toIndentedString(vnfInstanceid)).append("\n");
		sb.append("    scaleVnfType: ").append(toIndentedString(scaleVnfType)).append("\n");
		sb.append("    scaleToLevelData: ").append(toIndentedString(scaleToLevelData)).append("\n");
		sb.append("    scaleByStepData: ").append(toIndentedString(scaleByStepData)).append("\n");
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
