package com.ubiqube.etsi.mano.service.graph;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ComputeUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	private final VnfInstantiedCompute vnfInstantiedCompute;

	public ComputeUow(final VnfInstantiedCompute _vnfInstantiedCompute, final VnfCompute _vnfCompute) {
		super(_vnfInstantiedCompute, _vnfCompute.getToscaName());
		vnfInstantiedCompute = _vnfInstantiedCompute;
		vnfCompute = _vnfCompute;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		try {
			Thread.sleep(1000L);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final List<String> storages = vnfCompute.getStorages().stream().map(context::get).collect(Collectors.toList());
		final List<String> networks = vnfCompute.getNetworks().stream().map(context::get).collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, vnfCompute, vnfInstantiedCompute.getFlavorId(), vnfInstantiedCompute.getImageId(), networks, storages);
	}

	@Override
	public UowType getType() {
		return UowType.COMPUTE;
	}

	@Override
	protected String getPrefix() {
		return "compute";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteCompute(vimConnectionInformation, resourceId);
		return null;
	}

}
