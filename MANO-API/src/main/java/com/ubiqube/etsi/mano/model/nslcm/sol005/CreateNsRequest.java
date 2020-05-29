package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateNsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class CreateNsRequest {
	@JsonProperty("nsdId")
	private String nsdId = null;

	@JsonProperty("nsName")
	private String nsName = null;

	@JsonProperty("nsDescription")
	private String nsDescription = null;

	public CreateNsRequest nsdId(final String nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * Identifier of the NSD that defines the NS instance to be created.
	 *
	 * @return nsdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the NSD that defines the NS instance to be created. ")
	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public CreateNsRequest nsName(final String nsName) {
		this.nsName = nsName;
		return this;
	}

	/**
	 * Human-readable name of the NS instance to be created.
	 *
	 * @return nsName
	 **/
	@ApiModelProperty(required = true, value = "Human-readable name of the NS instance to be created. ")
	@NotNull

	public String getNsName() {
		return nsName;
	}

	public void setNsName(final String nsName) {
		this.nsName = nsName;
	}

	public CreateNsRequest nsDescription(final String nsDescription) {
		this.nsDescription = nsDescription;
		return this;
	}

	/**
	 * Human-readable description of the NS instance to be created.
	 *
	 * @return nsDescription
	 **/
	@ApiModelProperty(required = true, value = "Human-readable description of the NS instance to be created. ")
	@NotNull

	public String getNsDescription() {
		return nsDescription;
	}

	public void setNsDescription(final String nsDescription) {
		this.nsDescription = nsDescription;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CreateNsRequest createNsRequest = (CreateNsRequest) o;
		return Objects.equals(this.nsdId, createNsRequest.nsdId) &&
				Objects.equals(this.nsName, createNsRequest.nsName) &&
				Objects.equals(this.nsDescription, createNsRequest.nsDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsdId, nsName, nsDescription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateNsRequest {\n");

		sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
		sb.append("    nsName: ").append(toIndentedString(nsName)).append("\n");
		sb.append("    nsDescription: ").append(toIndentedString(nsDescription)).append("\n");
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
