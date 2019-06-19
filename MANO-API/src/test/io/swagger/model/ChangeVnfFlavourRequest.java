package io.swagger.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents request parameters for the \&quot;Change VNF flavour\&quot; operation.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents request parameters for the \"Change VNF flavour\" operation. ")

public class ChangeVnfFlavourRequest {

	private @Valid String newFlavourId = null;
	private @Valid String instantiationLevelId = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks>();
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> extManagedVirtualLinks = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks>();
	private @Valid Object additionalParams = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public ChangeVnfFlavourRequest newFlavourId(String newFlavourId) {
		this.newFlavourId = newFlavourId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("newFlavourId")
	@NotNull
	public String getNewFlavourId() {
		return newFlavourId;
	}

	public void setNewFlavourId(String newFlavourId) {
		this.newFlavourId = newFlavourId;
	}

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public ChangeVnfFlavourRequest instantiationLevelId(String instantiationLevelId) {
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
	public ChangeVnfFlavourRequest extVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks) {
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
	public ChangeVnfFlavourRequest extManagedVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks> extManagedVirtualLinks) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public ChangeVnfFlavourRequest additionalParams(Object additionalParams) {
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
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ChangeVnfFlavourRequest {\n");

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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
