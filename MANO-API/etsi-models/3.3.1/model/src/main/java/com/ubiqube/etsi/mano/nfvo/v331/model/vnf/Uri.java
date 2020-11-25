package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;

/**
 * String formatted according to IETF RFC 3986.
 */
@ApiModel(description = "String formatted according to IETF RFC 3986. ")
@Validated
public class Uri {

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Uri {\n");

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
