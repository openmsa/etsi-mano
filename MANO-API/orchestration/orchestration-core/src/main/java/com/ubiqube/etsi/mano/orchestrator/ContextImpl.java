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
package com.ubiqube.etsi.mano.orchestrator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.LinkedMultiValueMap;

import com.ubiqube.etsi.mano.orchestrator.exceptions.OrchestrationException;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ContextImpl implements Context {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final Map<Class<? extends Node>, LinkedMultiValueMap<String, String>> context = new ConcurrentHashMap<>();

	@Override
	public <U> void add(final UnitOfWork<U> uaow, final String res) {
		context.computeIfAbsent(uaow.getNode(), x -> new LinkedMultiValueMap<>(new ConcurrentHashMap<>()));
		final LinkedMultiValueMap<String, String> m = context.get(uaow.getNode());
		m.add(uaow.getTask().getName(), res);
	}

	@Override
	public String get(final Class<? extends Node> class1, final String toscaName) {
		final List<String> r = getList(class1, toscaName);
		if (null == r || r.isEmpty()) {
			return null;
		}
		if (r.size() > 1) {
			throw new OrchestrationException("More than one element returned for : " + class1 + "/" + toscaName);
		}
		return r.get(0);
	}

	private List<String> getList(final Class<? extends Node> class1, final String toscaName) {
		final LinkedMultiValueMap<String, String> m = context.get(class1);
		if (null == m) {
			return Collections.emptyList();
		}
		return m.get(toscaName);
	}

	@Override
	public List<String> getParent(final Class<? extends Node> class1, final String toscaName) {
		final List<String> r = getList(class1, toscaName);
		if (null == r) {
			return Collections.emptyList();
		}
		return r;
	}

	@Override
	public void add(final Class<? extends Node> class1, final String key, final String resourceId) {
		context.computeIfAbsent(class1, x -> new LinkedMultiValueMap<>());
		final LinkedMultiValueMap<String, String> m = context.get(class1);
		m.add(key, resourceId);
	}

	@Override
	public String toString() {
		return "ContextImpl [context=" + context + "]";
	}

}
