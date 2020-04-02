package com.ubiqube.etsi.mano.factory;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.Link;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.OperationParamsEnum;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOperationStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public final class LcmFactory {
	private LcmFactory() {
		// Nothing.
	}

	@Nonnull
	public static VnfInstance createVnfInstance(final CreateVnfRequest createVnfRequest, final VnfPkgInfo vnfPkgInfo) {
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setVnfdId(createVnfRequest.getVnfdId());
		final VnfPackage vnfPackage = new VnfPackage();
		vnfPackage.setId(UUID.fromString(createVnfRequest.getVnfdId()));
		vnfInstance.setVnfPkg(vnfPackage);
		vnfInstance.setVnfInstanceDescription(createVnfRequest.getVnfInstanceDescription());
		vnfInstance.setVnfInstanceName(createVnfRequest.getVnfInstanceName());
		vnfInstance.setVnfProductName(vnfPkgInfo.getVnfProductName());
		vnfInstance.setVnfProvider(vnfPkgInfo.getVnfProvider());
		vnfInstance.setVnfSoftwareVersion(vnfPkgInfo.getVnfSoftwareVersion());

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
	public static NsLcmOpOcc createNsLcmOpOcc(final String nsInstanceId, final NsLcmOpType lcmOperationType) {
		final NsLcmOpOcc nsLcmOpOccsNsLcmOpOcc = new NsLcmOpOcc();
		nsLcmOpOccsNsLcmOpOcc.setIsAutomaticInvocation(Boolean.TRUE);
		nsLcmOpOccsNsLcmOpOcc.setIsCancelPending(Boolean.FALSE);
		nsLcmOpOccsNsLcmOpOcc.setLcmOperationType(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setNsInstanceId(nsInstanceId);
		nsLcmOpOccsNsLcmOpOcc.setOperationParams(lcmOperationTypeToParameter(lcmOperationType));
		nsLcmOpOccsNsLcmOpOcc.setOperationState(NsLcmOperationStateType.PROCESSING);
		nsLcmOpOccsNsLcmOpOcc.setStartTime(OffsetDateTime.now());
		nsLcmOpOccsNsLcmOpOcc.setStateEnteredTime(OffsetDateTime.now());
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
