package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type defines the additional parameters for the VNF instance to be
 * created associated with an NS instance. It shall comply with the provisions
 * defined in Table 6.5.3.22-1.
 */
@ApiModel(description = "This type defines the additional parameters for the VNF instance to be created associated with an NS instance. It shall comply with the provisions defined in Table 6.5.3.22-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ParamsForVnf {
	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	@JsonProperty("additionalParams")
	private KeyValuePairs additionalParams = null;

	public ParamsForVnf vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Identifier of (reference to) a vnfProfile to which the additional parameters
	 * apply.
	 *
	 * @return vnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of (reference to) a vnfProfile to which the additional parameters apply. ")
	@NotNull

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public ParamsForVnf additionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters that are applied for the VNF instance to be created.
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional parameters that are applied for the VNF instance to be created. ")

	@Valid

	public KeyValuePairs getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final KeyValuePairs additionalParams) {
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
		final ParamsForVnf paramsForVnf = (ParamsForVnf) o;
		return Objects.equals(this.vnfProfileId, paramsForVnf.vnfProfileId) &&
				Objects.equals(this.additionalParams, paramsForVnf.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProfileId, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ParamsForVnf {\n");

		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
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
