package com.ubiqube.etsi.mano.dao.mano.vnfm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum LcmCoordResultTypeEnum {
	CONTINUE("CONTINUE"),
	ABORT("ABORT"),
	CANCELLED("CANCELLED");

	private String value;

	LcmCoordResultTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LcmCoordResultTypeEnum fromValue(final String text) {
		for (final LcmCoordResultTypeEnum b : LcmCoordResultTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
