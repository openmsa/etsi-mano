package com.ubiqube.etsi.mano.dao.mano;

import java.util.Date;
import java.util.List;

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
import com.ubiqube.etsi.mano.model.nslcm.sol003.CancelModeType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInfoModifications;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOccResourceChanges;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class VnfLcmOpOccs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private LcmOperationStateType operationState = null;

	@Field
	private Date stateEnteredTime = null;

	@Field
	private Date startTime = null;

	@OneToOne(optional = false)
	private VnfInstance vnfInstance = null;

	@Field
	private String grantId = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private LcmOperationType operation = null;

	@Field
	private Boolean isAutomaticInvocation = null;

	@Field
	private Boolean isCancelPending = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private CancelModeType cancelMode = null;

	private FailureDetails error = null;

	private String externalProcessId;

	@Transient
	private VnfLcmOpOccResourceChanges resourceChanges = null;

	@Transient
	private VnfInfoModifications changedInfo = null;

	@Transient
	private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
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

	public LcmOperationType getOperation() {
		return operation;
	}

	public void setOperation(final LcmOperationType operation) {
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

	public CancelModeType getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(final CancelModeType cancelMode) {
		this.cancelMode = cancelMode;
	}

	public FailureDetails getError() {
		return error;
	}

	public void setError(final FailureDetails error) {
		this.error = error;
	}

	public VnfLcmOpOccResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final VnfLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public VnfInfoModifications getChangedInfo() {
		return changedInfo;
	}

	public void setChangedInfo(final VnfInfoModifications changedInfo) {
		this.changedInfo = changedInfo;
	}

	public List<ExtVirtualLinkInfo> getChangedExtConnectivity() {
		return changedExtConnectivity;
	}

	public void setChangedExtConnectivity(final List<ExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
	}

	public String getExternalProcessId() {
		return externalProcessId;
	}

	public void setExternalProcessId(final String externalProcessId) {
		this.externalProcessId = externalProcessId;
	}

}
