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
 * The type represents the information that is requested to be changed
 * deployment flavor for an existing VNF instance. It shall comply with the
 * provisions defined in Table 6.5.3.25-1.
 */
@ApiModel(description = "The type represents the information that is requested to be changed deployment flavor for an existing VNF instance. It shall comply with the provisions defined in Table 6.5.3.25-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ChangeVnfFlavourData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("newFlavourId")
	private String newFlavourId = null;

	@JsonProperty("instantiationLevelId")
	private String instantiationLevelId = null;

	@JsonProperty("extVirtualLinks")
	@Valid
	private List<ExtVirtualLinkData> extVirtualLinks = null;

	@JsonProperty("extManagedVirtualLinks")
	@Valid
	private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

	@JsonProperty("additionalParams")
	private KeyValuePairs additionalParams = null;

	public ChangeVnfFlavourData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF instance to be modified.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance to be modified. ")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public ChangeVnfFlavourData newFlavourId(final String newFlavourId) {
		this.newFlavourId = newFlavourId;
		return this;
	}

	/**
	 * Identifier of the VNF deployment flavor to be instantiated.
	 * 
	 * @return newFlavourId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavor to be instantiated. ")
	@NotNull

	public String getNewFlavourId() {
		return newFlavourId;
	}

	public void setNewFlavourId(final String newFlavourId) {
		this.newFlavourId = newFlavourId;
	}

	public ChangeVnfFlavourData instantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	/**
	 * Identifier of the instantiation level of the deployment flavor to be
	 * instantiated. If not present, the default instantiation level as declared in
	 * the VNFD is instantiated.
	 * 
	 * @return instantiationLevelId
	 **/
	@ApiModelProperty(value = "Identifier of the instantiation level of the deployment flavor to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. ")

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public ChangeVnfFlavourData extVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	public ChangeVnfFlavourData addExtVirtualLinksItem(final ExtVirtualLinkData extVirtualLinksItem) {
		if (this.extVirtualLinks == null) {
			this.extVirtualLinks = new ArrayList<>();
		}
		this.extVirtualLinks.add(extVirtualLinksItem);
		return this;
	}

	/**
	 * Information about external VLs to connect the VNF to.
	 * 
	 * @return extVirtualLinks
	 **/
	@ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")

	@Valid

	public List<ExtVirtualLinkData> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public ChangeVnfFlavourData extManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	public ChangeVnfFlavourData addExtManagedVirtualLinksItem(final ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
		if (this.extManagedVirtualLinks == null) {
			this.extManagedVirtualLinks = new ArrayList<>();
		}
		this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
		return this;
	}

	/**
	 * information about internal VLs that are managed by NFVO.
	 * 
	 * @return extManagedVirtualLinks
	 **/
	@ApiModelProperty(value = "information about internal VLs that are managed by NFVO. ")

	@Valid

	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public ChangeVnfFlavourData additionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional input parameters for the flavor change process, specific to the
	 * VNF being modified, as declared in the VNFD as part of
	 * \"ChangeVnfFlavourOpConfig\".
	 * 
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional input parameters for the flavor change process, specific to the VNF being modified, as declared in the VNFD as part of \"ChangeVnfFlavourOpConfig\". ")

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
		final ChangeVnfFlavourData changeVnfFlavourData = (ChangeVnfFlavourData) o;
		return Objects.equals(this.vnfInstanceId, changeVnfFlavourData.vnfInstanceId) &&
				Objects.equals(this.newFlavourId, changeVnfFlavourData.newFlavourId) &&
				Objects.equals(this.instantiationLevelId, changeVnfFlavourData.instantiationLevelId) &&
				Objects.equals(this.extVirtualLinks, changeVnfFlavourData.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourData.extManagedVirtualLinks) &&
				Objects.equals(this.additionalParams, changeVnfFlavourData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ChangeVnfFlavourData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    newFlavourId: ").append(toIndentedString(newFlavourId)).append("\n");
		sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
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
