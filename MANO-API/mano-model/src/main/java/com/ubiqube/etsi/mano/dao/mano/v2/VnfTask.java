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
package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@EntityListeners(AuditListener.class)
public class VnfTask extends AbstractTask {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String vimReservationId;

	@Enumerated(EnumType.STRING)
	private ChangeType changeType;

	@Enumerated(EnumType.STRING)
	private ResourceTypeEnum type;

	private String zoneId;

	private String resourceGroupId;

	@ManyToOne
	private VnfBlueprint blueprint;

	private UUID removedVnfLiveInstance;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ScaleInfo scaleInfo;

	private String resourceProviderId;

	private String vimConnectionId;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public ChangeType getChangeType() {
		return changeType;
	}

	@Override
	public void setChangeType(final ChangeType _changeType) {
		changeType = _changeType;
	}

	public String getVimReservationId() {
		return vimReservationId;
	}

	public void setVimReservationId(final String vimReservationId) {
		this.vimReservationId = vimReservationId;
	}

	public ResourceTypeEnum getType() {
		return type;
	}

	public void setType(final ResourceTypeEnum type) {
		this.type = type;
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

	public VnfBlueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(final VnfBlueprint _blueprint) {
		blueprint = _blueprint;
	}

	public UUID getRemovedVnfLiveInstance() {
		return removedVnfLiveInstance;
	}

	public void setRemovedVnfLiveInstance(final UUID removedVnfLiveInstance) {
		this.removedVnfLiveInstance = removedVnfLiveInstance;
	}

	public ScaleInfo getScaleInfo() {
		return scaleInfo;
	}

	public void setScaleInfo(final ScaleInfo scaleInfo) {
		this.scaleInfo = scaleInfo;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

}
