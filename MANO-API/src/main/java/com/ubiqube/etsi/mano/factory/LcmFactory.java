package com.ubiqube.etsi.mano.factory;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceStatus;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.Link;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.OperationParamsEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;

public final class LcmFactory {
	private LcmFactory() {
		// Nothing.
	}

	// XXX Is it to be in LCM ?
	@Nonnull
	public static VnfInstance createVnfInstance(final CreateVnfRequest createVnfRequest, final VnfPackage vnfPkgInfo) {
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setVnfdId(createVnfRequest.getVnfdId());
		vnfInstance.setVnfPkg(vnfPkgInfo);
		vnfInstance.setVnfInstanceDescription(createVnfRequest.getVnfInstanceDescription());
		vnfInstance.setVnfInstanceName(createVnfRequest.getVnfInstanceName());
		vnfInstance.setVnfProductName(vnfPkgInfo.getVnfProductName());
		vnfInstance.setVnfProvider(vnfPkgInfo.getVnfProvider());
		vnfInstance.setVnfSoftwareVersion(vnfPkgInfo.getVnfSoftwareVersion());
		vnfInstance.setVnfdId(vnfPkgInfo.getVnfdId());
		vnfInstance.setVnfdVersion(vnfPkgInfo.getVnfdVersion());

		final VnfInstanceStatus instantiatedVnfInfo = new VnfInstanceStatus();
		instantiatedVnfInfo.setVnfState(OperationalStateType.STOPPED);
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
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
	public static VnfInstanceLinks createVnfInstancesLink(final String hrefSelf, final String hrefChangeExtConn, final String hrefChangeFlavor, final String hrefHeal, final String hrefIndicators, final String hrefInstanciate, final String hrefOperate, final String hrefScale, final String hrefScaleToLevel, final String hrefTerminate) {
		final VnfInstanceLinks vnfInstanceLinks = new VnfInstanceLinks();
		final Link self = new Link();
		self.setHref(hrefSelf);
		vnfInstanceLinks.self(self);

		final Link changeExtConn = new Link();
		changeExtConn.setHref(hrefChangeExtConn);
		vnfInstanceLinks.setChangeExtConn(changeExtConn);

		final Link changeFlavour = new Link();
		changeFlavour.setHref(hrefChangeFlavor);
		vnfInstanceLinks.setChangeFlavour(changeFlavour);

		final Link heal = new Link();
		heal.setHref(hrefHeal);
		vnfInstanceLinks.setHeal(heal);

		final Link indicators = new Link();
		indicators.setHref(hrefIndicators);
		vnfInstanceLinks.setIndicators(indicators);

		final Link instantiate = new Link();
		instantiate.setHref(hrefInstanciate);
		vnfInstanceLinks.setInstantiate(instantiate);

		final Link operate = new Link();
		operate.setHref(hrefOperate);
		vnfInstanceLinks.setOperate(operate);

		final Link scale = new Link();
		scale.setHref(hrefScale);
		vnfInstanceLinks.setScale(scale);

		final Link terminate = new Link();
		terminate.setHref(hrefTerminate);
		vnfInstanceLinks.setTerminate(terminate);

		final Link scaleToLevel = new Link();
		scaleToLevel.setHref(hrefScaleToLevel);
		vnfInstanceLinks.setScaleToLevel(scaleToLevel);
		return vnfInstanceLinks;
	}

	@Nonnull
	public static NsLcmOpOccs createNsLcmOpOcc(final NsdInstance nsInstance, final NsLcmOpType lcmOperationType) {
		final NsLcmOpOccs nsLcmOpOccsNsLcmOpOcc = new NsLcmOpOccs();
		nsLcmOpOccsNsLcmOpOcc.setIsAutomaticInvocation(Boolean.TRUE);
		nsLcmOpOccsNsLcmOpOcc.setIsCancelPending(Boolean.FALSE);
		nsLcmOpOccsNsLcmOpOcc.setLcmOperationType(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setNsInstance(nsInstance);
		nsLcmOpOccsNsLcmOpOcc.setOperationParams(lcmOperationTypeToParameter(lcmOperationType));
		nsLcmOpOccsNsLcmOpOcc.setOperationState(LcmOperationStateType.PROCESSING);
		nsLcmOpOccsNsLcmOpOcc.setStartTime(new Date());
		nsLcmOpOccsNsLcmOpOcc.setStateEnteredTime(new Date());
		return nsLcmOpOccsNsLcmOpOcc;
	}

	@Nonnull
	public static VnfLcmOpOccs createVnfLcmOpOccs(final LcmOperationType operation, final UUID vnfInstanceId) {
		final VnfLcmOpOccs vnfLcmOpOcc = new VnfLcmOpOccs();
		vnfLcmOpOcc.setOperation(operation);
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setId(vnfInstanceId);
		vnfLcmOpOcc.setVnfInstance(vnfInstance);
		vnfLcmOpOcc.setStateEnteredTime(new Date());
		vnfLcmOpOcc.setStartTime(new Date());
		vnfLcmOpOcc.setOperationState(LcmOperationStateType.STARTING);
		vnfLcmOpOcc.setIsAutomaticInvocation(Boolean.FALSE);
		vnfLcmOpOcc.setIsCancelPending(Boolean.FALSE);
		return vnfLcmOpOcc;
	}

	public static OperationParamsEnum lcmOperationTypeToParameter(final NsLcmOpType lcmOperationType) {
		switch (lcmOperationType) {
		case HEAL:
			return OperationParamsEnum.HEAL;
		case INSTANTIATE:
			return OperationParamsEnum.INSTANTIATE;
		case SCALE:
			return OperationParamsEnum.SCALE;
		case TERMINATE:
			return OperationParamsEnum.TERMINATE;
		case UPDATE:
			return OperationParamsEnum.UPDATE;

		default:
			throw new NotFoundException("Unknwon LVM Operation: " + lcmOperationType);
		}
	}

}
