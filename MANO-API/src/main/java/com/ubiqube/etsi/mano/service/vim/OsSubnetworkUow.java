package com.ubiqube.etsi.mano.service.vim;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;

public class OsSubnetworkUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(OsSubnetworkUow.class);

	private final VlProtocolData vlProtocolData;
	private final IpPool ipAllocationPool;

	private final String parentName;

	public OsSubnetworkUow(final ResourceHandleEntity resourceHandleEntity, final VlProtocolData y, final IpPool _ipAllocationPool, final String _parentName) {
		super(resourceHandleEntity, _ipAllocationPool.getStartIpAddress().replaceAll("\\.", "_"));
		vlProtocolData = y;
		ipAllocationPool = _ipAllocationPool;
		parentName = _parentName;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final OpenStackVim osVim = (OpenStackVim) vim;
		final String netId = context.get(parentName);
		LOG.debug("Parent network: {} = {}", parentName, netId);
		return osVim.createSubnet(vimConnectionInformation, vlProtocolData.getL3ProtocolData(), ipAllocationPool, netId);
	}

	@Override
	public UowType getType() {
		return UowType.VL;
	}

	@Override
	protected String getPrefix() {
		return "os_subnet";
	}

}
