package com.ubiqube.etsi.mano.service.vim;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public class VimStatus {

	private LcmOperationStateType lcmOperationStateType;

	private ProblemDetails problemDetails;

	public LcmOperationStateType getLcmOperationStateType() {
		return lcmOperationStateType;
	}

	public void setLcmOperationStateType(final LcmOperationStateType lcmOperationStateType) {
		this.lcmOperationStateType = lcmOperationStateType;
	}

	public ProblemDetails getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(final ProblemDetails problemDetails) {
		this.problemDetails = problemDetails;
	}

}
