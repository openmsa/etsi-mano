package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ComputeUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	private final List<VnfLinkPort> vnfLinkPort;

	private final ComputeTask computeTask;

	public ComputeUow(final ComputeTask _computeTask, final VnfCompute _vnfCompute, final Set<VnfLinkPort> _linkPort) {
		super(_computeTask);
		vnfCompute = _vnfCompute;
		vnfLinkPort = _linkPort.stream().collect(Collectors.toList());
		computeTask = _computeTask;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final List<String> storages = vnfCompute.getStorages().stream().map(context::get).collect(Collectors.toList());
		final List<String> networks = vnfLinkPort.stream()
				.map(VnfLinkPort::getVirtualLink)
				.map(context::get)
				.collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, computeTask.getAlias(), computeTask.getFlavorId(), computeTask.getImageId(), networks, storages, vnfCompute.getBootData());
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

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		vnfLinkPort.stream()
				.map(VnfLinkPort::getVirtualLink)
				.map(cache::get)
				.forEach(x -> g.addEdge(x, this));
	}

}
