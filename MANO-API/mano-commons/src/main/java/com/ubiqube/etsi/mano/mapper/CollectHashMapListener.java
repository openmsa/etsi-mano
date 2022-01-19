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
package com.ubiqube.etsi.mano.mapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ubiqube.etsi.mano.exception.GenericException;

public class CollectHashMapListener implements BeanListener {
	private final LinkedList<AttrNode> stack = new LinkedList<>();
	private final List<AttrHolder> attrs = new ArrayList<>();
	private final LinkedList<ClassTypeHolder> obj = new LinkedList<>();

	public CollectHashMapListener(final Class<?> obj) {
		this.obj.push(new ClassTypeHolder(obj, null));
	}

	@Override
	public void addProperty(final Object source) {
		final AttrHolder ah = new AttrHolder();
		ah.setStack((LinkedList<AttrNode>) stack.clone());
		ah.setValue(source);
		if (null != source) {
			attrs.add(ah);
		}
	}

	@Override
	public void startList(final String name) {
		stack.push(new ListAttrNode(name));
	}

	@Override
	public void endList() {
		stack.pop();
	}

	@Override
	public void listElementStart(final int i) {
		stack.push(new IndiceAttrNode(i));
	}

	@Override
	public void complexStart(final String name) {
		final ClassTypeHolder curr = obj.getFirst();
		if (curr.clazz.isAssignableFrom(Map.class)) {
			stack.push(new AttrMapEntryNode(name));
			obj.push(new ClassTypeHolder(curr.param, null));
			return;
		}
		if (curr.clazz.isAssignableFrom(List.class)) {
			stack.push(new NamedAttrNode(name));
			obj.push(new ClassTypeHolder(curr.param, null));
			return;
		}
		final BeanInfo beanInfo = getIntrospector(curr.clazz);
		final PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();
		final PropertyDescriptor props = find(name, propDescs);
		final Class<?> ret = props.getPropertyType();
		ClassTypeHolder newVal = new ClassTypeHolder(ret, null);
		if (ret.isAssignableFrom(Map.class) || ret.isAssignableFrom(List.class)) {
			final Class<?> subClass = extractInnerListType(props);
			newVal = new ClassTypeHolder(ret, subClass);
		}
		obj.push(newVal);
		stack.push(new NamedAttrNode(name));
	}

	private static Class<?> extractInnerListType(final PropertyDescriptor propertyDescriptor) {
		final Method method = propertyDescriptor.getReadMethod();
		final ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		final Type[] type = returnType.getActualTypeArguments();
		return (Class<?>) type[0];
	}

	private static PropertyDescriptor find(final String name, final PropertyDescriptor[] propDescs) {
		for (final PropertyDescriptor propertyDescriptor : propDescs) {
			if (propertyDescriptor.getName().equals(name)) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	private static BeanInfo getIntrospector(final Class<?> clazz) {
		try {
			return Introspector.getBeanInfo(clazz);
		} catch (final IntrospectionException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void complexEnd() {
		stack.pop();
		obj.pop();
	}

	@Override
	public void listElementEnd() {
		stack.pop();
	}

	public List<AttrHolder> getAttrs() {
		return attrs;
	}

	@Override
	public void startMap(final String name) {
		stack.push(new NamedAttrNode(name));
	}

	@Override
	public void mapStartEntry(final String key) {
		stack.push(new AttrMapEntryNode(key));
	}

	@Override
	public void mapEndEntry(final String key) {
		stack.pop();
	}

	@Override
	public void endMap(final String name) {
		stack.pop();
	}

	record ClassTypeHolder(Class<?> clazz, Class<?> param) {
		/**/}
}
