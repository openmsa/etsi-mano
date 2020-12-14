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

import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;

public class NsLcmOpOccs implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private InstantiationStatusType operationState = null;

	private Date stateEnteredTime = null;

	private NsdInstance nsInstance = null;

	private NsdChangeType lcmOperationType = null;

	private Date startTime = null;

	private Boolean isAutomaticInvocation = null;

	private NsdChangeType operationParams = null;

	private Boolean isCancelPending = null;

	private CancelModeTypeEnum cancelMode = null;

	private FailureDetails error = null;

	private String externalProcessId;

	private NsLcmOpOccsResourceChanges resourceChanges = new NsLcmOpOccsResourceChanges();

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public InstantiationStatusType getOperationState() {
		return operationState;
	}

	public void setOperationState(final InstantiationStatusType operationState) {
		this.operationState = operationState;
	}

	public Date getStateEnteredTime() {
		return stateEnteredTime;
	}

	public void setStateEnteredTime(final Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
	}

	public NsdInstance getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final NsdInstance nsInstanceId) {
		this.nsInstance = nsInstanceId;
	}

	public NsdChangeType getLcmOperationType() {
		return lcmOperationType;
	}

	public void setLcmOperationType(final NsdChangeType lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Boolean getIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public NsdChangeType getOperationParams() {
		return operationParams;
	}

	public void setOperationParams(final NsdChangeType operationParams) {
		this.operationParams = operationParams;
	}

	public Boolean getIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	public CancelModeTypeEnum getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(final CancelModeTypeEnum cancelMode) {
		this.cancelMode = cancelMode;
	}

	public FailureDetails getError() {
		return error;
	}

	public void setError(final FailureDetails error) {
		this.error = error;
	}

	public NsLcmOpOccsResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final NsLcmOpOccsResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public String getExternalProcessId() {
		return externalProcessId;
	}

	public void setExternalProcessId(final String externalProcessId) {
		this.externalProcessId = externalProcessId;
	}

}
