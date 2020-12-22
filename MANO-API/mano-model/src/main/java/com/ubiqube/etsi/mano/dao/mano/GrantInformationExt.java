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

@Entity
public class GrantInformationExt implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private ResourceTypeEnum type;

	private UUID vduId;

	private String resourceDefinitionId;

	private String reservationId;

	private String vimConnectionId;

	private String resourceProviderId;

	private String zoneId;

	private String resourceGroupId;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public ResourceTypeEnum getType() {
		return type;
	}

	public void setType(final ResourceTypeEnum type) {
		this.type = type;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
		this.vduId = vduId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantInformationExt {\n");
		sb.append("id:" + id);
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
