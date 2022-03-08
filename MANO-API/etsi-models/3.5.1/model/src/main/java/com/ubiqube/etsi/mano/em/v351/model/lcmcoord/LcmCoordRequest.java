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
package com.ubiqube.etsi.mano.em.v351.model.lcmcoord;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * LcmCoordRequest
 */
@Validated

public class LcmCoordRequest {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfLcmOpOccId")
	private String vnfLcmOpOccId = null;

	@JsonProperty("lcmOperationType")
	private LcmOperationForCoordType lcmOperationType = null;

	@JsonProperty("coordinationActionName")
	private String coordinationActionName = null;

	@JsonProperty("inputParams")
	private Map<String, String> inputParams = null;

	@JsonProperty("_links")
	private LcmCoordRequestLinks _links = null;

	public LcmCoordRequest vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public LcmCoordRequest vnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
		return this;
	}

	/**
	 * Get vnfLcmOpOccId
	 *
	 * @return vnfLcmOpOccId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfLcmOpOccId() {
		return vnfLcmOpOccId;
	}

	public void setVnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
	}

	public LcmCoordRequest lcmOperationType(final LcmOperationForCoordType lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
		return this;
	}

	/**
	 * Get lcmOperationType
	 *
	 * @return lcmOperationType
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public LcmOperationForCoordType getLcmOperationType() {
		return lcmOperationType;
	}

	public void setLcmOperationType(final LcmOperationForCoordType lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
	}

	public LcmCoordRequest coordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
		return this;
	}

	/**
	 * Get coordinationActionName
	 *
	 * @return coordinationActionName
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getCoordinationActionName() {
		return coordinationActionName;
	}

	public void setCoordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
	}

	public LcmCoordRequest inputParams(final Map<String, String> inputParams) {
		this.inputParams = inputParams;
		return this;
	}

	/**
	 * Get inputParams
	 *
	 * @return inputParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getInputParams() {
		return inputParams;
	}

	public void setInputParams(final Map<String, String> inputParams) {
		this.inputParams = inputParams;
	}

	public LcmCoordRequest _links(final LcmCoordRequestLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public LcmCoordRequestLinks getLinks() {
		return _links;
	}

	public void setLinks(final LcmCoordRequestLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final LcmCoordRequest lcmCoordRequest = (LcmCoordRequest) o;
		return Objects.equals(this.vnfInstanceId, lcmCoordRequest.vnfInstanceId) &&
				Objects.equals(this.vnfLcmOpOccId, lcmCoordRequest.vnfLcmOpOccId) &&
				Objects.equals(this.lcmOperationType, lcmCoordRequest.lcmOperationType) &&
				Objects.equals(this.coordinationActionName, lcmCoordRequest.coordinationActionName) &&
				Objects.equals(this.inputParams, lcmCoordRequest.inputParams) &&
				Objects.equals(this._links, lcmCoordRequest._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfLcmOpOccId, lcmOperationType, coordinationActionName, inputParams, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LcmCoordRequest {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
		sb.append("    lcmOperationType: ").append(toIndentedString(lcmOperationType)).append("\n");
		sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
		sb.append("    inputParams: ").append(toIndentedString(inputParams)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
