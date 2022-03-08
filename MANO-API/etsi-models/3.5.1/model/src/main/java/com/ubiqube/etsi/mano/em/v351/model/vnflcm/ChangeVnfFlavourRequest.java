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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents request parameters for the \&quot;Change VNF
 * flavour\&quot; operation. * NOTE: The indication of externally-managed
 * internal VLs is needed in case networks have been pre-configured for use with
 * certain VNFs, for instance to ensure that these networks have certain
 * properties such as security or acceleration features, or to address
 * particular network topologies. The present document assumes that
 * externally-managed internal VLs are managed by the NFVO and created towards
 * the VIM.
 */
@Schema(description = "This type represents request parameters for the \"Change VNF flavour\" operation. * NOTE: The indication of externally-managed internal VLs is needed in case networks have been           pre-configured for use with certain VNFs, for instance to ensure that these networks have certain           properties such as security or acceleration features, or to address particular network topologies.           The present document assumes that externally-managed internal VLs are managed by the NFVO and           created towards the VIM. ")
@Validated

public class ChangeVnfFlavourRequest {
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

	public ChangeVnfFlavourRequest newFlavourId(final String newFlavourId) {
		this.newFlavourId = newFlavourId;
		return this;
	}

	/**
	 * Get newFlavourId
	 *
	 * @return newFlavourId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getNewFlavourId() {
		return newFlavourId;
	}

	public void setNewFlavourId(final String newFlavourId) {
		this.newFlavourId = newFlavourId;
	}

	public ChangeVnfFlavourRequest instantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	/**
	 * Get instantiationLevelId
	 *
	 * @return instantiationLevelId
	 **/
	@Schema(description = "")

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public ChangeVnfFlavourRequest extVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	public ChangeVnfFlavourRequest addExtVirtualLinksItem(final ExtVirtualLinkData extVirtualLinksItem) {
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
	@Schema(description = "Information about external VLs to connect the VNF to. ")
	@Valid
	public List<ExtVirtualLinkData> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public ChangeVnfFlavourRequest extManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	public ChangeVnfFlavourRequest addExtManagedVirtualLinksItem(final ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
		if (this.extManagedVirtualLinks == null) {
			this.extManagedVirtualLinks = new ArrayList<>();
		}
		this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about external VLs to connect the VNF to. See note.
	 *
	 * @return extManagedVirtualLinks
	 **/
	@Schema(description = "Information about external VLs to connect the VNF to. See note. ")
	@Valid
	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public ChangeVnfFlavourRequest additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public ChangeVnfFlavourRequest extensions(final Map<String, String> extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * Get extensions
	 *
	 * @return extensions
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public ChangeVnfFlavourRequest vnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Get vnfConfigurableProperties
	 *
	 * @return vnfConfigurableProperties
	 **/
	@Schema(description = "")

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
		final ChangeVnfFlavourRequest changeVnfFlavourRequest = (ChangeVnfFlavourRequest) o;
		return Objects.equals(this.newFlavourId, changeVnfFlavourRequest.newFlavourId) &&
				Objects.equals(this.instantiationLevelId, changeVnfFlavourRequest.instantiationLevelId) &&
				Objects.equals(this.extVirtualLinks, changeVnfFlavourRequest.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourRequest.extManagedVirtualLinks) &&
				Objects.equals(this.additionalParams, changeVnfFlavourRequest.additionalParams) &&
				Objects.equals(this.extensions, changeVnfFlavourRequest.extensions) &&
				Objects.equals(this.vnfConfigurableProperties, changeVnfFlavourRequest.vnfConfigurableProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, additionalParams, extensions, vnfConfigurableProperties);
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
