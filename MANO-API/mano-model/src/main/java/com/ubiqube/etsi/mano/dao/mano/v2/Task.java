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

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;

@Entity
@EntityListeners(AuditListener.class)
public class Task implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@Enumerated(EnumType.STRING)
	private PlanStatusType status;

	private String vimResourceId;

	private String vimReservationId;

	@Enumerated(EnumType.STRING)
	private ChangeType changeType;

	private String toscaName;

	private String alias;

	@Enumerated(EnumType.STRING)
	private ResourceTypeEnum type;

	private String zoneId;

	private String resourceGroupId;

	@ManyToOne
	private Blueprint blueprint;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public PlanStatusType getStatus() {
		return status;
	}

	public void setStatus(final PlanStatusType status) {
		this.status = status;
	}

	public String getVimResourceId() {
		return vimResourceId;
	}

	public void setVimResourceId(final String vimResourceId) {
		this.vimResourceId = vimResourceId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType _changeType) {
		changeType = _changeType;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
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

	public Blueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(final Blueprint _blueprint) {
		blueprint = _blueprint;
	}

}
