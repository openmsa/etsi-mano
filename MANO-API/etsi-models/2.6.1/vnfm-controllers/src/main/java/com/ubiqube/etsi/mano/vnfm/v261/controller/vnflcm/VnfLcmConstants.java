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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfLcmConstants {

	private VnfLcmConstants() {
		// Nothing.
	}

	public static final Set<String> VNFLCMOPOCC_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "operationState", "stateEnteredTime",
			"isAutomaticInvocation", "operationParams", "isCancelPending", "startTime", "vnfInstanceId", "operation"));

	public static final String VNFLCMOPOCC_SEARCH_DEFAULT_EXCLUDE_FIELDS = "error,resourceChanges,changedInfo,changedExtConnectivity";

	public static final Set<String> VNFLCM_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "vnfProvider", "vnfProductName", "vnfSoftwareVersion", "vnfdVersion", "instantiationState", "vnfdId"));

	public static final String VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS = "error,resourceChanges,changedInfo,changedExtConnectivity";

}
