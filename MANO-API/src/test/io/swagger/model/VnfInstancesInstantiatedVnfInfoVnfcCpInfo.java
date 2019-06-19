package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoVnfcCpInfo {

	private @Valid String id = null;
	private @Valid String cpdId = null;
	private @Valid String vnfExtCpId = null;
	private @Valid VnfInstancesInstantiatedVnfInfoAddresses addresses = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcCpInfo id(String id) {
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
	public VnfInstancesInstantiatedVnfInfoVnfcCpInfo cpdId(String cpdId) {
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
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcCpInfo vnfExtCpId(String vnfExtCpId) {
		this.vnfExtCpId = vnfExtCpId;
		return this;
	}

	@ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
	@JsonProperty("vnfExtCpId")
	public String getVnfExtCpId() {
		return vnfExtCpId;
	}

	public void setVnfExtCpId(String vnfExtCpId) {
		this.vnfExtCpId = vnfExtCpId;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcCpInfo addresses(VnfInstancesInstantiatedVnfInfoAddresses addresses) {
		this.addresses = addresses;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("addresses")
	public VnfInstancesInstantiatedVnfInfoAddresses getAddresses() {
		return addresses;
	}

	public void setAddresses(VnfInstancesInstantiatedVnfInfoAddresses addresses) {
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
		final VnfInstancesInstantiatedVnfInfoVnfcCpInfo vnfInstancesInstantiatedVnfInfoVnfcCpInfo = (VnfInstancesInstantiatedVnfInfoVnfcCpInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoVnfcCpInfo.id) &&
				Objects.equals(cpdId, vnfInstancesInstantiatedVnfInfoVnfcCpInfo.cpdId) &&
				Objects.equals(vnfExtCpId, vnfInstancesInstantiatedVnfInfoVnfcCpInfo.vnfExtCpId) &&
				Objects.equals(addresses, vnfInstancesInstantiatedVnfInfoVnfcCpInfo.addresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpdId, vnfExtCpId, addresses);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoVnfcCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    vnfExtCpId: ").append(toIndentedString(vnfExtCpId)).append("\n");
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
