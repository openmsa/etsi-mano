/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ExtVirtualLinkData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The type represents the information that is requested to be changed
 * deployment flavor for an existing VNF instance. It shall comply with the
 * provisions defined in Table 6.5.3.25-1.
 */
@ApiModel(description = "The type represents the information that is requested to be changed deployment flavor for an existing VNF instance. It shall comply with the provisions defined in Table 6.5.3.25-1. ")
@Validated

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
	private Map<String, String> additionalParams = null;

	@JsonProperty("extensions")
	private Map<String, String> extensions = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, String> vnfConfigurableProperties = null;

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
	 * information about internal VLs that are managed by NFVO. The indication of
	 * externally-managed internal VLs is needed in case networks have been
	 * pre-configured for use with certain VNFs, for instance to ensure that these
	 * networks have certain properties such as security or acceleration features,
	 * or to address particular network topologies. The present document assumes
	 * that externally-managed internal VLs are managed by the NFVO and created
	 * towards the VIM.
	 *
	 * @return extManagedVirtualLinks
	 **/
	@ApiModelProperty(value = "information about internal VLs that are managed by NFVO. The indication of externally-managed internal VLs is needed in case networks have been pre-configured for use with certain VNFs, for instance to ensure that these networks have certain properties such as security or acceleration features, or to address particular network topologies. The present document assumes that externally-managed internal VLs are managed by the NFVO and created towards the VIM. ")

	@Valid

	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public ChangeVnfFlavourData additionalParams(final Map<String, String> additionalParams) {
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

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public ChangeVnfFlavourData extensions(final Map<String, String> extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * This attribute provides values for the \"extensions\" input parameter of the
	 * Change VNF Flavour operation defined in ETSI GS NFV-SOL 003.
	 *
	 * @return extensions
	 **/
	@ApiModelProperty(value = "This attribute provides values for the \"extensions\" input parameter of the Change VNF Flavour operation defined in ETSI GS NFV-SOL 003. ")

	@Valid

	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public ChangeVnfFlavourData vnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * This attribute provides values for the \"vnfConfigurableProperties\" input
	 * parameter of the Change VNF Flavour operation defined in ETSI GS NFV SOL 003.
	 *
	 * @return vnfConfigurableProperties
	 **/
	@ApiModelProperty(value = "This attribute provides values for the \"vnfConfigurableProperties\" input parameter of the Change VNF Flavour operation defined in ETSI GS NFV SOL 003. ")

	@Valid

	public Map<String, String> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ChangeVnfFlavourData changeVnfFlavourData = (ChangeVnfFlavourData) o;
		return Objects.equals(this.vnfInstanceId, changeVnfFlavourData.vnfInstanceId) &&
				Objects.equals(this.newFlavourId, changeVnfFlavourData.newFlavourId) &&
				Objects.equals(this.instantiationLevelId, changeVnfFlavourData.instantiationLevelId) &&
				Objects.equals(this.extVirtualLinks, changeVnfFlavourData.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourData.extManagedVirtualLinks) &&
				Objects.equals(this.additionalParams, changeVnfFlavourData.additionalParams) &&
				Objects.equals(this.extensions, changeVnfFlavourData.extensions) &&
				Objects.equals(this.vnfConfigurableProperties, changeVnfFlavourData.vnfConfigurableProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, additionalParams, extensions, vnfConfigurableProperties);
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
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
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
