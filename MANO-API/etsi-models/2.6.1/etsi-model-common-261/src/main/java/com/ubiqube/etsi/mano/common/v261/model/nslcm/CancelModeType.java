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
package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Cancellation mode. The NFVO shall not start any new VNF lifecycle management
 * and resource management operation, and shall wait for the ongoing VNF
 * lifecycle management and resource management operations in the underlying
 * system, typically the VNFM and VIM, to finish execution or to time out. After
 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
 * The NFVO shall not start any new VNF lifecycle management and resource
 * management operation, shall cancel the ongoing VNF lifecycle management and
 * resource management operations in the underlying system, typically the VNFM
 * and VIM, and shall wait for the cancellation to finish or to time out. After
 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
 */
public enum CancelModeType {

	GRACEFUL("GRACEFUL"),

	FORCEFUL("FORCEFUL");

	private String value;

	CancelModeType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CancelModeType fromValue(final String text) {
		for (final CancelModeType b : CancelModeType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
