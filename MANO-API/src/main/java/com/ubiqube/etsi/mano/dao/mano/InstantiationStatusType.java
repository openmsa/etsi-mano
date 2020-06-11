package com.ubiqube.etsi.mano.dao.mano;

public enum InstantiationStatusType {
	NOT_STARTED("NOT_STARTED"),

	STARTED("STARTED"),

	SUCCESS("SUCCESS"),

	COMPLETED("COMPLETED"),

	FAILED_TEMP("FAILED_TEMP"),

	PARTIALLY_COMPLETED("PARTIALLY_COMPLETED"),

	FAILED("FAILED"),

	ROLLING_BACK("ROLLING_BACK"),

	ROLLED_BACK("ROLLED_BACK");

	private String value;

	InstantiationStatusType(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static InstantiationStatusType fromValue(final String text) {
		for (final InstantiationStatusType b : InstantiationStatusType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public String value() {
		return value;
	}

}
