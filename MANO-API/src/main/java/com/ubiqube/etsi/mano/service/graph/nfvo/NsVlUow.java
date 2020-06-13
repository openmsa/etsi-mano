package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVl;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsVlUow extends AbstractNsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NsVlUow(final NsInstantiatedVl _resourceHandleEntity, final String _name) {
		super(_resourceHandleEntity, _name);
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		final VlProtocolData vlProtocolData = new VlProtocolData();
		final L2Data l2 = new L2Data();
		l2.setMtu(1500);
		// l2.setName(name);
		l2.setNetworkType("flat");
		l2.setVlanTransparent(false);
		vlProtocolData.setL2ProtocolData(l2);
		final L3Data l3 = new L3Data();
		// l3.setCidr(cidr);
		l3.setDhcpEnabled(true);
		// l3.setGatewayIp(gatewayIp);
		l3.setIpVersion("ipv4");
		// l3.setL3Name(l3Name);
		return vim.createNetwork(vimConnectionInformation, vlProtocolData, null);
	}

	@Override
	public NsUowType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

}
