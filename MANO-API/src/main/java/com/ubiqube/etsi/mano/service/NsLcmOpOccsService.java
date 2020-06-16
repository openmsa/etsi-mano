package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.NsLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class NsLcmOpOccsService {
	private final NsLcmOpOccsJpa nsLcmOpOccsJpa;
	private final EntityManager em;

	public NsLcmOpOccsService(final NsLcmOpOccsJpa _nsLcmOpOccsJpa, final EntityManager _em) {
		nsLcmOpOccsJpa = _nsLcmOpOccsJpa;
		em = _em;
	}

	@Nonnull
	public NsLcmOpOccs createLcmOpOccs(final NsdInstance nsInstance, @Nonnull final NsLcmOpType state) {
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, state);
		return nsLcmOpOccsJpa.save(lcmOpOccs);
	}

	public NsLcmOpOccs save(final NsLcmOpOccs lcmOpOccs) {
		return nsLcmOpOccsJpa.save(lcmOpOccs);
	}

	public NsLcmOpOccs get(final UUID id) {
		return nsLcmOpOccsJpa.findById(id).orElseThrow(() -> new NotFoundException("Lcm Op OCCS not found: " + id));
	}

	public List<NsLcmOpOccs> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<NsLcmOpOccs>) sq.getCriteria(filter, NsLcmOpOccs.class)
				.getResultStream().collect(Collectors.toList());
	}

	public NsLcmOpOccs findById(final UUID nsLcmId) {
		return nsLcmOpOccsJpa.findById(nsLcmId).orElseThrow(() -> new NotFoundException("NsLcmOpOccs Not found " + nsLcmId));
	}

}
