package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;

public interface VnfmInterface {

	VnfInstance createVnfInstance(final VnfPackage vnf, String vnfInstanceDescription, String vnfInstanceName);

	Blueprint vnfInstatiate(@Nonnull UUID vnfInstanceId, VnfInstantiate instantiateVnfRequest, UUID vnfId);

	Blueprint getVnfLcmOpOccs(@NotNull UUID id);

	Blueprint vnfTerminate(@Nonnull UUID nsInstanceId);

}
