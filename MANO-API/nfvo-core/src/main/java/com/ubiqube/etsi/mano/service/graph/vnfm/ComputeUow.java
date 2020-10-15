package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ComputeUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	private final VnfInstantiatedCompute vnfInstantiedCompute;

	private final List<VnfLinkPort> vnfLinkPort;

	public ComputeUow(final VnfInstantiatedCompute _vnfInstantiedCompute, final VnfCompute _vnfCompute, final Set<VnfLinkPort> _vnfLinkPort) {
		super(_vnfInstantiedCompute, _vnfCompute.getToscaName());
		vnfInstantiedCompute = _vnfInstantiedCompute;
		vnfCompute = _vnfCompute;
		vnfLinkPort = _vnfLinkPort.stream()
				.filter(x -> x.getVirtualBinding().equals(_vnfCompute.getToscaName()))
				.sorted(Comparator.comparingInt(VnfLinkPort::getInterfaceOrder))
				.collect(Collectors.toList());
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final List<String> storages = vnfCompute.getStorages().stream().map(context::get).collect(Collectors.toList());
		final List<String> networks = vnfLinkPort.stream()
				.map(VnfLinkPort::getVirtualLink)
				.map(context::get)
				.collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, vnfInstantiedCompute.getAliasName(), vnfInstantiedCompute.getFlavorId(), vnfInstantiedCompute.getImageId(), networks, storages, vnfCompute.getBootData());
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
