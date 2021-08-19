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
package com.ubiqube.etsi.mano.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToStringUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ToStringUtil.class);

	public static String toString(final Object o) {
		try {
			return toStringInernal(o);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException | NoSuchFieldException | SecurityException e) {
			return e.toString();
		}
	}

	private static String toStringInernal(final Object o) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException {
		final StringBuilder sb = new StringBuilder("class ");
		sb.append(o.getClass().getSimpleName()).append(" {\n");
		final BeanInfo cls = Introspector.getBeanInfo(o.getClass());
		final PropertyDescriptor[] clspd = cls.getPropertyDescriptors();
		for (final PropertyDescriptor pd : clspd) {
			if (ignorable(pd, o)) {
				continue;
			}
			final Method rm = pd.getReadMethod();
			if (rm != null) {
				sb.append("    ").append(pd.getName())
						.append(": ")
						.append(getValue(rm, o))
						.append("\n");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private static boolean ignorable(final PropertyDescriptor pd, final Object o) throws SecurityException {
		final ToStringIgnore[] ann = pd.getReadMethod().getAnnotationsByType(ToStringIgnore.class);
		if (ann.length > 0) {
			return true;
		}
		Field field;
		try {
			field = o.getClass().getDeclaredField(pd.getName());
			if (field.isAnnotationPresent(ToStringIgnore.class)) {
				return true;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			LOG.trace("", e);
		}
		return false;
	}

	private static Object getValue(final Method rm, final Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Object res = rm.invoke(o);
		if (null == res) {
			return "null";
		}
		if (res instanceof final Collection<?> l) {
			if (l.isEmpty()) {
				return "[]";
			}
			final StringBuilder sb = new StringBuilder("[\n    ");
			l.forEach(x -> {
				if (x.getClass().isPrimitive() || (x.getClass() == String.class)) {
					sb.append(toIndentedString(x));
				} else {
					sb.append(toIndentedString(toString(x)));
				}
				sb.append(",");
			});
			sb.append("\n] ");
			return toIndentedString(sb.toString());
		}
		return toIndentedString(res);
	}

	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
