package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NoopUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final String name;

	public NoopUow() {
		super(new ResourceHandleEntity());
		name = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz0123456789");
	}

	@Override
	public String getName() {
		return "Noop_operation_" + name;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		//
		return null;
	}

	@Override
	public UowType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
