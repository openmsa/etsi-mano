/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
