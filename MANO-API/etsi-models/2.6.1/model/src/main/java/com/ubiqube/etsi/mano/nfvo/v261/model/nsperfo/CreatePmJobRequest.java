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
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The VNF creation parameters.
 **/
@ApiModel(description = "The VNF creation parameters.             ")
public class CreatePmJobRequest {

	@ApiModelProperty(required = true)
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
