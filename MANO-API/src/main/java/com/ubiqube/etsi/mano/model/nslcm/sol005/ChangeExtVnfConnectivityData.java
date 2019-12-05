package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type describes the information invoked by the NFVO to change the
 * external VNF connectivity information maintained by the VNFM. The types of
 * changes that this operation supports are: 1) Disconnect the external CPs that
 * are connected to a particular external VL, and connect them to a different
 * external VL. 2) Change the connectivity parameters of the existing external
 * CPs, including changing addresses. NOTE: Depending on the capabilities of the
 * underlying VIM resources, certain changes (e.g. modifying the IP address
 * assignment) might not be supported without deleting the resource and creating
 * another one with the modified configuration. This type shall comply with the
 * provisions defined in Table 6.5.3.33-1.
 */
@ApiModel(description = "This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different external VL. 2) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address assignment) might not be supported without deleting the resource and creating another one with the modified configuration. This type shall comply with the provisions defined in Table 6.5.3.33-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class ChangeExtVnfConnectivityData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("extVirtualLink")
	@Valid
	private List<ExtVirtualLinkData> extVirtualLink = new ArrayList<>();

	@JsonProperty("additionalParams")
	private KeyValuePairs additionalParams = null;

	public ChangeExtVnfConnectivityData vnfInstanceId(final String vnfInstanceId) {
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

	public ChangeExtVnfConnectivityData extVirtualLink(final List<ExtVirtualLinkData> extVirtualLink) {
		this.extVirtualLink = extVirtualLink;
		return this;
	}

	public ChangeExtVnfConnectivityData addExtVirtualLinkItem(final ExtVirtualLinkData extVirtualLinkItem) {
		this.extVirtualLink.add(extVirtualLinkItem);
		return this;
	}

	/**
	 * Information about external VLs to change (e.g. connect the VNF to).
	 * 
	 * @return extVirtualLink
	 **/
	@ApiModelProperty(required = true, value = "Information about external VLs to change (e.g. connect the VNF to). ")
	@NotNull

	@Valid

	public List<ExtVirtualLinkData> getExtVirtualLink() {
		return extVirtualLink;
	}

	public void setExtVirtualLink(final List<ExtVirtualLinkData> extVirtualLink) {
		this.extVirtualLink = extVirtualLink;
	}

	public ChangeExtVnfConnectivityData additionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters passed by the OSS as input to the external connectivity
	 * change process, specific to the VNF instance being changed.
	 * 
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional parameters passed by the OSS as input to the external connectivity change process, specific to the VNF instance being changed. ")

	@Valid

	public KeyValuePairs getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ChangeExtVnfConnectivityData changeExtVnfConnectivityData = (ChangeExtVnfConnectivityData) o;
		return Objects.equals(this.vnfInstanceId, changeExtVnfConnectivityData.vnfInstanceId) &&
				Objects.equals(this.extVirtualLink, changeExtVnfConnectivityData.extVirtualLink) &&
				Objects.equals(this.additionalParams, changeExtVnfConnectivityData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, extVirtualLink, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ChangeExtVnfConnectivityData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    extVirtualLink: ").append(toIndentedString(extVirtualLink)).append("\n");
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
