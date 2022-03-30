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
package com.ubiqube.etsi.mano.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.dto.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * Maybe we should rename this class ;)
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ClassPathConverter {
	private final Map<Class<?>, String> path = new HashMap<>();

	public ClassPathConverter() {
		path.put(NsdPackage.class, "nsd-package");
		path.put(NsLcmOpOccs.class, "nsd-lcm-op-occs");
		path.put(NsdInstance.class, "nsd-instances");

		path.put(VnfPackage.class, "vnf-packages");
		path.put(Subscription.class, "vnf-subscription");
		path.put(VnfLcmOpOccs.class, "vnf-lcm-op-occs");
		path.put(VnfInstance.class, "vnf-instances");

		path.put(PnfDescriptor.class, "pnfd");

		// path.put(AppPkg.class, "appd");
	}

	public Set<Class<?>> getList() {
		return Collections.unmodifiableSet(path.keySet());
	}

	/**
	 *
	 * @param clazz
	 * @return Should be a Path ?
	 */
	public String convert(final Class<?> clazz) {
		final String ret = path.get(clazz);
		if (null == ret) {
			throw new GenericException("Class " + clazz + " doesn't have a path.");
		}
		return ret;
	}
}
