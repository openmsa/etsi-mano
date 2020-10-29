/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration GrantedLcmOperationType defines the permitted values to
 * represent VNF lifecycle operation types in grant requests. Value |
 * Description ------|------------ INSTANTIATE | Represents the \"Instantiate
 * VNF\" LCM operation. SCALE | Represents the \"Scale VNF\" LCM operation.
 * SCALE_TO_LEVEL | Represents the \"Scale VNF to Level\" LCM operation.
 * CHANGE_FLAVOUR | Represents the \"Change VNF Flavour\" LCM operation.
 * TERMINATE | Represents the \"Terminate VNF\" LCM operation. HEAL | Represents
 * the \"Heal VNF\" LCM operation. OPERATE | Represents the \"Operate VNF\" LCM
 * operation. CHANGE_EXT_CONN | Represents the \"Change external VNF
 * connectivity\" LCM operation.
 */
public enum GrantedLcmOperationType {

	INSTANTIATE("INSTANTIATE"),

	SCALE("SCALE"),

	SCALE_TO_LEVEL("SCALE_TO_LEVEL"),

	CHANGE_FLAVOUR("CHANGE_FLAVOUR"),

	TERMINATE("TERMINATE"),

	HEAL("HEAL"),

	OPERATE("OPERATE"),

	CHANGE_EXT_CONN("CHANGE_EXT_CONN");

	private String value;

	GrantedLcmOperationType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static GrantedLcmOperationType fromValue(final String text) {
		for (final GrantedLcmOperationType b : GrantedLcmOperationType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
