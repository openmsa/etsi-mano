package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ExtVirtualLinkInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ExtVirtualLinkInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("resourceHandle")
	private ResourceHandle resourceHandle = null;

	@JsonProperty("extLinkPorts")
	@Valid
	private List<ExtLinkPortInfo> extLinkPorts = null;

	public ExtVirtualLinkInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the external VL and the related external VL information
	 * instance. The identifier is assigned by the NFV-MANO entity that manages this
	 * VL instance.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the external VL and the related external VL information instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ExtVirtualLinkInfo resourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
		return this;
	}

	/**
	 * Reference to the resource realizing this VL.
	 * 
	 * @return resourceHandle
	 **/
	@ApiModelProperty(required = true, value = "Reference to the resource realizing this VL. ")
	@NotNull

	@Valid

	public ResourceHandle getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	public ExtVirtualLinkInfo extLinkPorts(final List<ExtLinkPortInfo> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
		return this;
	}

	public ExtVirtualLinkInfo addExtLinkPortsItem(final ExtLinkPortInfo extLinkPortsItem) {
		if (this.extLinkPorts == null) {
			this.extLinkPorts = new ArrayList<>();
		}
		this.extLinkPorts.add(extLinkPortsItem);
		return this;
	}

	/**
	 * Link ports of this VL.
	 * 
	 * @return extLinkPorts
	 **/
	@ApiModelProperty(value = "Link ports of this VL. ")

	@Valid

	public List<ExtLinkPortInfo> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(final List<ExtLinkPortInfo> extLinkPorts) {
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
		final ExtVirtualLinkInfo extVirtualLinkInfo = (ExtVirtualLinkInfo) o;
		return Objects.equals(this.id, extVirtualLinkInfo.id) &&
				Objects.equals(this.resourceHandle, extVirtualLinkInfo.resourceHandle) &&
				Objects.equals(this.extLinkPorts, extVirtualLinkInfo.extLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceHandle, extLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtVirtualLinkInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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
