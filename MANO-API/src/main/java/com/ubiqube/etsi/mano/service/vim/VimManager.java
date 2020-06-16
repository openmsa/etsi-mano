package com.ubiqube.etsi.mano.service.vim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;

@Service
//@DependsOn("liquibase")
public class VimManager {

	private final List<Vim> vims;
	private final VimConnectionInformationJpa vimConnectionInformationJpa;
	private final Map<UUID, Vim> vimAssociation;

	public VimManager(final List<Vim> _vims, final VimConnectionInformationJpa _vimConnectionInformationJpa) {
		vims = _vims;
		vimAssociation = new HashMap<>();
		vimConnectionInformationJpa = _vimConnectionInformationJpa;
		init();
	}

	private void init() {
		vims.forEach(x -> {
			final Set<VimConnectionInformation> vimsId = vimConnectionInformationJpa.findByVimType(x.getType());
			associateVims(vimsId, x);
		});
	}

	private void associateVims(final Set<VimConnectionInformation> vimsIs, final Vim vim) {
		vimsIs.forEach(x -> vimAssociation.put(x.getId(), vim));
	}

	@NotNull
	public Vim getVimById(final UUID id) {
		return Optional.ofNullable(vimAssociation.get(id)).orElseThrow(() -> new NotFoundException("No such Vim: " + id));
	}

	public void rebuildCache() {
		init();
	}

	public VimConnectionInformation findVimById(final UUID id) {
		return vimConnectionInformationJpa.findById(id).orElseThrow(() -> new NotFoundException("No connection Id " + id));
	}

	@Nonnull
	public Set<VimConnectionInformation> getVimByType(final String type) {
		return vimConnectionInformationJpa.findByVimType(type);
	}
}
