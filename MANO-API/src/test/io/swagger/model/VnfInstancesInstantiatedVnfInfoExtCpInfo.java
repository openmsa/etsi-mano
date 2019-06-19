package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoExtCpInfo {

	private @Valid String id = null;
	private @Valid String cpdId = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoAddresses> addresses = new ArrayList<VnfInstancesInstantiatedVnfInfoAddresses>();

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtCpInfo id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtCpInfo cpdId(String cpdId) {
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
	 * List of network addresses that have been configured (statically or
	 * dynamically) on the CP.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtCpInfo addresses(List<VnfInstancesInstantiatedVnfInfoAddresses> addresses) {
		this.addresses = addresses;
		return this;
	}

	@ApiModelProperty(value = "List of network addresses that have been configured (statically or dynamically) on the CP. ")
	@JsonProperty("addresses")
	public List<VnfInstancesInstantiatedVnfInfoAddresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<VnfInstancesInstantiatedVnfInfoAddresses> addresses) {
		this.addresses = addresses;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoExtCpInfo vnfInstancesInstantiatedVnfInfoExtCpInfo = (VnfInstancesInstantiatedVnfInfoExtCpInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoExtCpInfo.id) &&
				Objects.equals(cpdId, vnfInstancesInstantiatedVnfInfoExtCpInfo.cpdId) &&
				Objects.equals(addresses, vnfInstancesInstantiatedVnfInfoExtCpInfo.addresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpdId, addresses);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoExtCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
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
