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

import java.util.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;

@MappedSuperclass
public abstract class AbstractBlueprint<U extends Task, V extends Instance> implements Blueprint<U, V>, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private Date startTime;

	@Embedded
	private FailureDetails error;

	@Embedded
	private Audit audit;

	@Enumerated(EnumType.STRING)
	private PlanOperationType operation;

	private Date stateEnteredTime;
	@Enumerated(EnumType.STRING)
	private OperationStatusType operationStatus;

	private boolean automaticInvocation = false;

	private boolean cancelPending = false;

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public abstract void setTasks(final Set<U> tasks);

	@Override
	public PlanOperationType getOperation() {
		return operation;
	}

	public void setOperation(final PlanOperationType operation) {
		this.operation = operation;
	}

	public FailureDetails getError() {
		return error;
	}

	@Override
	public void setError(final FailureDetails error) {
		this.error = error;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getStateEnteredTime() {
		return stateEnteredTime;
	}

	@Override
	public void setStateEnteredTime(final Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
	}

	@Override
	public OperationStatusType getOperationStatus() {
		return operationStatus;
	}

	@Override
	public void setOperationStatus(final OperationStatusType operationStatus) {
		this.operationStatus = operationStatus;
	}

	public boolean isAutomaticInvocation() {
		return automaticInvocation;
	}

	public void setAutomaticInvocation(final boolean automaticInvocation) {
		this.automaticInvocation = automaticInvocation;
	}

	public boolean isCancelPending() {
		return cancelPending;
	}

	public void setCancelPending(final boolean cancelPending) {
		this.cancelPending = cancelPending;
	}

}
