package com.ubiqube.etsi.mano.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class TestUnitOfWork implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final String name;

	public TestUnitOfWork(final String _name) {
		name = _name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "TestUnitOfWork [name=" + name + "]";
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		try {
			Thread.sleep((long) (Math.random() * 5000));
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Ended";
	}

	@Override
	public UowType getType() {
		return UowType.COMPUTE;
	}

	@Override
	public String getToscaName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfInstantiatedBase getResourceHandleEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
