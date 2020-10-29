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
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a NS termination request. It shall comply with the
 * provisions defined in Table 6.5.2.15-1.
 */
@ApiModel(description = "This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. ")
@Validated


public class TerminateNsRequest {
	@JsonProperty("terminationTime")
	private OffsetDateTime terminationTime = null;

	public TerminateNsRequest terminationTime(final OffsetDateTime terminationTime) {
		this.terminationTime = terminationTime;
		return this;
	}

	/**
	 * Timestamp indicating the end time of the NS, i.e. the NS will be terminated
	 * automatically at this timestamp. Cardinality \"0\" indicates the NS
	 * termination takes place immediately
	 * 
	 * @return terminationTime
	 **/
	@ApiModelProperty(value = "Timestamp indicating the end time of the NS, i.e. the NS will be terminated automatically at this timestamp. Cardinality \"0\" indicates the NS termination takes place immediately ")

	@Valid

	public OffsetDateTime getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(final OffsetDateTime terminationTime) {
		this.terminationTime = terminationTime;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final TerminateNsRequest terminateNsRequest = (TerminateNsRequest) o;
		return Objects.equals(this.terminationTime, terminateNsRequest.terminationTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(terminationTime);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class TerminateNsRequest {\n");

		sb.append("    terminationTime: ").append(toIndentedString(terminationTime)).append("\n");
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
