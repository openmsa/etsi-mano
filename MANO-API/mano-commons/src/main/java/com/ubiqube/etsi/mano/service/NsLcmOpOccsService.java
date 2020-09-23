package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;

public interface NsLcmOpOccsService {

	@Nonnull
	NsLcmOpOccs createLcmOpOccs(final NsdInstance nsInstance, @Nonnull final NsdChangeType state);

	NsLcmOpOccs save(final NsLcmOpOccs lcmOpOccs);

	NsLcmOpOccs get(final UUID id);

	List<NsLcmOpOccs> query(final String filter);

	NsLcmOpOccs findById(final UUID nsLcmId);

}
