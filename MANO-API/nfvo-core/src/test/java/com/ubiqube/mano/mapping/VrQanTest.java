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
package com.ubiqube.mano.mapping;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import com.ubiqube.etsi.mano.dao.mano.vrqan.VrQan;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;

public class VrQanTest {

	private static void compare(final ResourceQuota pr, final VrQan vrqan) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final BeanInfo cls = Introspector.getBeanInfo(VrQan.class);
		final PropertyDescriptor[] clspd = cls.getPropertyDescriptors();

		final BeanInfo clsVim = Introspector.getBeanInfo(ResourceQuota.class);
		final PropertyDescriptor[] clspdVim = clsVim.getPropertyDescriptors();
		for (final PropertyDescriptor pd : clspd) {
			pd.getReadMethod().invoke(vrqan);
			invokeGettter(pd, clspdVim, pr);
		}
	}

	private static Object invokeGettter(final PropertyDescriptor pd, final PropertyDescriptor[] clspdVim, final ResourceQuota pr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final PropertyDescriptor ne = find(pd.getName(), clspdVim);
		return ne.getReadMethod().invoke(pr);
	}

	private static PropertyDescriptor find(final String name, final PropertyDescriptor[] clspdVim) {
		for (final PropertyDescriptor propertyDescriptor : clspdVim) {
			if (propertyDescriptor.getName().equals(name)) {
				return propertyDescriptor;
			}
		}
		throw new GenericException("Could not find property: " + name);
	}
}
