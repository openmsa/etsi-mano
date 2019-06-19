package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents an external VL.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents an external VL. ")

public class VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks {

	private @Valid String id = null;
	private @Valid String vimConnectionId = null;
	private @Valid String resourceProviderId = null;
	private @Valid String resourceId = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtCps> extCps = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtCps>();
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtLinkPorts> extLinkPorts = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtLinkPorts>();

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks id(String id) {
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
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks vimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vimConnectionId")
	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks resourceProviderId(String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("resourceProviderId")
	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	/**
	 * An identifier maintained by the VIM or other resource provider. It is
	 * expected to be unique within the VIM instance.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks resourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
	@JsonProperty("resourceId")
	@NotNull
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * External CPs of the VNF to be connected to this external VL.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks extCps(List<VnfInstancesvnfInstanceIdinstantiateExtCps> extCps) {
		this.extCps = extCps;
		return this;
	}

	@ApiModelProperty(required = true, value = "External CPs of the VNF to be connected to this external VL. ")
	@JsonProperty("extCps")
	@NotNull
	public List<VnfInstancesvnfInstanceIdinstantiateExtCps> getExtCps() {
		return extCps;
	}

	public void setExtCps(List<VnfInstancesvnfInstanceIdinstantiateExtCps> extCps) {
		this.extCps = extCps;
	}

	/**
	 * Externally provided link ports to be used to connect external connection
	 * points to this external VL. If this attribute is not present, the VNFM shall
	 * create the link ports on the external VL.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks extLinkPorts(List<VnfInstancesvnfInstanceIdinstantiateExtLinkPorts> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
		return this;
	}

	@ApiModelProperty(value = "Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL.  ")
	@JsonProperty("extLinkPorts")
	public List<VnfInstancesvnfInstanceIdinstantiateExtLinkPorts> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(List<VnfInstancesvnfInstanceIdinstantiateExtLinkPorts> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks = (VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks) o;
		return Objects.equals(id, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.id) &&
				Objects.equals(vimConnectionId, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.vimConnectionId) &&
				Objects.equals(resourceProviderId, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.resourceProviderId) &&
				Objects.equals(resourceId, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.resourceId) &&
				Objects.equals(extCps, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.extCps) &&
				Objects.equals(extLinkPorts, vnfInstancesvnfInstanceIdinstantiateExtVirtualLinks.extLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vimConnectionId, resourceProviderId, resourceId, extCps, extLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
