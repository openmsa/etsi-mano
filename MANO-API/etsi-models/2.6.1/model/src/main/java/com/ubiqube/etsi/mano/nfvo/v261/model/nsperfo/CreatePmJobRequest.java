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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The VNF creation parameters.
 **/
@Schema(description = "The VNF creation parameters.             ")
public class CreatePmJobRequest {

	@Schema(required = true)
	@Valid
	private PmJobsCreatePmJobRequest createPmJobRequest = null;

	/**
	 * Get createPmJobRequest
	 * 
	 * @return createPmJobRequest
	 **/
	@JsonProperty("CreatePmJobRequest")
	@NotNull
	public PmJobsCreatePmJobRequest getCreatePmJobRequest() {
		return createPmJobRequest;
	}

	public void setCreatePmJobRequest(PmJobsCreatePmJobRequest createPmJobRequest) {
		this.createPmJobRequest = createPmJobRequest;
	}

	public CreatePmJobRequest createPmJobRequest(PmJobsCreatePmJobRequest createPmJobRequest) {
		this.createPmJobRequest = createPmJobRequest;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreatePmJobRequest {\n");

		sb.append("    createPmJobRequest: ").append(toIndentedString(createPmJobRequest)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
