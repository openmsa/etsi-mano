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
/*
 * SOL003 - VNF Lifecycle Management interface
 * SOL003 - VNF Lifecycle Management interface definition  IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to. In case of discrepancies the published ETSI Group Specification takes precedence.  In clause 4.3.2 of ETSI GS NFV-SOL 003 v2.4.1, an attribute-based filtering mechanism is defined. This mechanism is currently not included in the corresponding OpenAPI design for this GS version. Changes to the attribute-based filtering mechanism are being considered in v2.5.1 of this GS for inclusion in the corresponding future ETSI NFV OpenAPI design. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis&list_id=61&product=NFV&resolution=
 *
 * OpenAPI spec version: 1.1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ubiqube.etsi.mano.vnfm.v261.model.nslcm;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfOperationalStateType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents request parameters for the \&quot;Operate VNF\&quot;
 * operation.
 */
@ApiModel(description = "This type represents request parameters for the \"Operate VNF\" operation. ")

public class OperateVnfRequest {
	@JsonProperty("changeStateTo")
	private VnfOperationalStateType changeStateTo = null;

	@JsonProperty("stopType")
	private StopType stopType = null;

	@JsonProperty("gracefulStopTimeout")
	private Integer gracefulStopTimeout = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	public OperateVnfRequest changeStateTo(final VnfOperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
		return this;
	}

	/**
	 * The desired operational state (i.e. started or stopped) to change the VNF to.
	 *
	 * @return changeStateTo
	 **/
	@JsonProperty("changeStateTo")
	@ApiModelProperty(required = true, value = "The desired operational state (i.e. started or stopped) to change the VNF to. ")
	@NotNull
	public VnfOperationalStateType getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(final VnfOperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	public OperateVnfRequest stopType(final StopType stopType) {
		this.stopType = stopType;
		return this;
	}

	/**
	 * It signals whether forceful or graceful stop is requested. The “stopType” and
	 * “gracefulStopTimeout” attributes shall be absent, when the “changeStateTo”
	 * attribute is equal to “STARTED”. The “gracefulStopTimeout” attribute shall be
	 * present, when the “changeStateTo” is equal to “STOPPED” and the “stopType”
	 * attribute is equal to “GRACEFUL”. The “gracefulStopTimeout” attribute shall
	 * be absent, when the “changeStateTo” attribute is equal to “STOPPED” and the
	 * “stopType” attribute is equal to “FORCEFUL”. The request shall be treated as
	 * if the “stopType” attribute was set to ”FORCEFUL”, when the “changeStateTo”
	 * attribute is equal to “STOPPED” and the “stopType” attribute is absent.
	 *
	 * @return stopType
	 **/
	@JsonProperty("stopType")
	@ApiModelProperty(value = "It signals whether forceful or graceful stop is requested. The “stopType” and “gracefulStopTimeout” attributes shall be absent, when the “changeStateTo” attribute is equal to “STARTED”. The “gracefulStopTimeout” attribute shall be present, when the “changeStateTo” is equal to “STOPPED” and the “stopType” attribute is equal to “GRACEFUL”. The “gracefulStopTimeout” attribute shall be absent, when the “changeStateTo” attribute is equal to “STOPPED” and the “stopType” attribute is equal to “FORCEFUL”. The request shall be treated as if the “stopType” attribute was set to ”FORCEFUL”, when the “changeStateTo” attribute is equal to “STOPPED” and the “stopType” attribute is absent. ")
	public StopType getStopType() {
		return stopType;
	}

	public void setStopType(final StopType stopType) {
		this.stopType = stopType;
	}

	public OperateVnfRequest gracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
		return this;
	}

	/**
	 * The time interval (in seconds) to wait for the VNF to be taken out of service
	 * during graceful stop, before stopping the VNF. The “stopType” and
	 * “gracefulStopTimeout” attributes shall be absent, when the “changeStateTo”
	 * attribute is equal to “STARTED”. The “gracefulStopTimeout” attribute shall be
	 * present, when the “changeStateTo” is equal to “STOPPED” and the “stopType”
	 * attribute is equal to “GRACEFUL”. The “gracefulStopTimeout” attribute shall
	 * be absent, when the “changeStateTo” attribute is equal to “STOPPED” and the
	 * “stopType” attribute is equal to “FORCEFUL”. The request shall be treated as
	 * if the “stopType” attribute was set to ”FORCEFUL”, when the “changeStateTo”
	 * attribute is equal to “STOPPED” and the “stopType” attribute is absent.
	 *
	 * @return gracefulStopTimeout
	 **/
	@JsonProperty("gracefulStopTimeout")
	@ApiModelProperty(value = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. The “stopType” and “gracefulStopTimeout” attributes shall be absent, when the “changeStateTo” attribute is equal to “STARTED”. The “gracefulStopTimeout” attribute shall be present, when the “changeStateTo” is equal to “STOPPED” and the “stopType” attribute is equal to “GRACEFUL”. The “gracefulStopTimeout” attribute shall be absent, when the “changeStateTo” attribute is equal to “STOPPED” and the “stopType” attribute is equal to “FORCEFUL”. The request shall be treated as if the “stopType” attribute was set to ”FORCEFUL”, when the “changeStateTo” attribute is equal to “STOPPED” and the “stopType” attribute is absent. ")
	public Integer getGracefulStopTimeout() {
		return gracefulStopTimeout;
	}

	public void setGracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
	}

	public OperateVnfRequest additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters passed by the NFVO as input to the process, specific to
	 * the VNF of which the operation status is changed, as declared in the VNFD as
	 * part of \&quot;OperateVnfOpConfig\&quot;.
	 *
	 * @return additionalParams
	 **/
	@JsonProperty("additionalParams")
	@ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the operation status is changed, as declared in the VNFD as part of \"OperateVnfOpConfig\". ")
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class OperateVnfRequest {\n");

		sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
		sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
		sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
