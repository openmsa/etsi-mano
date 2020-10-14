package com.ubiqube.etsi.mano.service.vim;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.VimService;

@Service
public class VimServiceImpl implements VimService {

	private final VimConnectionInformationJpa vimConnectionInformationJpa;

	public VimServiceImpl(final VimConnectionInformationJpa _vimConnectionInformationJpa) {
		vimConnectionInformationJpa = _vimConnectionInformationJpa;
	}

	@Override
	public Optional<VimConnectionInformation> findById(final UUID id) {
		return vimConnectionInformationJpa.findById(id);
	}

}
