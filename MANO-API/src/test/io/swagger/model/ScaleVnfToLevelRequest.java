package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents request parameters for the \&quot;Scale VNF to Level\&quot; operation.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents request parameters for the \"Scale VNF to Level\" operation. ")

public class ScaleVnfToLevelRequest {

	private @Valid String instantiationLevelId = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoScaleStatus>();
	private @Valid Object additionalParams = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public ScaleVnfToLevelRequest instantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	@ApiModelProperty(value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("instantiationLevelId")
	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	/**
	 * For each scaling aspect of the current deployment flavour, indicates the
	 * target scale level to which the VNF is to be scaled. Either the
	 * instantiationLevelId attribute or the scaleInfo attribute shall be included.
	 **/
	public ScaleVnfToLevelRequest scaleInfo(List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleInfo) {
		this.scaleInfo = scaleInfo;
		return this;
	}

	@ApiModelProperty(value = "For each scaling aspect of the current deployment flavour, indicates the target scale level to which the VNF is to be scaled. Either the instantiationLevelId attribute or the scaleInfo attribute shall be included. ")
	@JsonProperty("scaleInfo")
	public List<VnfInstancesInstantiatedVnfInfoScaleStatus> getScaleInfo() {
		return scaleInfo;
	}

	public void setScaleInfo(List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleInfo) {
		this.scaleInfo = scaleInfo;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public ScaleVnfToLevelRequest additionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("additionalParams")
	public Object getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleVnfToLevelRequest scaleVnfToLevelRequest = (ScaleVnfToLevelRequest) o;
		return Objects.equals(instantiationLevelId, scaleVnfToLevelRequest.instantiationLevelId) &&
				Objects.equals(scaleInfo, scaleVnfToLevelRequest.scaleInfo) &&
				Objects.equals(additionalParams, scaleVnfToLevelRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(instantiationLevelId, scaleInfo, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleVnfToLevelRequest {\n");

		sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
		sb.append("    scaleInfo: ").append(toIndentedString(scaleInfo)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
