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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtVirtualLinkData;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the information related to a SAP of a NS. The
 * InstantiateVnfData data type specifies the parameters that are needed for VNF
 * instantiation. This information element is used for the bottom-up NS creation
 * when the OSS/BSS explicitly requests VNF instantiation for a given NS. When
 * the NFVO invokes the Instantiate VNF update operation, a set of these
 * parameters are then passed by the NFVO to the VNFM. NOTE 1: It is possible to
 * have several ExtManagedVirtualLinkData for the same VNF internal VL in case
 * of a multi-site VNF spanning several VIMs. The set of
 * ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall
 * indicate so by referencing to the same VnfVirtualLinkDesc and
 * externally-managed multi-site VL instance (refer to clause 6.5.3.27). NOTE 2:
 * If vnfdId and vnfFlavourId (and vnfInstantiationLevelId, if provided) are
 * present, there should be only one vnfProfile that matches the vnfdId and
 * vnfFlavourId (and vnfInstantiationLevelId, if present) in the NS deployment
 * flavour specified in the NSD associated to the NS instance to which the
 * present operation is triggered. In the case there is more than one matching
 * vnfProfile, the NFVO may select a matching vnfProfile based on other
 * information, such as external VL. NOTE 3: Either the attribute triple
 * \&quot;vnfdId, vnfFlavourId and vnfInstantiationLevelId (if provided)\&quot;
 * or the attribute \&quot;vnProfileId\&quot; shall be present, but not both.
 */
@Schema(description = "This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. NOTE 1: It is possible to have several ExtManagedVirtualLinkData for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 6.5.3.27). NOTE 2: If vnfdId and vnfFlavourId (and vnfInstantiationLevelId, if provided) are present, there should be only one vnfProfile that matches the vnfdId and vnfFlavourId (and vnfInstantiationLevelId, if present) in the NS deployment flavour specified in the NSD associated to the NS instance to which the present operation is triggered. In the case there is more than one matching vnfProfile, the NFVO may select a matching vnfProfile based on other information, such as external VL. NOTE 3: Either the attribute triple \"vnfdId, vnfFlavourId and vnfInstantiationLevelId (if provided)\" or the attribute \"vnProfileId\" shall be present, but not both. ")
@Validated

public class InstantiateVnfData {
	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfFlavourId")
	private String vnfFlavourId = null;

	@JsonProperty("vnfInstantiationLevelId")
	private String vnfInstantiationLevelId = null;

	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

	@JsonProperty("extVirtualLinks")
	@Valid
	private List<ExtVirtualLinkData> extVirtualLinks = null;

	@JsonProperty("extManagedVirtualLinks")
	@Valid
	private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

	@JsonProperty("localizationLanguage")
	private String localizationLanguage = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, String> vnfConfigurableProperties = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	@JsonProperty("extensions")
	private Map<String, String> extensions = null;

	@JsonProperty("locationConstraints")
	private VnfLocationConstraint locationConstraints = null;

	public InstantiateVnfData vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@Schema(description = "")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public InstantiateVnfData vnfFlavourId(final String vnfFlavourId) {
		this.vnfFlavourId = vnfFlavourId;
		return this;
	}

	/**
	 * Get vnfFlavourId
	 *
	 * @return vnfFlavourId
	 **/
	@Schema(description = "")

	public String getVnfFlavourId() {
		return vnfFlavourId;
	}

	public void setVnfFlavourId(final String vnfFlavourId) {
		this.vnfFlavourId = vnfFlavourId;
	}

	public InstantiateVnfData vnfInstantiationLevelId(final String vnfInstantiationLevelId) {
		this.vnfInstantiationLevelId = vnfInstantiationLevelId;
		return this;
	}

	/**
	 * Get vnfInstantiationLevelId
	 *
	 * @return vnfInstantiationLevelId
	 **/
	@Schema(description = "")

	public String getVnfInstantiationLevelId() {
		return vnfInstantiationLevelId;
	}

	public void setVnfInstantiationLevelId(final String vnfInstantiationLevelId) {
		this.vnfInstantiationLevelId = vnfInstantiationLevelId;
	}

	public InstantiateVnfData vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Get vnfProfileId
	 *
	 * @return vnfProfileId
	 **/
	@Schema(description = "")

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public InstantiateVnfData vnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	/**
	 * Human-readable name of the VNF instance to be created.
	 *
	 * @return vnfInstanceName
	 **/
	@Schema(description = "Human-readable name of the VNF instance to be created. ")

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public InstantiateVnfData vnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	/**
	 * Human-readable description of the VNF instance to be created.
	 *
	 * @return vnfInstanceDescription
	 **/
	@Schema(description = "Human-readable description of the VNF instance to be created. ")

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public InstantiateVnfData extVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	public InstantiateVnfData addExtVirtualLinksItem(final ExtVirtualLinkData extVirtualLinksItem) {
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

	public InstantiateVnfData extManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	public InstantiateVnfData addExtManagedVirtualLinksItem(final ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
		if (this.extManagedVirtualLinks == null) {
			this.extManagedVirtualLinks = new ArrayList<>();
		}
		this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about internal VLs that are managed by other entities than the
	 * VNFM. See note 1.
	 *
	 * @return extManagedVirtualLinks
	 **/
	@Schema(description = "Information about internal VLs that are managed by other entities than the VNFM. See note 1. ")
	@Valid
	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public InstantiateVnfData localizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
		return this;
	}

	/**
	 * Localization language of the VNF to be instantiated. The value shall comply
	 * with the format defined in IETF RFC 5646.
	 *
	 * @return localizationLanguage
	 **/
	@Schema(description = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")

	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	public InstantiateVnfData vnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
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

	public InstantiateVnfData additionalParams(final Map<String, String> additionalParams) {
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

	public InstantiateVnfData metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public InstantiateVnfData extensions(final Map<String, String> extensions) {
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

	public InstantiateVnfData locationConstraints(final VnfLocationConstraint locationConstraints) {
		this.locationConstraints = locationConstraints;
		return this;
	}

	/**
	 * Get locationConstraints
	 *
	 * @return locationConstraints
	 **/
	@Schema(description = "")

	@Valid
	public VnfLocationConstraint getLocationConstraints() {
		return locationConstraints;
	}

	public void setLocationConstraints(final VnfLocationConstraint locationConstraints) {
		this.locationConstraints = locationConstraints;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final InstantiateVnfData instantiateVnfData = (InstantiateVnfData) o;
		return Objects.equals(this.vnfdId, instantiateVnfData.vnfdId) &&
				Objects.equals(this.vnfFlavourId, instantiateVnfData.vnfFlavourId) &&
				Objects.equals(this.vnfInstantiationLevelId, instantiateVnfData.vnfInstantiationLevelId) &&
				Objects.equals(this.vnfProfileId, instantiateVnfData.vnfProfileId) &&
				Objects.equals(this.vnfInstanceName, instantiateVnfData.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, instantiateVnfData.vnfInstanceDescription) &&
				Objects.equals(this.extVirtualLinks, instantiateVnfData.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, instantiateVnfData.extManagedVirtualLinks) &&
				Objects.equals(this.localizationLanguage, instantiateVnfData.localizationLanguage) &&
				Objects.equals(this.vnfConfigurableProperties, instantiateVnfData.vnfConfigurableProperties) &&
				Objects.equals(this.additionalParams, instantiateVnfData.additionalParams) &&
				Objects.equals(this.metadata, instantiateVnfData.metadata) &&
				Objects.equals(this.extensions, instantiateVnfData.extensions) &&
				Objects.equals(this.locationConstraints, instantiateVnfData.locationConstraints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdId, vnfFlavourId, vnfInstantiationLevelId, vnfProfileId, vnfInstanceName, vnfInstanceDescription, extVirtualLinks, extManagedVirtualLinks, localizationLanguage, vnfConfigurableProperties, additionalParams, metadata, extensions, locationConstraints);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InstantiateVnfData {\n");

		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfFlavourId: ").append(toIndentedString(vnfFlavourId)).append("\n");
		sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
		sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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
