package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type provides information regarding a resource zone.
 */
@ApiModel(description = "This type provides information regarding a resource zone. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class ZoneInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("zoneId")
	private String zoneId = null;

	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	public ZoneInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * The identifier of this ZoneInfo instance, for the purpose of referencing it
	 * from other structures in the \"Grant\" structure.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "The identifier of this ZoneInfo instance, for the purpose of referencing it from other structures in the \"Grant\" structure. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ZoneInfo zoneId(final String zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	/**
	 * The identifier of the resource zone, as managed by the resource management
	 * layer (typically, the VIM).
	 * 
	 * @return zoneId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM). ")
	@NotNull

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public ZoneInfo vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the connection to the VIM that manages the resource zone. The
	 * applicable \"VimConnectionInfo\" structure, which is referenced by
	 * vimConnectionId, can be obtained from the \" vimConnectionInfo\" attribute of
	 * the \"VnfInstance\" structure. This attribute shall only be supported and
	 * present when VNF-related Resource Management in direct mode is applicable.
	 * 
	 * @return vimConnectionId
	 **/
	@ApiModelProperty(value = "Identifier of the connection to the VIM that manages the resource zone. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \" vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present when VNF-related Resource Management in direct mode is applicable. ")

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public ZoneInfo resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management the resource zone. This
	 * attribute shall only be supported and present when VNF-related Resource
	 * Management in indirect mode is applicable. The identification scheme is
	 * outside the scope of the present document.
	 * 
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifies the entity responsible for the management the resource zone. This attribute shall only be supported and present when VNF-related Resource Management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ZoneInfo zoneInfo = (ZoneInfo) o;
		return Objects.equals(this.id, zoneInfo.id) &&
				Objects.equals(this.zoneId, zoneInfo.zoneId) &&
				Objects.equals(this.vimConnectionId, zoneInfo.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, zoneInfo.resourceProviderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, zoneId, vimConnectionId, resourceProviderId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ZoneInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
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
