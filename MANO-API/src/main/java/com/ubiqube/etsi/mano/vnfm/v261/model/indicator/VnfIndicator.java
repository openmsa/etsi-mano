package com.ubiqube.etsi.mano.vnfm.v261.model.indicator;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF indicator value.
 */
@ApiModel(description = "This type represents a VNF indicator value. ")
@Validated


public class VnfIndicator {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("value")
	private Object value = null;

	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("_links")
	private VnfIndicatorLinks links = null;

	public VnfIndicator id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VNF indicator.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this VNF indicator. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfIndicator name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human readable name of the indicator. Shall be present if defined in the
	 * VNFD.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "Human readable name of the indicator. Shall be present if defined in the VNFD. ")

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public VnfIndicator value(final Object value) {
		this.value = value;
		return this;
	}

	/**
	 * Provides the value of the indicator. The value format is defined in the VNFD.
	 * ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on
	 * TOSCA specifications.
	 * 
	 * @return value
	 **/
	@ApiModelProperty(required = true, value = "Provides the value of the indicator. The value format is defined in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the  VNFD based on TOSCA specifications. ")
	@NotNull

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public VnfIndicator vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the \"Individual VNF instance\" which provides the indicator
	 * value.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the \"Individual VNF instance\" which provides the indicator value. ")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public VnfIndicator links(final VnfIndicatorLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public VnfIndicatorLinks getLinks() {
		return links;
	}

	public void setLinks(final VnfIndicatorLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfIndicator vnfIndicator = (VnfIndicator) o;
		return Objects.equals(this.id, vnfIndicator.id) &&
				Objects.equals(this.name, vnfIndicator.name) &&
				Objects.equals(this.value, vnfIndicator.value) &&
				Objects.equals(this.vnfInstanceId, vnfIndicator.vnfInstanceId) &&
				Objects.equals(this.links, vnfIndicator.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, value, vnfInstanceId, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfIndicator {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
