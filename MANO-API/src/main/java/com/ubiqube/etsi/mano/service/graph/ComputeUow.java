package com.ubiqube.etsi.mano.service.graph;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ComputeUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	public ComputeUow(final ResourceHandleEntity resourceHandleEntity, final VnfCompute x) {
		super(resourceHandleEntity);
		vnfCompute = x;
	}

	@Override
	public String getName() {
		return "compute_" + vnfCompute.getToscaName();
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final List<String> storages = vnfCompute.getStorages().stream().map(context::get).collect(Collectors.toList());
		final List<String> networks = vnfCompute.getNetworks().stream().map(context::get).collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, vnfCompute, networks, storages);
	}

	@Override
	public UowType getType() {
		return UowType.COMPUTE;
	}

}
