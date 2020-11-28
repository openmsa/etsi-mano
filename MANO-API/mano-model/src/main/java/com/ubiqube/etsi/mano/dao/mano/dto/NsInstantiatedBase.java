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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;

public class NsInstantiatedBase implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private Date startTime;

	private Date endTime;

	private NsdChangeType changeType;

	private InstantiationStatusType changeResult = InstantiationStatusType.NOT_STARTED;

	// Vim or VNFM resourceId.
	private String resourceId;

	private String instantiationLevel;

	private NsLcmOpOccs nsLcmOpOccs;

	private Audit audit;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public NsdChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final NsdChangeType changeType) {
		this.changeType = changeType;
	}

	public InstantiationStatusType getChangeResult() {
		return changeResult;
	}

	public void setChangeResult(final InstantiationStatusType changeResult) {
		this.changeResult = changeResult;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public NsLcmOpOccs getNsLcmOpOccs() {
		return nsLcmOpOccs;
	}

	public void setNsLcmOpOccs(final NsLcmOpOccs nsLcmOpOccs) {
		this.nsLcmOpOccs = nsLcmOpOccs;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
