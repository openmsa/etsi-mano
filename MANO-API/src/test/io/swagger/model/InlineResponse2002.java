package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents a subscription related to notifications about VNF lifecycle changes.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a subscription related to notifications about VNF lifecycle changes. ")

public class InlineResponse2002 {

	private @Valid String id = null;
	private @Valid InlineResponse2002Filter filter = null;
	private @Valid String callbackUri = null;
	private @Valid InlineResponse2002Links links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse2002 id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 **/
	public InlineResponse2002 filter(InlineResponse2002Filter filter) {
		this.filter = filter;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("filter")
	public InlineResponse2002Filter getFilter() {
		return filter;
	}

	public void setFilter(InlineResponse2002Filter filter) {
		this.filter = filter;
	}

	/**
	 * String formatted according to IETF RFC 3986.
	 **/
	public InlineResponse2002 callbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	@ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
	@JsonProperty("callbackUri")
	@NotNull
	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
	}

	/**
	 **/
	public InlineResponse2002 links(InlineResponse2002Links links) {
		this.links = links;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("_links")
	@NotNull
	public InlineResponse2002Links getLinks() {
		return links;
	}

	public void setLinks(InlineResponse2002Links links) {
		this.links = links;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002 inlineResponse2002 = (InlineResponse2002) o;
		return Objects.equals(id, inlineResponse2002.id) &&
				Objects.equals(filter, inlineResponse2002.filter) &&
				Objects.equals(callbackUri, inlineResponse2002.callbackUri) &&
				Objects.equals(links, inlineResponse2002.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002 {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
