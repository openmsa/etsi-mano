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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nsperfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdsPostResponse;

@Profile({ "!VNFM" })
public class ThresholdsSol005Api implements ThresholdsSol005 {

	/**
	 * Query thresholds.
	 *
	 * The client can use this method to query information about thresholds.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> thresholdsGet(final String accept, final String filter) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	/**
	 * Create a threshold.
	 *
	 * The POST method can be used by the client to create a threshold. This method
	 * shall follow the provisions specified in the table 7.4.5.3.1-2 for URI query
	 * parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<ThresholdsPostResponse> thresholdsPost(final CreateThresholdRequest createThresholdRequest, final String accept, final String contentType) {
		// : Implement...

		return null;
	}

	/**
	 * Delete a threshold.
	 *
	 * This method allows to delete a threshold.
	 *
	 */
	@Override
	public void thresholdsThresholdIdDelete(final String thresholdId, final String accept) {
		// : Implement...

	}

	/**
	 * Query a single threshold.
	 *
	 * The client can use this method for reading an individual threshold. This
	 * method shall follow the provisions specified in the Tables 7.4.6.3.2-1 and
	 * 7.4.6.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public ResponseEntity<ThresholdsPostResponse> thresholdsThresholdIdGet(final String thresholdId, final String accept) {
		// : Implement...

		return null;
	}

}
