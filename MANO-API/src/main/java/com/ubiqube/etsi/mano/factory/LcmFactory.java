package com.ubiqube.etsi.mano.factory;

import java.util.Date;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.Link;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfOperationalStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationParamsEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationStateEnum;

public final class LcmFactory {
	private LcmFactory() {
		// Nothing.
	}

	@Nonnull
	public static VnfInstance createVnfInstance(final CreateVnfRequest createVnfRequest) {
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setVnfdId(createVnfRequest.getVnfdId());
		vnfInstance.setVnfPkgId(createVnfRequest.getVnfdId());
		vnfInstance.setVnfInstanceDescription(createVnfRequest.getVnfInstanceDescription());
		vnfInstance.setVnfInstanceName(createVnfRequest.getVnfInstanceName());

		final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
		instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STOPPED);
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
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
	public static NsLcmOpOccsNsLcmOpOcc createNsLcmOpOccsNsLcmOpOcc(final String nsInstanceId, final LcmOperationTypeEnum lcmOperationType) {
		final NsLcmOpOccsNsLcmOpOcc nsLcmOpOccsNsLcmOpOcc = new NsLcmOpOccsNsLcmOpOcc();
		nsLcmOpOccsNsLcmOpOcc.setIsAutomaticInvocation(true);
		nsLcmOpOccsNsLcmOpOcc.setIsCancelPending(false);
		nsLcmOpOccsNsLcmOpOcc.setLcmOperationType(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setNsInstanceId(nsInstanceId);
		nsLcmOpOccsNsLcmOpOcc.setOperationParams(lcmOperationTypeToParameter(lcmOperationType));
		nsLcmOpOccsNsLcmOpOcc.setOperationState(OperationStateEnum.PROCESSING);
		nsLcmOpOccsNsLcmOpOcc.setStartTime(new Date());
		nsLcmOpOccsNsLcmOpOcc.setStateEnteredTime(new Date());
		return nsLcmOpOccsNsLcmOpOcc;
	}

	public static VnfLcmOpOcc createVnfLcmOpOccs(final LcmOperationType operation, final String vnfInstanceId) {
		final VnfLcmOpOcc vnfLcmOpOcc = new VnfLcmOpOcc();
		vnfLcmOpOcc.setOperation(operation);
		vnfLcmOpOcc.setVnfInstanceId(vnfInstanceId);
		vnfLcmOpOcc.setStateEnteredTime(new Date());
		vnfLcmOpOcc.setStartTime(new Date());
		vnfLcmOpOcc.setOperationState(LcmOperationStateType.STARTING);
		return vnfLcmOpOcc;
	}

	public static OperationParamsEnum lcmOperationTypeToParameter(final LcmOperationTypeEnum lcmOperationType) {
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
