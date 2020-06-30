package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.CancelModeType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a parameter to select the mode of canceling an ongoing
 * NS LCM operation occurrence. It shall comply with the provisions defined in
 * Table 6.5.2.16-1.
 */
@ApiModel(description = "This type represents a parameter to select the mode of canceling an ongoing NS LCM operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.16-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class CancelMode {
	@JsonProperty("cancelMode")
	private CancelModeType cancelMode = null;

	public CancelMode cancelMode(final CancelModeType cancelMode) {
		this.cancelMode = cancelMode;
		return this;
	}

	/**
	 * Cancellation mode to apply.
	 * 
	 * @return cancelMode
	 **/
	@ApiModelProperty(required = true, value = "Cancellation mode to apply. ")
	@NotNull

	@Valid

	public CancelModeType getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(final CancelModeType cancelMode) {
		this.cancelMode = cancelMode;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CancelMode cancelMode = (CancelMode) o;
		return Objects.equals(this.cancelMode, cancelMode.cancelMode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cancelMode);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CancelMode {\n");

		sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
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
