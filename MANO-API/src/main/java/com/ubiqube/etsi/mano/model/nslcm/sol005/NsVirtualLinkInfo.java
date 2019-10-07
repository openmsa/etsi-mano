package com.ubiqube.etsi.mano.model.nslcm.sol005;

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
 * This type specifies the information about an NS VL instance. It shall comply
 * with the provisions defined in Table 6.5.3.53-1
 */
@ApiModel(description = "This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class NsVirtualLinkInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("nsVirtualLinkDescId")
	private String nsVirtualLinkDescId = null;

	@JsonProperty("nsVirtualLinkProfileId")
	private String nsVirtualLinkProfileId = null;

	@JsonProperty("resourceHandle")
	@Valid
	private List<ResourceHandle> resourceHandle = null;

	@JsonProperty("linkPort")
	@Valid
	private List<NsLinkPortInfo> linkPort = null;

	public NsVirtualLinkInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the VL instance.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VL instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsVirtualLinkInfo nsVirtualLinkDescId(final String nsVirtualLinkDescId) {
		this.nsVirtualLinkDescId = nsVirtualLinkDescId;
		return this;
	}

	/**
	 * Identifier of the VLD in the NSD.
	 * 
	 * @return nsVirtualLinkDescId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VLD in the NSD. ")
	@NotNull

	public String getNsVirtualLinkDescId() {
		return nsVirtualLinkDescId;
	}

	public void setNsVirtualLinkDescId(final String nsVirtualLinkDescId) {
		this.nsVirtualLinkDescId = nsVirtualLinkDescId;
	}

	public NsVirtualLinkInfo nsVirtualLinkProfileId(final String nsVirtualLinkProfileId) {
		this.nsVirtualLinkProfileId = nsVirtualLinkProfileId;
		return this;
	}

	/**
	 * Identifier of the VL profile in the NSD.
	 * 
	 * @return nsVirtualLinkProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VL profile in the NSD. ")
	@NotNull

	public String getNsVirtualLinkProfileId() {
		return nsVirtualLinkProfileId;
	}

	public void setNsVirtualLinkProfileId(final String nsVirtualLinkProfileId) {
		this.nsVirtualLinkProfileId = nsVirtualLinkProfileId;
	}

	public NsVirtualLinkInfo resourceHandle(final List<ResourceHandle> resourceHandle) {
		this.resourceHandle = resourceHandle;
		return this;
	}

	public NsVirtualLinkInfo addResourceHandleItem(final ResourceHandle resourceHandleItem) {
		if (this.resourceHandle == null) {
			this.resourceHandle = new ArrayList<>();
		}
		this.resourceHandle.add(resourceHandleItem);
		return this;
	}

	/**
	 * Identifier(s) of the virtualised network resource(s) realizing the VL
	 * instance. See note.
	 * 
	 * @return resourceHandle
	 **/
	@ApiModelProperty(value = "Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. ")

	@Valid

	public List<ResourceHandle> getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final List<ResourceHandle> resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	public NsVirtualLinkInfo linkPort(final List<NsLinkPortInfo> linkPort) {
		this.linkPort = linkPort;
		return this;
	}

	public NsVirtualLinkInfo addLinkPortItem(final NsLinkPortInfo linkPortItem) {
		if (this.linkPort == null) {
			this.linkPort = new ArrayList<>();
		}
		this.linkPort.add(linkPortItem);
		return this;
	}

	/**
	 * Link ports of the VL instance. Cardinality of zero indicates that no port has
	 * yet been created for the VL instance.
	 * 
	 * @return linkPort
	 **/
	@ApiModelProperty(value = "Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. ")

	@Valid

	public List<NsLinkPortInfo> getLinkPort() {
		return linkPort;
	}

	public void setLinkPort(final List<NsLinkPortInfo> linkPort) {
		this.linkPort = linkPort;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsVirtualLinkInfo nsVirtualLinkInfo = (NsVirtualLinkInfo) o;
		return Objects.equals(this.id, nsVirtualLinkInfo.id) &&
				Objects.equals(this.nsVirtualLinkDescId, nsVirtualLinkInfo.nsVirtualLinkDescId) &&
				Objects.equals(this.nsVirtualLinkProfileId, nsVirtualLinkInfo.nsVirtualLinkProfileId) &&
				Objects.equals(this.resourceHandle, nsVirtualLinkInfo.resourceHandle) &&
				Objects.equals(this.linkPort, nsVirtualLinkInfo.linkPort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nsVirtualLinkDescId, nsVirtualLinkProfileId, resourceHandle, linkPort);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsVirtualLinkInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nsVirtualLinkDescId: ").append(toIndentedString(nsVirtualLinkDescId)).append("\n");
		sb.append("    nsVirtualLinkProfileId: ").append(toIndentedString(nsVirtualLinkProfileId)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
		sb.append("    linkPort: ").append(toIndentedString(linkPort)).append("\n");
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
