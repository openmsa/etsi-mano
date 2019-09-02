package com.ubiqube.etsi.mano.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;

/**
 * This type represents a list of key-value pairs. The order of the pairs in the
 * list is not significant. In JSON, a set of keyvalue pairs is represented as
 * an object. It shall comply with the provisions defined in clause 4 of IETF
 * RFC 8259. In the following example, a list of key-value pairs with four keys
 * (\&quot;aString\&quot;, \&quot;aNumber\&quot;, \&quot;anArray\&quot; and
 * \&quot;anObject\&quot;) is provided to illustrate that the values associated
 * with different keys can be of different type.
 */
@ApiModel(description = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of keyvalue pairs is represented as an object. It shall comply with the provisions defined in clause 4 of IETF RFC 8259. In the following example, a list of key-value pairs with four keys (\"aString\", \"aNumber\", \"anArray\" and \"anObject\") is provided to illustrate that the values associated with different keys can be of different type. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class KeyValuePairs {

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
		sb.append("class KeyValuePairs {\n");

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
