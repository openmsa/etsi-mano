package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoMonitoringParameters {

	private @Valid String id = null;
	private @Valid String name = null;
	private @Valid Object value = null;
	private @Valid String timeStamp = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesInstantiatedVnfInfoMonitoringParameters id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Human readable name of the monitoring parameter, as defined in the VNFD.
	 **/
	public VnfInstancesInstantiatedVnfInfoMonitoringParameters name(String name) {
		this.name = name;
		return this;
	}

	@ApiModelProperty(value = "Human readable name of the monitoring parameter, as defined in the VNFD. ")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Value of the monitoring parameter known to the VNFM (e.g. obtained for
	 * autoscaling purposes). The type of the \&quot;value\&quot; attribute (i.e.
	 * scalar, structure (Object in JSON), or array (of scalars, arrays or
	 * structures/Objects)) is assumed to be defined in an external measurement
	 * specification outside the scope of the present document.
	 **/
	public VnfInstancesInstantiatedVnfInfoMonitoringParameters value(Object value) {
		this.value = value;
		return this;
	}

	@ApiModelProperty(required = true, value = "Value of the monitoring parameter known to the VNFM (e.g. obtained for autoscaling purposes). The type of the \"value\" attribute (i.e. scalar, structure (Object in JSON), or array (of scalars, arrays or structures/Objects)) is assumed to be defined in an external measurement specification outside the scope of the present document. ")
	@JsonProperty("value")
	@NotNull
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Represents the point in time when the measurement has been performed, as
	 * known to the VNFM. Should be formatted according to ETF RFC 3339.
	 **/
	public VnfInstancesInstantiatedVnfInfoMonitoringParameters timeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	@ApiModelProperty(required = true, value = "Represents the point in time when the measurement has been performed, as known to the VNFM. Should be formatted according to ETF RFC 3339. ")
	@JsonProperty("timeStamp")
	@NotNull
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoMonitoringParameters vnfInstancesInstantiatedVnfInfoMonitoringParameters = (VnfInstancesInstantiatedVnfInfoMonitoringParameters) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoMonitoringParameters.id) &&
				Objects.equals(name, vnfInstancesInstantiatedVnfInfoMonitoringParameters.name) &&
				Objects.equals(value, vnfInstancesInstantiatedVnfInfoMonitoringParameters.value) &&
				Objects.equals(timeStamp, vnfInstancesInstantiatedVnfInfoMonitoringParameters.timeStamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, value, timeStamp);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoMonitoringParameters {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
