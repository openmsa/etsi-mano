package com.ubiqube.etsi.mano.nfvo.v261.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an externally-managed internal VL. It shall comply with
 * the provisions defined in Table 6.5.3.27-1.
 */
@ApiModel(description = "This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ExtManagedVirtualLinkData {
	@JsonProperty("extManagedVirtualLinkId")
	private String extManagedVirtualLinkId = null;

	@JsonProperty("vmfVirtualLinkDescId")
	private String vmfVirtualLinkDescId = null;

	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	public ExtManagedVirtualLinkData extManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
		return this;
	}

	/**
	 * The identifier of the externally-managed internal VL instance, if provided.
	 *
	 * @return extManagedVirtualLinkId
	 **/
	@ApiModelProperty(value = "The identifier of the externally-managed internal VL instance, if provided. ")

	public String getExtManagedVirtualLinkId() {
		return extManagedVirtualLinkId;
	}

	public void setExtManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
	}

	public ExtManagedVirtualLinkData vmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
		return this;
	}

	/**
	 * The identifier of the VLD in the VNFD for this VL.
	 *
	 * @return vmfVirtualLinkDescId
	 **/
	@ApiModelProperty(value = "The identifier of the VLD in the VNFD for this VL. ")

	public String getVmfVirtualLinkDescId() {
		return vmfVirtualLinkDescId;
	}

	public void setVmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
	}

	public ExtManagedVirtualLinkData vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * Identifier of the VIM that manage this resource. This attribute shall only be
	 * supported and present if VNFrelated resource management in direct mode is
	 * applicable.
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "Identifier of the VIM that manage this resource. This attribute shall only be supported and present if VNFrelated resource management in direct mode is applicable. ")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public ExtManagedVirtualLinkData resourceProviderId(final String resourceProviderId) {
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

	public ExtManagedVirtualLinkData resourceId(final String resourceId) {
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

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExtManagedVirtualLinkData extManagedVirtualLinkData = (ExtManagedVirtualLinkData) o;
		return Objects.equals(this.extManagedVirtualLinkId, extManagedVirtualLinkData.extManagedVirtualLinkId) &&
				Objects.equals(this.vmfVirtualLinkDescId, extManagedVirtualLinkData.vmfVirtualLinkDescId) &&
				Objects.equals(this.vimId, extManagedVirtualLinkData.vimId) &&
				Objects.equals(this.resourceProviderId, extManagedVirtualLinkData.resourceProviderId) &&
				Objects.equals(this.resourceId, extManagedVirtualLinkData.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(extManagedVirtualLinkId, vmfVirtualLinkDescId, vimId, resourceProviderId, resourceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtManagedVirtualLinkData {\n");

		sb.append("    extManagedVirtualLinkId: ").append(toIndentedString(extManagedVirtualLinkId)).append("\n");
		sb.append("    vmfVirtualLinkDescId: ").append(toIndentedString(vmfVirtualLinkDescId)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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
