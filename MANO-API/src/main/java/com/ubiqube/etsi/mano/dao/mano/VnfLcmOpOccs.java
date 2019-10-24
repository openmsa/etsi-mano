package com.ubiqube.etsi.mano.dao.mano;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.search.annotations.FieldBridge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CancelModeType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInfoModifications;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOccResourceChanges;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

public class VnfLcmOpOccs {
	private final String id = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private final LcmOperationStateType operationState = null;

	private final Date stateEnteredTime = null;

	private final Date startTime = null;

	private final String vnfInstanceId = null;

	private final String grantId = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private final LcmOperationType operation = null;

	private final Boolean isAutomaticInvocation = null;

	private final Boolean isCancelPending = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private final CancelModeType cancelMode = null;

	private final ProblemDetails error = null;

	private final VnfLcmOpOccResourceChanges resourceChanges = null;

	@JsonProperty("changedInfo")
	private final VnfInfoModifications changedInfo = null;

	@JsonProperty("changedExtConnectivity")
	private final List<ExtVirtualLinkInfo> changedExtConnectivity = null;

}
