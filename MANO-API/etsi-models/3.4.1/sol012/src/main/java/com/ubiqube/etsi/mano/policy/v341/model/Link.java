package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a link to a resource using an absolute URI.
 */
@Schema(description = "This type represents a link to a resource using an absolute URI. ")
@Validated

public class Link {
	@JsonProperty("href")
	private String href = null;

	public Link() {
		// Nothing.
	}

	public Link(final String href) {
		super();
		this.href = href;
	}

	public Link href(final String href) {
		this.href = href;
		return this;
	}

	/**
	 * Get href
	 *
	 * @return href
	 **/
	@Schema(required = true, description = "")
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
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
