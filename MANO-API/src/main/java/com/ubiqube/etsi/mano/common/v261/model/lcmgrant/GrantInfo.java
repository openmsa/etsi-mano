package com.ubiqube.etsi.mano.common.v261.model.lcmgrant;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type contains information about a Compute, storage or network resource
 * whose addition/update/deletion was granted.
 */
@ApiModel(description = "This type contains information about a Compute, storage or network resource whose addition/update/deletion was granted. ")
@Validated
public class GrantInfo {
	@JsonProperty("resourceDefinitionId")
	private String resourceDefinitionId = null;

	@JsonProperty("reservationId")
	private String reservationId = null;

	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("zoneId")
	private String zoneId = null;

	@JsonProperty("resourceGroupId")
	private String resourceGroupId = null;

	public GrantInfo resourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
		return this;
	}

	/**
	 * Identifier of the related \"ResourceDefinition\" structure from the related
	 * \"GrantRequest\" structure.
	 *
	 * @return resourceDefinitionId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the related \"ResourceDefinition\" structure from the related \"GrantRequest\" structure. ")
	@NotNull

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public GrantInfo reservationId(final String reservationId) {
		this.reservationId = reservationId;
		return this;
	}

	/**
	 * The reservation identifier applicable to the VNFC/VirtualLink/VirtualStorage.
	 * It shall be present for new resources when policy is GRANT_RESERVE_MULTI and
	 * an applicable reservation exists; shall not be present otherwise.
	 *
	 * @return reservationId
	 **/
	@ApiModelProperty(value = "The reservation identifier applicable to the VNFC/VirtualLink/VirtualStorage. It shall be present for new resources when policy is GRANT_RESERVE_MULTI and an applicable reservation exists; shall not be present otherwise. ")

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public GrantInfo vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the VIM connection to be used to manage this resource. Shall be
	 * present for new resources, and shall be absent for resources that have
	 * already been allocated. The applicable \"VimConnectionInfo\" structure, which
	 * is referenced by vimConnectionId, can be obtained from the
	 * \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This
	 * attribute shall only be supported when VNF-related Resource Management in
	 * direct mode is applicable.
	 *
	 * @return vimConnectionId
	 **/
	@ApiModelProperty(value = "Identifier of the VIM connection to be used to manage this resource. Shall be present for new resources, and shall be absent for resources that have already been allocated. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported when VNF-related Resource Management in direct mode is applicable. ")

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public GrantInfo resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management of the virtualised
	 * resource. Shall be present for new resources, and shall be absent for
	 * resources that have already been allocated. This attribute shall only be
	 * supported when VNF-related Resource Management in indirect mode is
	 * applicable. The identification scheme is outside the scope of the present
	 * document.
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifies the entity responsible for the management of the virtualised resource. Shall be present for new resources, and shall be absent for resources that have already been allocated. This attribute shall only be supported when VNF-related Resource Management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public GrantInfo zoneId(final String zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	/**
	 * Reference to the identifier of the \"ZoneInfo\" structure in the \"Grant\"
	 * structure defining the resource zone into which this resource is to be
	 * placed. Shall be present for new resources if the zones concept is applicable
	 * to them (typically, Compute resources), and shall be absent for resources
	 * that have already been allocated.
	 *
	 * @return zoneId
	 **/
	@ApiModelProperty(value = "Reference to the identifier of the \"ZoneInfo\" structure in the \"Grant\" structure defining the resource zone into which this resource is to be placed. Shall be present for new resources if the zones concept is applicable to them (typically, Compute resources), and shall be absent for resources that have already been allocated. ")

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public GrantInfo resourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
		return this;
	}

	/**
	 * Identifier of the \"infrastructure resource group\", logical grouping of
	 * virtual resources assigned to a tenant within an Infrastructure Domain, to be
	 * provided when allocating the resource. If the VIM connection referenced by
	 * \"vimConnectionId\" applies to multiple infrastructure resource groups, this
	 * attribute shall be present for new resources. If the VIM connection
	 * referenced by \"vimConnectionId\" applies to a single infrastructure resource
	 * group, this attribute may be present for new resources. This attribute shall
	 * be absent for resources that have already been allocated.
	 *
	 * @return resourceGroupId
	 **/
	@ApiModelProperty(value = "Identifier of the \"infrastructure resource group\", logical grouping of virtual resources assigned to a tenant within an Infrastructure Domain, to be provided when allocating the resource. If the VIM connection referenced by \"vimConnectionId\" applies to multiple infrastructure resource groups, this attribute shall be present for new resources. If the VIM connection referenced by \"vimConnectionId\" applies to a single infrastructure resource group, this attribute may be present for new resources. This attribute shall be absent for resources that have already been allocated. ")

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final GrantInfo grantInfo = (GrantInfo) o;
		return Objects.equals(this.resourceDefinitionId, grantInfo.resourceDefinitionId) &&
				Objects.equals(this.reservationId, grantInfo.reservationId) &&
				Objects.equals(this.vimConnectionId, grantInfo.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, grantInfo.resourceProviderId) &&
				Objects.equals(this.zoneId, grantInfo.zoneId) &&
				Objects.equals(this.resourceGroupId, grantInfo.resourceGroupId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resourceDefinitionId, reservationId, vimConnectionId, resourceProviderId, zoneId, resourceGroupId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantInfo {\n");

		sb.append("    resourceDefinitionId: ").append(toIndentedString(resourceDefinitionId)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
		sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
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
