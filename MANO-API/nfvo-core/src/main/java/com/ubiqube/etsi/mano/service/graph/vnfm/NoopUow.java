package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NoopUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NoopUow(final VnfInstantiatedCompute vnfInstantiedCompute) {
		super(vnfInstantiedCompute, RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz0123456789"));
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return null;
	}

	@Override
	public UowType getType() {
		return null;
	}

	@Override
	protected String getPrefix() {
		return "noop";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		return null;
	}

}
