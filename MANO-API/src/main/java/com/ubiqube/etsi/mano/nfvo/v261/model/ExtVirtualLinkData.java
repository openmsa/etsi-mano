package com.ubiqube.etsi.mano.nfvo.v261.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an external VL. It shall comply with the provisions
 * defined in Table 6.5.3.26-1.
 */
@ApiModel(description = "This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class ExtVirtualLinkData {
	@JsonProperty("extVirtualLinkId")
	private String extVirtualLinkId = null;

	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	@JsonProperty("extCps")
	@Valid
	private List<VnfExtCpData> extCps = new ArrayList<>();

	@JsonProperty("extLinkPorts")
	@Valid
	private List<ExtLinkPortData> extLinkPorts = null;

	public ExtVirtualLinkData extVirtualLinkId(final String extVirtualLinkId) {
		this.extVirtualLinkId = extVirtualLinkId;
		return this;
	}

	/**
	 * The identifier of the external VL instance, if provided.
	 *
	 * @return extVirtualLinkId
	 **/
	@ApiModelProperty(value = "The identifier of the external VL instance, if provided. ")

	public String getExtVirtualLinkId() {
		return extVirtualLinkId;
	}

	public void setExtVirtualLinkId(final String extVirtualLinkId) {
		this.extVirtualLinkId = extVirtualLinkId;
	}

	public ExtVirtualLinkData vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * Identifier of the VIM that manages this resource. This attribute shall only
	 * be supported and present if VNFrelated resource management in direct mode is
	 * applicable.
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "Identifier of the VIM that manages this resource. This attribute shall only be supported and present if VNFrelated resource management in direct mode is applicable. ")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public ExtVirtualLinkData resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management of this resource. This
	 * attribute shall only be supported and present if VNF-related resource
	 * management in indirect mode is applicable. The identification scheme is
	 * outside the scope of the present document.
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public ExtVirtualLinkData resourceId(final String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * The identifier of the resource in the scope of the VIM or the resource
	 * provider.
	 *
	 * @return resourceId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the resource in the scope of the VIM or the resource provider. ")
	@NotNull

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public ExtVirtualLinkData extCps(final List<VnfExtCpData> extCps) {
		this.extCps = extCps;
		return this;
	}

	public ExtVirtualLinkData addExtCpsItem(final VnfExtCpData extCpsItem) {
		this.extCps.add(extCpsItem);
		return this;
	}

	/**
	 * External CPs of the VNF to be connected to this external VL.
	 *
	 * @return extCps
	 **/
	@ApiModelProperty(required = true, value = "External CPs of the VNF to be connected to this external VL. ")
	@NotNull

	@Valid

	public List<VnfExtCpData> getExtCps() {
		return extCps;
	}

	public void setExtCps(final List<VnfExtCpData> extCps) {
		this.extCps = extCps;
	}

	public ExtVirtualLinkData extLinkPorts(final List<ExtLinkPortData> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
		return this;
	}

	public ExtVirtualLinkData addExtLinkPortsItem(final ExtLinkPortData extLinkPortsItem) {
		if (this.extLinkPorts == null) {
			this.extLinkPorts = new ArrayList<>();
		}
		this.extLinkPorts.add(extLinkPortsItem);
		return this;
	}

	/**
	 * Externally provided link ports to be used to connect external connection
	 * points to this external VL.
	 *
	 * @return extLinkPorts
	 **/
	@ApiModelProperty(value = "Externally provided link ports to be used to connect external connection points to this external VL. ")

	@Valid

	public List<ExtLinkPortData> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(final List<ExtLinkPortData> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExtVirtualLinkData extVirtualLinkData = (ExtVirtualLinkData) o;
		return Objects.equals(this.extVirtualLinkId, extVirtualLinkData.extVirtualLinkId) &&
				Objects.equals(this.vimId, extVirtualLinkData.vimId) &&
				Objects.equals(this.resourceProviderId, extVirtualLinkData.resourceProviderId) &&
				Objects.equals(this.resourceId, extVirtualLinkData.resourceId) &&
				Objects.equals(this.extCps, extVirtualLinkData.extCps) &&
				Objects.equals(this.extLinkPorts, extVirtualLinkData.extLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(extVirtualLinkId, vimId, resourceProviderId, resourceId, extCps, extLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtVirtualLinkData {\n");

		sb.append("    extVirtualLinkId: ").append(toIndentedString(extVirtualLinkId)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
		sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
		sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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
