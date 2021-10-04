package com.ubiqube.etsi.mano.em.v261.model.vnfconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents configuration parameters of a VNF instance and its VNFC instances.
 */
@Schema(description = "This type represents configuration parameters of a VNF instance and its VNFC instances. ")
@Validated

public class VnfConfiguration {
	@JsonProperty("vnfConfigurationData")
	private VnfConfigurationData vnfConfigurationData = null;

	@JsonProperty("vnfcConfigurationData")
	@Valid
	private List<VnfcConfigurationData> vnfcConfigurationData = null;

	public VnfConfiguration vnfConfigurationData(final VnfConfigurationData vnfConfigurationData) {
		this.vnfConfigurationData = vnfConfigurationData;
		return this;
	}

	/**
	 * Configuration parameters of the VNF instance.
	 *
	 * @return vnfConfigurationData
	 **/
	@Schema(required = true, description = "Configuration parameters of the VNF instance. ")
	@NotNull

	@Valid

	public VnfConfigurationData getVnfConfigurationData() {
		return vnfConfigurationData;
	}

	public void setVnfConfigurationData(final VnfConfigurationData vnfConfigurationData) {
		this.vnfConfigurationData = vnfConfigurationData;
	}

	public VnfConfiguration vnfcConfigurationData(final List<VnfcConfigurationData> vnfcConfigurationData) {
		this.vnfcConfigurationData = vnfcConfigurationData;
		return this;
	}

	public VnfConfiguration addVnfcConfigurationDataItem(final VnfcConfigurationData vnfcConfigurationDataItem) {
		if (this.vnfcConfigurationData == null) {
			this.vnfcConfigurationData = new ArrayList<>();
		}
		this.vnfcConfigurationData.add(vnfcConfigurationDataItem);
		return this;
	}

	/**
	 * Configuration parameters of the VNFC instances.
	 *
	 * @return vnfcConfigurationData
	 **/
	@Schema(description = "Configuration parameters of the VNFC instances. ")

	@Valid

	public List<VnfcConfigurationData> getVnfcConfigurationData() {
		return vnfcConfigurationData;
	}

	public void setVnfcConfigurationData(final List<VnfcConfigurationData> vnfcConfigurationData) {
		this.vnfcConfigurationData = vnfcConfigurationData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfConfiguration vnfConfiguration = (VnfConfiguration) o;
		return Objects.equals(this.vnfConfigurationData, vnfConfiguration.vnfConfigurationData) &&
				Objects.equals(this.vnfcConfigurationData, vnfConfiguration.vnfcConfigurationData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfConfigurationData, vnfcConfigurationData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfConfiguration {\n");

		sb.append("    vnfConfigurationData: ").append(toIndentedString(vnfConfigurationData)).append("\n");
		sb.append("    vnfcConfigurationData: ").append(toIndentedString(vnfcConfigurationData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
