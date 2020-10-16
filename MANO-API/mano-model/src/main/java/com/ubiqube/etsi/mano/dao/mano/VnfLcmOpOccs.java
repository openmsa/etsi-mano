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

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class VnfLcmOpOccs implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private InstantiationStatusType operationState = null;

	@Field
	private Date stateEnteredTime = null;

	@Field
	private Date startTime = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private VnfInstance vnfInstance = null;

	@Field
	private String grantId = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private NsdChangeType operation = null;

	@Field
	private Boolean isAutomaticInvocation = null;

	@Field
	private Boolean isCancelPending = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private CancelModeTypeEnum cancelMode = null;

	@Embedded
	private FailureDetails error = new FailureDetails();

	private String externalProcessId;

	@Embedded
	private VnfInstantiatedInfo vnfInstantiatedInfo = new VnfInstantiatedInfo();

	@Embedded
	private VnfLcmResourceChanges resourceChanges = new VnfLcmResourceChanges();

	@Embedded
	private OperateChanges operateChanges = new OperateChanges();

	// private VnfInfoModifications changedInfo = null;

	// @Transient
	// private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vnfLcmOpOccs")
	private Set<VnfInstantiatedBase> resourceHandleEntity;

	@Embedded
	private VnfScaleInfo vnfScaleInfo = new VnfScaleInfo();

	@Embedded
	private Audit audit;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstanceId) {
		this.vnfInstance = vnfInstanceId;
	}

	public String getGrantId() {
		return grantId;
	}

	public void setGrantId(final String grantId) {
		this.grantId = grantId;
	}

	public NsdChangeType getOperation() {
		return operation;
	}

	public void setOperation(final NsdChangeType operation) {
		this.operation = operation;
	}

	public Boolean getIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
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

	public VnfLcmResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final VnfLcmResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public String getExternalProcessId() {
		return externalProcessId;
	}

	public void setExternalProcessId(final String externalProcessId) {
		this.externalProcessId = externalProcessId;
	}

	public Set<VnfInstantiatedBase> getResourceHandleEntity() {
		return resourceHandleEntity;
	}

	public void setResourceHandleEntity(final Set<VnfInstantiatedBase> resourceHandleEntity) {
		this.resourceHandleEntity = resourceHandleEntity;
	}

	public VnfInstantiatedInfo getVnfInstantiatedInfo() {
		return vnfInstantiatedInfo;
	}

	public void setVnfInstantiatedInfo(final VnfInstantiatedInfo vnfInstantiatedInfo) {
		this.vnfInstantiatedInfo = vnfInstantiatedInfo;
	}

	public VnfScaleInfo getVnfScaleInfo() {
		return vnfScaleInfo;
	}

	public void setVnfScaleInfo(final VnfScaleInfo _vnfScaleInfo) {
		vnfScaleInfo = _vnfScaleInfo;
	}

	public OperateChanges getOperateChanges() {
		return operateChanges;
	}

	public void setOperateChanges(final OperateChanges operateChanges) {
		this.operateChanges = operateChanges;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
