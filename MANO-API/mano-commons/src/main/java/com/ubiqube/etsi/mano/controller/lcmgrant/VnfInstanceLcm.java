package com.ubiqube.etsi.mano.controller.lcmgrant;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;

/**
 * NFVO+VNFM & VNFM Implementation. TODO: Make terminate Async and this will be
 * generic again.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface VnfInstanceLcm {

	List<VnfInstance> get(final Map<String, String> queryParameters);

	Blueprint get(final UUID id);

	VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription);

	void delete(@Nonnull final UUID vnfInstanceId);

	Blueprint instantiate(@Nonnull final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest);

	Blueprint terminate(@Nonnull final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout);

	Blueprint scaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest);

	Blueprint scale(final UUID uuid, final VnfScaleRequest scaleVnfRequest);

	Blueprint operate(final UUID uuid, final VnfOperateRequest operateVnfRequest);

}
