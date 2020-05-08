package com.ubiqube.etsi.mano.service.vim;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public class VimStatus {

	private LcmOperationStateType lcmOperationStateType;

	private FailureDetails problemDetails;

	public LcmOperationStateType getLcmOperationStateType() {
		return lcmOperationStateType;
	}

	public void setLcmOperationStateType(final LcmOperationStateType lcmOperationStateType) {
		this.lcmOperationStateType = lcmOperationStateType;
	}

	public FailureDetails getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(final FailureDetails problemDetails) {
		this.problemDetails = problemDetails;
	}

}
