package com.ubiqube.etsi.mano.dao.mano;

import javax.xml.bind.annotation.XmlEnumValue;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ApiTypesEnum {
	@XmlEnumValue("SOL003")
	SOL003(String.valueOf("SOL003")),
	@XmlEnumValue("SOL005")
	SOL005(String.valueOf("SOL005"));

	private final String value;

	ApiTypesEnum(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ApiTypesEnum fromValue(final String v) {
		for (final ApiTypesEnum b : ApiTypesEnum.values()) {
			if (String.valueOf(b.value).equals(v)) {
				return b;
			}
		}
		return null;
	}
}
