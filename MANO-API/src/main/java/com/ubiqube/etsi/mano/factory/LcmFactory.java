package com.ubiqube.etsi.mano.factory;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceStatus;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;

public final class LcmFactory {
	private LcmFactory() {
		// Nothing.
	}

	// XXX Is it to be in LCM ?
	@Nonnull
	public static VnfInstance createVnfInstance(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription, final VnfPackage vnfPkgInfo) {
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setVnfdId(vnfdId);
		vnfInstance.setVnfPkg(vnfPkgInfo);
		vnfInstance.setVnfInstanceDescription(vnfInstanceDescription);
		vnfInstance.setVnfInstanceName(vnfInstanceName);
		vnfInstance.setVnfProductName(vnfPkgInfo.getVnfProductName());
		vnfInstance.setVnfProvider(vnfPkgInfo.getVnfProvider());
		vnfInstance.setVnfSoftwareVersion(vnfPkgInfo.getVnfSoftwareVersion());
		vnfInstance.setVnfdId(vnfPkgInfo.getVnfdId());
		vnfInstance.setVnfdVersion(vnfPkgInfo.getVnfdVersion());

		final VnfInstanceStatus instantiatedVnfInfo = new VnfInstanceStatus();
		instantiatedVnfInfo.setVnfState(OperationalStateType.STOPPED);
		vnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
		final Set<VnfInstanceScaleInfo> scaleInfo = vnfPkgInfo.getVnfCompute().stream()
				.map(x -> x.getScalingAspectDeltas().stream()
						.map(VnfComputeAspectDelta::getAspectName)
						.distinct()
						.collect(Collectors.toList()))
				.flatMap(List::stream)
				.distinct()
				.map(x -> new VnfInstanceScaleInfo(x, Integer.valueOf(0)))
				.collect(Collectors.toSet());
		instantiatedVnfInfo.setScaleStatus(scaleInfo);
		return vnfInstance;
	}

	@Nonnull
	public static NsLcmOpOccs createNsLcmOpOcc(final NsdInstance nsInstance, final NsdChangeType lcmOperationType) {
		final NsLcmOpOccs nsLcmOpOccsNsLcmOpOcc = new NsLcmOpOccs();
		nsLcmOpOccsNsLcmOpOcc.setIsAutomaticInvocation(Boolean.TRUE);
		nsLcmOpOccsNsLcmOpOcc.setIsCancelPending(Boolean.FALSE);
		nsLcmOpOccsNsLcmOpOcc.setLcmOperationType(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setNsInstance(nsInstance);
		nsLcmOpOccsNsLcmOpOcc.setOperationParams(lcmOperationTypeToParameter(lcmOperationType));
		nsLcmOpOccsNsLcmOpOcc.setOperationState(InstantiationStatusType.PROCESSING);
		nsLcmOpOccsNsLcmOpOcc.setStartTime(new Date());
		nsLcmOpOccsNsLcmOpOcc.setStateEnteredTime(new Date());
		return nsLcmOpOccsNsLcmOpOcc;
	}

	@Nonnull
	public static VnfLcmOpOccs createVnfLcmOpOccs(final NsdChangeType operation, final UUID vnfInstanceId) {
		final VnfLcmOpOccs vnfLcmOpOcc = new VnfLcmOpOccs();
		vnfLcmOpOcc.setOperation(operation);
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setId(vnfInstanceId);
		vnfLcmOpOcc.setVnfInstance(vnfInstance);
		vnfLcmOpOcc.setStateEnteredTime(new Date());
		vnfLcmOpOcc.setStartTime(new Date());
		vnfLcmOpOcc.setOperationState(InstantiationStatusType.STARTING);
		vnfLcmOpOcc.setIsAutomaticInvocation(Boolean.FALSE);
		vnfLcmOpOcc.setIsCancelPending(Boolean.FALSE);
		return vnfLcmOpOcc;
	}

	public static NsdChangeType lcmOperationTypeToParameter(final NsdChangeType lcmOperationType) {
		switch (lcmOperationType) {
		case HEAL:
			return NsdChangeType.HEAL;
		case INSTANTIATE:
			return NsdChangeType.INSTANTIATE;
		case SCALE:
			return NsdChangeType.SCALE;
		case TERMINATE:
			return NsdChangeType.TERMINATE;
		case UPDATE:
			return NsdChangeType.UPDATE;

		default:
			throw new NotFoundException("Unknwon LVM Operation: " + lcmOperationType);
		}
	}

}
