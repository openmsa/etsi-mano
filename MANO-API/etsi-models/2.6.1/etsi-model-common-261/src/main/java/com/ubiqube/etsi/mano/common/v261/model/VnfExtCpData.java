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
package com.ubiqube.etsi.mano.common.v261.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfExtCpConfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents configuration information for external CPs created from
 * a CPD.
 */
@ApiModel(description = "This type represents configuration information for external CPs created from a CPD. ")
@Validated
public class VnfExtCpData {
	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpConfig")
	@Valid
	private List<VnfExtCpConfig> cpConfig = null;

	public VnfExtCpData cpdId(final String _cpdId) {
		this.cpdId = _cpdId;
		return this;
	}

	/**
	 * The identifier of the CPD in the VNFD.
	 *
	 * @return cpdId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the CPD in the VNFD. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfExtCpData cpConfig(final List<VnfExtCpConfig> _cpConfig) {
		this.cpConfig = _cpConfig;
		return this;
	}

	public VnfExtCpData addCpConfigItem(final VnfExtCpConfig cpConfigItem) {
		if (this.cpConfig == null) {
			this.cpConfig = new ArrayList<>();
		}
		this.cpConfig.add(cpConfigItem);
		return this;
	}

	/**
	 * List of instance data that need to be configured on the CP instances created
	 * from the respective CPD.
	 *
	 * @return cpConfig
	 **/
	@ApiModelProperty(value = "List of instance data that need to be configured on the CP instances created from the respective CPD. ")

	@Valid

	public List<VnfExtCpConfig> getCpConfig() {
		return cpConfig;
	}

	public void setCpConfig(final List<VnfExtCpConfig> cpConfig) {
		this.cpConfig = cpConfig;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfExtCpData vnfExtCpData = (VnfExtCpData) o;
		return Objects.equals(this.cpdId, vnfExtCpData.cpdId) &&
				Objects.equals(this.cpConfig, vnfExtCpData.cpConfig);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpdId, cpConfig);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfExtCpData {\n");

		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
