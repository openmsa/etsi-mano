package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents configuration information for external CPs created from a CPD.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents configuration information for external CPs created from a CPD. ")

public class VnfInstancesvnfInstanceIdinstantiateExtCps {

	private @Valid String cpdId = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateCpConfig> cpConfig = new ArrayList<VnfInstancesvnfInstanceIdinstantiateCpConfig>();

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtCps cpdId(String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("cpdId")
	@NotNull
	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(String cpdId) {
		this.cpdId = cpdId;
	}

	/**
	 * List of instance data that need to be configured on the CP instances created
	 * from the respective CPD.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtCps cpConfig(List<VnfInstancesvnfInstanceIdinstantiateCpConfig> cpConfig) {
		this.cpConfig = cpConfig;
		return this;
	}

	@ApiModelProperty(value = "List of instance data that need to be configured on the CP instances created from the respective CPD. ")
	@JsonProperty("cpConfig")
	public List<VnfInstancesvnfInstanceIdinstantiateCpConfig> getCpConfig() {
		return cpConfig;
	}

	public void setCpConfig(List<VnfInstancesvnfInstanceIdinstantiateCpConfig> cpConfig) {
		this.cpConfig = cpConfig;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateExtCps vnfInstancesvnfInstanceIdinstantiateExtCps = (VnfInstancesvnfInstanceIdinstantiateExtCps) o;
		return Objects.equals(cpdId, vnfInstancesvnfInstanceIdinstantiateExtCps.cpdId) &&
				Objects.equals(cpConfig, vnfInstancesvnfInstanceIdinstantiateExtCps.cpConfig);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpdId, cpConfig);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateExtCps {\n");

		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
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
