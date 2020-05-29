package com.ubiqube.etsi.mano.service;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.NsLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;

@Service
public class NsLcmOpOccsService {
	private final NsLcmOpOccsJpa nsLcmOpOccsJpa;

	public NsLcmOpOccsService(final NsLcmOpOccsJpa _nsLcmOpOccsJpa) {
		nsLcmOpOccsJpa = _nsLcmOpOccsJpa;
	}

	public NsLcmOpOccs createLcmOpOccs(final NsdInstance nsInstance, @Nonnull final NsLcmOpType state) {
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, state);
		return nsLcmOpOccsJpa.save(lcmOpOccs);
	}

}
