package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * InlineResponse200
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class InlineResponse200 {
	@JsonProperty("NsInstance")
	private NsInstance nsInstance = null;

	public InlineResponse200 nsInstance(final NsInstance nsInstance) {
		this.nsInstance = nsInstance;
		return this;
	}

	/**
	 * Get nsInstance
	 * 
	 * @return nsInstance
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public NsInstance getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final NsInstance nsInstance) {
		this.nsInstance = nsInstance;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse200 inlineResponse200 = (InlineResponse200) o;
		return Objects.equals(this.nsInstance, inlineResponse200.nsInstance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsInstance);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse200 {\n");

		sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
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
