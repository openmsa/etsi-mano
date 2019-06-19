package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Links to resources related to this resource.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Links to resources related to this resource. ")

public class InlineResponse2002Links {

	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf self = null;

	/**
	 **/
	public InlineResponse2002Links self(VnfInstancesInstantiatedVnfInfoLinksSelf self) {
		this.self = self;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("self")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoLinksSelf getSelf() {
		return self;
	}

	public void setSelf(VnfInstancesInstantiatedVnfInfoLinksSelf self) {
		this.self = self;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002Links inlineResponse2002Links = (InlineResponse2002Links) o;
		return Objects.equals(self, inlineResponse2002Links.self);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002Links {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
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
