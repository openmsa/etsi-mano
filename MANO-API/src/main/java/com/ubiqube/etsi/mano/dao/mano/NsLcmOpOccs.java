package com.ubiqube.etsi.mano.dao.mano;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.CancelModeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationParamsEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccResourceChanges;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class NsLcmOpOccs implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Enumerated(EnumType.STRING)
	@Field
	@FieldBridge(impl = EnumFieldBridge.class)
	private LcmOperationStateType operationState = null;

	@Field
	private Date stateEnteredTime = null;

	@OneToOne(optional = false)
	private NsdInstance nsInstance = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private LcmOperationTypeEnum lcmOperationType = null;

	@Field
	private Date startTime = null;

	@Field
	private Boolean isAutomaticInvocation = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private OperationParamsEnum operationParams = null;

	@Field
	private Boolean isCancelPending = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private CancelModeEnum cancelMode = null;

	private FailureDetails error = null;

	private String externalProcessId;

	@Transient
	private NsLcmOpOccsNsLcmOpOccResourceChanges resourceChanges = null;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public LcmOperationStateType getOperationState() {
		return operationState;
	}

	public void setOperationState(final LcmOperationStateType operationState) {
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

	public LcmOperationTypeEnum getLcmOperationType() {
		return lcmOperationType;
	}

	public void setLcmOperationType(final LcmOperationTypeEnum lcmOperationType) {
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

	public OperationParamsEnum getOperationParams() {
		return operationParams;
	}

	public void setOperationParams(final OperationParamsEnum operationParams) {
		this.operationParams = operationParams;
	}

	public Boolean getIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	public CancelModeEnum getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(final CancelModeEnum cancelMode) {
		this.cancelMode = cancelMode;
	}

	public FailureDetails getError() {
		return error;
	}

	public void setError(final FailureDetails error) {
		this.error = error;
	}

	public NsLcmOpOccsNsLcmOpOccResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final NsLcmOpOccsNsLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public String getExternalProcessId() {
		return externalProcessId;
	}

	public void setExternalProcessId(final String externalProcessId) {
		this.externalProcessId = externalProcessId;
	}

}
