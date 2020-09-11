package com.ubiqube.etsi.mano.dao.mano.v2;

public enum OperationStatusType {
	NOT_STARTED("NOT_STARTED"),

	STARTED("STARTED"),
	STARTING("STARTING"),

	PROCESSING("PROCESSING"),
	SUCCESS("SUCCESS"),

	COMPLETED("COMPLETED"),

	FAILED_TEMP("FAILED_TEMP"),

	PARTIALLY_COMPLETED("PARTIALLY_COMPLETED"),

	FAILED("FAILED"),

	ROLLING_BACK("ROLLING_BACK"),

	ROLLED_BACK("ROLLED_BACK");

	private String value;

	OperationStatusType(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static OperationStatusType fromValue(final String text) {
		for (final OperationStatusType b : OperationStatusType.values()) {
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
