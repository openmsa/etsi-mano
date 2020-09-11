package com.ubiqube.etsi.mano.repository.jpa;

import org.hibernate.search.bridge.builtin.EnumBridge;

public class EnumFieldBridge extends EnumBridge {

	@Override
	public String objectToString(final Object object) {
		if (object instanceof String) {
			return (String) object;
		}
		return super.objectToString(object);
	}

}
