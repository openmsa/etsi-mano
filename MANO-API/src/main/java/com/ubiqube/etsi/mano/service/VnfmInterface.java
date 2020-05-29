package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;

public interface VnfmInterface {

	VnfInstance createVnfInstance(final VnfPackage vnf, String vnfInstanceDescription, String vnfInstanceName);

	VnfLcmOpOccs vnfInstatiate(@Nonnull UUID vnfInstanceId, InstantiateVnfRequest instantiateVnfRequest, UUID vnfId);

	VnfLcmOpOccs getVnfLcmOpOccs(@NotNull UUID id);

	VnfLcmOpOccs vnfTerminate(@Nonnull UUID nsInstanceId, @Nonnull UUID uuid);

}
