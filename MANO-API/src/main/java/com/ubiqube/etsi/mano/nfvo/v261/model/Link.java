package com.ubiqube.etsi.mano.nfvo.v261.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a link to a resource using an absolute URI.
 */
@ApiModel(description = "This type represents a link to a resource using an absolute URI. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class Link {
	@JsonProperty("href")
	private String href = null;

	public Link href(final String _href) {
		this.href = _href;
		return this;
	}

	/**
	 * URI of another resource referenced from a resource. Shall be an absolute URI
	 * (i.e. a UTI that contains {apiRoot}).
	 *
	 * @return href
	 **/
	@ApiModelProperty(required = true, value = "URI of another resource referenced from a resource. Shall be an absolute URI (i.e. a UTI that contains {apiRoot}). ")
	@NotNull

	public String getHref() {
		return href;
	}

	public void setHref(final String href) {
		this.href = href;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Link link = (Link) o;
		return Objects.equals(this.href, link.href);
	}

	@Override
	public int hashCode() {
		return Objects.hash(href);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Link {\n");

		sb.append("    href: ").append(toIndentedString(href)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
