package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class NsLcmOpOccs implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Enumerated(EnumType.STRING)
	@Field
	@FieldBridge(impl = EnumFieldBridge.class)
	private InstantiationStatusType operationState = null;

	@Field
	private Date stateEnteredTime = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdInstance nsInstance = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private NsdChangeType lcmOperationType = null;

	@Field
	private Date startTime = null;

	@Field
	private Boolean isAutomaticInvocation = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private NsdChangeType operationParams = null;

	@Field
	private Boolean isCancelPending = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private CancelModeTypeEnum cancelMode = null;

	private FailureDetails error = null;

	private String externalProcessId;

	@Embedded
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
