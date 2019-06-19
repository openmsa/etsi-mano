package com.ubiqube.json.patch;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.jxpath.AbstractFactory;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import org.junit.Test;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class BeanTest {

	@Test
	public void describeTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		// final Map res = BeanUtils.describe(vnfPkgInfo);
		// System.out.println(res);
	}

	@Test
	public void jxPathTest() throws Exception {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		final JXPathContext context = JXPathContext.newContext(vnfPkgInfo);
		context.setFactory(new AddressFactory());
		// context.createPathAndSetValue("checksum/algorithm", "test");

	}

	public class AddressFactory extends AbstractFactory {
		@Override
		public boolean createObject(JXPathContext context, Pointer pointer,
				Object parent, String name, int index) {
			final PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			try {
				final PropertyDescriptor descr = propertyUtilsBean.getPropertyDescriptor(parent, name);
				final Method readMethod = descr.getReadMethod();
				final Object spawnedClass = spawnClass(readMethod.getReturnType());
				BeanUtils.setProperty(parent, name, spawnedClass);
				return true;
			} catch (final Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		private Object spawnClass(Class<?> _returnType) throws InstantiationException, IllegalAccessException {

			return _returnType.newInstance();
		}
	}

}
