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
package com.ubiqube.etsi.mano.service.pkg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPackageProxy implements InvocationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultPackageProxy.class);

	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		final Class<?> retType = method.getReturnType();
		if (retType.isInterface()) {
			return handleInterface(retType);
		}
		LOG.info("{}", retType);
		return null;
	}

	private static Object handleInterface(final Class<?> retType) {
		if (retType.isAssignableFrom(Set.class)) {
			return new HashSet<>();
		}
		throw new ToscaException("Unknow interface type " + retType);
	}

}
