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
import com.ubiqube.etsi.mano.em.v351.model.vnfconfig.ProblemDetails;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents an LCM coordination result.
 */
@Schema(description = "This type represents an LCM coordination result. ")
@Validated

public class LcmCoord {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("coordinationResult")
	private LcmCoordResultType coordinationResult = null;

	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfLcmOpOccId")
	private String vnfLcmOpOccId = null;

	@JsonProperty("lcmOperationType")
	private LcmOperationForCoordType lcmOperationType = null;

	@JsonProperty("coordinationActionName")
	private String coordinationActionName = null;

	@JsonProperty("outputParams")
	private Map<String, String> outputParams = null;

	@JsonProperty("warnings")
	private String warnings = null;

	@JsonProperty("error")
	private ProblemDetails error = null;

	@JsonProperty("_links")
	private LcmCoordLinks _links = null;

	public LcmCoord id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public LcmCoord coordinationResult(final LcmCoordResultType coordinationResult) {
		this.coordinationResult = coordinationResult;
		return this;
	}

	/**
	 * Get coordinationResult
	 *
	 * @return coordinationResult
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public LcmCoordResultType getCoordinationResult() {
		return coordinationResult;
	}

	public void setCoordinationResult(final LcmCoordResultType coordinationResult) {
		this.coordinationResult = coordinationResult;
	}

	public LcmCoord vnfInstanceId(final String vnfInstanceId) {
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

	public LcmCoord vnfLcmOpOccId(final String vnfLcmOpOccId) {
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

	public LcmCoord lcmOperationType(final LcmOperationForCoordType lcmOperationType) {
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

	public LcmCoord coordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
		return this;
	}

	/**
	 * Indicates the actual LCM coordination action. The coordination actions that a
	 * VNF supports are declared in the VNFD.
	 *
	 * @return coordinationActionName
	 **/
	@Schema(required = true, description = "Indicates the actual LCM coordination action. The coordination actions that a VNF supports are declared in the VNFD. ")
	@NotNull

	public String getCoordinationActionName() {
		return coordinationActionName;
	}

	public void setCoordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
	}

	public LcmCoord outputParams(final Map<String, String> outputParams) {
		this.outputParams = outputParams;
		return this;
	}

	/**
	 * Get outputParams
	 *
	 * @return outputParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(final Map<String, String> outputParams) {
		this.outputParams = outputParams;
	}

	public LcmCoord warnings(final String warnings) {
		this.warnings = warnings;
		return this;
	}

	/**
	 * Warning messages that were generated while the operation was executing.
	 *
	 * @return warnings
	 **/
	@Schema(description = "Warning messages that were generated while the operation was executing. ")

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(final String warnings) {
		this.warnings = warnings;
	}

	public LcmCoord error(final ProblemDetails error) {
		this.error = error;
		return this;
	}

	/**
	 * Get error
	 *
	 * @return error
	 **/
	@Schema(description = "")

	@Valid
	public ProblemDetails getError() {
		return error;
	}

	public void setError(final ProblemDetails error) {
		this.error = error;
	}

	public LcmCoord _links(final LcmCoordLinks _links) {
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
	public LcmCoordLinks getLinks() {
		return _links;
	}

	public void setLinks(final LcmCoordLinks _links) {
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
		final LcmCoord lcmCoord = (LcmCoord) o;
		return Objects.equals(this.id, lcmCoord.id) &&
				Objects.equals(this.coordinationResult, lcmCoord.coordinationResult) &&
				Objects.equals(this.vnfInstanceId, lcmCoord.vnfInstanceId) &&
				Objects.equals(this.vnfLcmOpOccId, lcmCoord.vnfLcmOpOccId) &&
				Objects.equals(this.lcmOperationType, lcmCoord.lcmOperationType) &&
				Objects.equals(this.coordinationActionName, lcmCoord.coordinationActionName) &&
				Objects.equals(this.outputParams, lcmCoord.outputParams) &&
				Objects.equals(this.warnings, lcmCoord.warnings) &&
				Objects.equals(this.error, lcmCoord.error) &&
				Objects.equals(this._links, lcmCoord._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, coordinationResult, vnfInstanceId, vnfLcmOpOccId, lcmOperationType, coordinationActionName, outputParams, warnings, error, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LcmCoord {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    coordinationResult: ").append(toIndentedString(coordinationResult)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
		sb.append("    lcmOperationType: ").append(toIndentedString(lcmOperationType)).append("\n");
		sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
		sb.append("    outputParams: ").append(toIndentedString(outputParams)).append("\n");
		sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
