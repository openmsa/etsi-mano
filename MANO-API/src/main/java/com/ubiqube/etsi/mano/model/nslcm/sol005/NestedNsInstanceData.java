package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies an existing nested NS instance to be used in the NS
 * instance and if needed, the NsProfile to use for this nested NS instance. It
 * shall comply with the provisions defined in Table 6.5.3.19a-1.
 */
@ApiModel(description = "This type specifies an existing nested NS instance to be used in the NS instance  and if needed, the NsProfile to use for this nested NS instance.  It shall comply with the provisions defined in Table 6.5.3.19a-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class NestedNsInstanceData {
	@JsonProperty("nestedNsInstanceId")
	private String nestedNsInstanceId = null;

	@JsonProperty("nsProfileId")
	private String nsProfileId = null;

	public NestedNsInstanceData nestedNsInstanceId(final String nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
		return this;
	}

	/**
	 * Identifier of the existing nested NS instance to be used in the NS.
	 * 
	 * @return nestedNsInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the existing nested NS instance to be used in the NS. ")
	@NotNull

	public String getNestedNsInstanceId() {
		return nestedNsInstanceId;
	}

	public void setNestedNsInstanceId(final String nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
	}

	public NestedNsInstanceData nsProfileId(final String nsProfileId) {
		this.nsProfileId = nsProfileId;
		return this;
	}

	/**
	 * Identifier of an NsProfile defined in the NSD which the existing nested NS
	 * instance shall be matched with. If not present, the NFVO will select the
	 * NsProfile matching the information in the nested NS instance.
	 * 
	 * @return nsProfileId
	 **/
	@ApiModelProperty(value = "Identifier of an NsProfile defined in the NSD which the existing  nested NS instance shall be matched with. If not present, the NFVO will select the NsProfile matching the  information in the nested NS instance. ")

	public String getNsProfileId() {
		return nsProfileId;
	}

	public void setNsProfileId(final String nsProfileId) {
		this.nsProfileId = nsProfileId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NestedNsInstanceData nestedNsInstanceData = (NestedNsInstanceData) o;
		return Objects.equals(this.nestedNsInstanceId, nestedNsInstanceData.nestedNsInstanceId) &&
				Objects.equals(this.nsProfileId, nestedNsInstanceData.nsProfileId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nestedNsInstanceId, nsProfileId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NestedNsInstanceData {\n");

		sb.append("    nestedNsInstanceId: ").append(toIndentedString(nestedNsInstanceId)).append("\n");
		sb.append("    nsProfileId: ").append(toIndentedString(nsProfileId)).append("\n");
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
