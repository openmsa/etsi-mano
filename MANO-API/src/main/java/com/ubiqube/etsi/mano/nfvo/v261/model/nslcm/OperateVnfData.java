package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF instance for which the operational state needs to
 * be changed and the requested new state. It shall comply with the provisions
 * defined in Table 6.5.3.31-1.
 */
@ApiModel(description = "This type represents a VNF instance for which the operational state  needs to be changed and the requested new state. It shall comply with the provisions defined in Table 6.5.3.31-1. ")
@Validated


public class OperateVnfData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("changeStateTo")
	private OperationalStates changeStateTo = null;

	@JsonProperty("stopType")
	private StopType stopType = null;

	@JsonProperty("gracefulStopTimeout")
	private Integer gracefulStopTimeout = null;

	public OperateVnfData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF instance.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance. ")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public OperateVnfData changeStateTo(final OperationalStates changeStateTo) {
		this.changeStateTo = changeStateTo;
		return this;
	}

	/**
	 * The desired operational state (i.e. started or stopped) to change the VNF to.
	 * 
	 * @return changeStateTo
	 **/
	@ApiModelProperty(required = true, value = "The desired operational state (i.e. started or stopped) to change the VNF to. ")
	@NotNull

	@Valid

	public OperationalStates getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(final OperationalStates changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	public OperateVnfData stopType(final StopType stopType) {
		this.stopType = stopType;
		return this;
	}

	/**
	 * It signals whether forceful or graceful stop is requested.
	 * 
	 * @return stopType
	 **/
	@ApiModelProperty(value = "It signals whether forceful or graceful stop is requested.            ")

	@Valid

	public StopType getStopType() {
		return stopType;
	}

	public void setStopType(final StopType stopType) {
		this.stopType = stopType;
	}

	public OperateVnfData gracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
		return this;
	}

	/**
	 * The time interval (in seconds) to wait for the VNF to be taken out of service
	 * during graceful stop, before stopping the VNF.
	 * 
	 * @return gracefulStopTimeout
	 **/
	@ApiModelProperty(value = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. ")

	public Integer getGracefulStopTimeout() {
		return gracefulStopTimeout;
	}

	public void setGracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final OperateVnfData operateVnfData = (OperateVnfData) o;
		return Objects.equals(this.vnfInstanceId, operateVnfData.vnfInstanceId) &&
				Objects.equals(this.changeStateTo, operateVnfData.changeStateTo) &&
				Objects.equals(this.stopType, operateVnfData.stopType) &&
				Objects.equals(this.gracefulStopTimeout, operateVnfData.gracefulStopTimeout);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, changeStateTo, stopType, gracefulStopTimeout);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class OperateVnfData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
		sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
		sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
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
