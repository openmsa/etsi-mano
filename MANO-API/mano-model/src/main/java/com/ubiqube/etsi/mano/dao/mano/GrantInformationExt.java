/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.dao.mano.grant.SnapshotResourceDefinitionEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
public class GrantInformationExt implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * From ResourceHandle, but in fact we need this in database.
	 *
	 * Identifier of this "ResourceDefinition" structure, unique at least within the
	 * scope of the "GrantRequest" structure.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * From ResourceHandle.
	 *
	 * Type of the resource definition referenced.
	 */
	@Enumerated(EnumType.STRING)
	private ResourceTypeEnum type;

	/**
	 * From ResourceHandle.
	 *
	 * Reference to the related VDU in the VNFD applicable to this resource. Shall
	 * only be present if a VDU is applicable to this resource.
	 */
	private String vduId;

	/**
	 * From ResourceHandle.
	 *
	 * Identifier of the VNFD to which resourceTemplateId and vduId refer. Shall be
	 * present if the operation to be granted changes the current VNF Package. May
	 * be absent otherwise.
	 *
	 * @since 3.3.1
	 */
	private String vnfdId;

	/**
	 * Reference to a secondary resource template (VnfExtCpd) in the VNFD. Shall be
	 * present if type="LINKPORT" and the linkport is shared by two external CP
	 * instances, one exposing a VNFC CP instance (based on a VnfExtCpd referenced
	 * by "resourceTemplateId") and another one exposing a VIP CP instance (based on
	 * a VnfExtCpd referenced by this attribute). Shall be absent otherwise.
	 *
	 * @Since 3.5.1
	 */
	private String secondaryResourceTemplateId;

	/**
	 * From ResourceHandle.
	 *
	 * Reference to the applicable resource template in the VNFD as follows:
	 * <ul>
	 * <li>- If type="VL": VnfVirtualLinkDesc</li>
	 * <li>- If type="COMPUTE": VirtualComputeDesc</li>
	 * <li>- If type="LINKPORT": VnfExtCpd</li>
	 * <li>- If type="STORAGE": VirtualStorageDesc</li>
	 * </ul>
	 */
	private String resourceTemplateId;

	/**
	 * From GrantInfo.
	 *
	 * Identifier of the related "ResourceDefinition" structure from the related
	 * "GrantRequest" structure.
	 *
	 * <b>Note: For grant request resourceDefinitinId is the Id. So when recreating
	 * a GrantInfo the information is correct.</b>
	 */
	private String resourceDefinitionId;

	/**
	 * From ResourceHandle.
	 *
	 * Identifier of the resource in the scope of the VIM or the resource provider.
	 */
	private String resourceId;
	/**
	 * From GrantInfo.
	 *
	 * The reservation identifier applicable to the
	 * VNFC/VirtualLink/VirtualStorage/compute host. It shall be present for new
	 * resources when policy is GRANT_RESERVE and an applicable reservation exists;
	 * shall not be present otherwise.
	 */
	private String reservationId;

	/**
	 * From ResourceHandle & GrantInfo.
	 *
	 * Identifier of the VIM connection to manage the resource. This attribute shall
	 * only be supported and present if VNF-related resource management in direct
	 * mode is applicable. The applicable "VimConnectionInfo" structure, which is
	 * referenced by vimConnectionId, can be obtained from the "vimConnectionInfo"
	 * attribute of the "VnfInstance" structure.
	 */
	private String vimConnectionId;
	/**
	 * From ResourceHandle & GrantInfo.
	 *
	 * Identifier of the entity responsible for the management of the resource. This
	 * attribute shall only be supported and present when VNF-related resource
	 * management in indirect mode is applicable. The identification scheme is
	 * outside the scope of the present document.
	 */
	private String resourceProviderId;

	/**
	 * From ResourceHandle.
	 *
	 * Type of the resource in the scope of the VIM or the resource provider. See
	 * note.
	 */
	private String vimLevelResourceType;

	/**
	 * From GrantInfo.
	 *
	 * Reference to the identifier of the "ZoneInfo" structure in the "Grant"
	 * structure defining the resource zone into which this resource is to be
	 * placed. Shall be present for new resources if the zones concept is applicable
	 * to them (typically, Compute resources) and shall be absent for resources that
	 * have already been allocated.
	 */
	private String zoneId;

	/**
	 * From GrantInfo.
	 *
	 * Identifier of the "infrastructure resource group", logical grouping of
	 * virtual resources assigned to a tenant within an Infrastructure Domain, to be
	 * provided when allocating the resource.
	 *
	 * If the VIM connection referenced by "vimConnectionId" applies to multiple
	 * infrastructure resource groups, this attribute shall be present for new
	 * resources.
	 *
	 * If the VIM connection referenced by "vimConnectionId" applies to a single
	 * infrastructure resource group, this attribute may be present for new
	 * resources.
	 *
	 * This attribute shall be absent for resources that have already been
	 * allocated.
	 */
	private String resourceGroupId;
	/**
	 * From ResourceHandle.
	 *
	 * Information to identify a snapshot resource. Shall only be present if the
	 * operation to be granted concerns to creating a VNF snapshot from the VNF or
	 * to reverting the VNF to a VNF snapshot.
	 *
	 * @since 3.3.1
	 */
	@OneToOne
	private SnapshotResourceDefinitionEntity snapshotResDef;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantInformationExt {\n");
		sb.append("    id:" + id).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
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
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
