package com.ubiqube.etsi.mano.json;

import org.apache.commons.beanutils.Converter;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOperationalStateType;

public class OperationalStateConverter implements Converter {

	@Override
	public Object convert(final Class type, final Object value) {
		if (type.equals(PackageOperationalStateType.class)) {
			return PackageOperationalStateType.fromValue((String) value);
		} else if (type.equals(NsdUsageStateType.class)) {
			return NsdUsageStateType.fromValue((String) value);
		}
		return null;
	}

}
