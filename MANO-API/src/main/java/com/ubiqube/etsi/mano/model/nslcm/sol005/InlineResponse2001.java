package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * InlineResponse2001
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class InlineResponse2001 {
	@JsonProperty("NsLcmOpOcc")
	private NsLcmOpOcc nsLcmOpOcc = null;

	public InlineResponse2001 nsLcmOpOcc(final NsLcmOpOcc nsLcmOpOcc) {
		this.nsLcmOpOcc = nsLcmOpOcc;
		return this;
	}

	/**
	 * Get nsLcmOpOcc
	 * 
	 * @return nsLcmOpOcc
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public NsLcmOpOcc getNsLcmOpOcc() {
		return nsLcmOpOcc;
	}

	public void setNsLcmOpOcc(final NsLcmOpOcc nsLcmOpOcc) {
		this.nsLcmOpOcc = nsLcmOpOcc;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
		return Objects.equals(this.nsLcmOpOcc, inlineResponse2001.nsLcmOpOcc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsLcmOpOcc);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001 {\n");

		sb.append("    nsLcmOpOcc: ").append(toIndentedString(nsLcmOpOcc)).append("\n");
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
