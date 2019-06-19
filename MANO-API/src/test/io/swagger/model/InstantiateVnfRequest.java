package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class InstantiateVnfRequest {

	private @Valid String flavourId = null;
	private @Valid String instantiationLevelId = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks>();
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> extManagedVirtualLinks = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks>();
	private @Valid String localizationLanguage = null;
	private @Valid Object additionalParams = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public InstantiateVnfRequest flavourId(String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("flavourId")
	@NotNull
	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(String flavourId) {
		this.flavourId = flavourId;
	}

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public InstantiateVnfRequest instantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	@ApiModelProperty(value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("instantiationLevelId")
	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	/**
	 * Information about external VLs to connect the VNF to.
	 **/
	public InstantiateVnfRequest extVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	@ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")
	@JsonProperty("extVirtualLinks")
	public List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	/**
	 * Information about external VLs to connect the VNF to.
	 **/
	public InstantiateVnfRequest extManagedVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	@ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")
	@JsonProperty("extManagedVirtualLinks")
	public List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	/**
	 * Localization language of the VNF to be instantiated. The value shall comply
	 * with the format defined in IETF RFC 5646.
	 **/
	public InstantiateVnfRequest localizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
		return this;
	}

	@ApiModelProperty(value = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")
	@JsonProperty("localizationLanguage")
	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InstantiateVnfRequest additionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("additionalParams")
	public Object getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InstantiateVnfRequest instantiateVnfRequest = (InstantiateVnfRequest) o;
		return Objects.equals(flavourId, instantiateVnfRequest.flavourId) &&
				Objects.equals(instantiationLevelId, instantiateVnfRequest.instantiationLevelId) &&
				Objects.equals(extVirtualLinks, instantiateVnfRequest.extVirtualLinks) &&
				Objects.equals(extManagedVirtualLinks, instantiateVnfRequest.extManagedVirtualLinks) &&
				Objects.equals(localizationLanguage, instantiateVnfRequest.localizationLanguage) &&
				Objects.equals(additionalParams, instantiateVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, localizationLanguage, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InstantiateVnfRequest {\n");

		sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
		sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
		sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
