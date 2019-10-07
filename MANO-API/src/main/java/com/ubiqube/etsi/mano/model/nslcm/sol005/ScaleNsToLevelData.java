package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information used to scale an NS instance to a target
 * size. The target size is either expressed as an NS instantiation level or as
 * a list of NS scale levels, one per NS scaling aspect, of the current DF. The
 * NS instantiation levels, the NS scaling aspects and their corresponding NS
 * scale levels applicable to the NS instance are declared in the NSD.
 */
@ApiModel(description = "This type represents the information used to scale an NS instance to a target size. The target size is either expressed as an NS instantiation level or as a list of NS scale levels, one per NS scaling aspect, of the current DF. The NS instantiation levels, the NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ScaleNsToLevelData {
	@JsonProperty("nsInstantiationLevel")
	private String nsInstantiationLevel = null;

	@JsonProperty("nsScaleInfo")
	@Valid
	private List<NsScaleInfo> nsScaleInfo = null;

	public ScaleNsToLevelData nsInstantiationLevel(final String nsInstantiationLevel) {
		this.nsInstantiationLevel = nsInstantiationLevel;
		return this;
	}

	/**
	 * Identifier of the target NS instantiation level of the current DF to which
	 * the NS instance is requested to be scaled.
	 * 
	 * @return nsInstantiationLevel
	 **/
	@ApiModelProperty(value = "Identifier of the target NS instantiation level of the current DF to which the NS instance is requested to be scaled. ")

	public String getNsInstantiationLevel() {
		return nsInstantiationLevel;
	}

	public void setNsInstantiationLevel(final String nsInstantiationLevel) {
		this.nsInstantiationLevel = nsInstantiationLevel;
	}

	public ScaleNsToLevelData nsScaleInfo(final List<NsScaleInfo> nsScaleInfo) {
		this.nsScaleInfo = nsScaleInfo;
		return this;
	}

	public ScaleNsToLevelData addNsScaleInfoItem(final NsScaleInfo nsScaleInfoItem) {
		if (this.nsScaleInfo == null) {
			this.nsScaleInfo = new ArrayList<>();
		}
		this.nsScaleInfo.add(nsScaleInfoItem);
		return this;
	}

	/**
	 * For each NS scaling aspect of the current DF, defines the target NS scale
	 * level to which the NS instance is to be scaled.
	 * 
	 * @return nsScaleInfo
	 **/
	@ApiModelProperty(value = "For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. ")

	@Valid

	public List<NsScaleInfo> getNsScaleInfo() {
		return nsScaleInfo;
	}

	public void setNsScaleInfo(final List<NsScaleInfo> nsScaleInfo) {
		this.nsScaleInfo = nsScaleInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleNsToLevelData scaleNsToLevelData = (ScaleNsToLevelData) o;
		return Objects.equals(this.nsInstantiationLevel, scaleNsToLevelData.nsInstantiationLevel) &&
				Objects.equals(this.nsScaleInfo, scaleNsToLevelData.nsScaleInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsInstantiationLevel, nsScaleInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleNsToLevelData {\n");

		sb.append("    nsInstantiationLevel: ").append(toIndentedString(nsInstantiationLevel)).append("\n");
		sb.append("    nsScaleInfo: ").append(toIndentedString(nsScaleInfo)).append("\n");
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
