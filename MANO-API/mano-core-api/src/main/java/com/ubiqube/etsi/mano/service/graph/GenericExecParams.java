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
package com.ubiqube.etsi.mano.service.graph;

import java.io.Serializable;
import java.util.Map;

public class GenericExecParams implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private Map<String, String> context;

	private String vimResourceId;

	public GenericExecParams(final Map<String, String> context, final String vimResourceId) {
		super();
		this.context = context;
		this.vimResourceId = vimResourceId;
	}

	public final Map<String, String> getContext() {
		return context;
	}

	public final void setContext(final Map<String, String> context) {
		this.context = context;
	}

	public final String getVimResourceId() {
		return vimResourceId;
	}

	public final void setVimResourceId(final String vimResourceId) {
		this.vimResourceId = vimResourceId;
	}

}
