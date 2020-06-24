package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.model.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.model.KeyValuePairs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information related to a SAP of a NS. The
 * InstantiateVnfData data type specifies the parameters that are needed for VNF
 * instantiation. This information element is used for the bottom-up NS creation
 * when the OSS/BSS explicitly requests VNF instantiation for a given NS. When
 * the NFVO invokes the Instantiate VNF update operation, a set of these
 * parameters are then passed by the NFVO to the VNFM. It shall comply with the
 * provisions defined in Table 6.5.3.24-1.
 */
@ApiModel(description = "This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class InstantiateVnfData {
	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfFlavourId")
	private String vnfFlavourId = null;

	@JsonProperty("vnfInstantiationLevelId")
	private String vnfInstantiationLevelId = null;

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

	@JsonProperty("additionalParams")
	private KeyValuePairs additionalParams = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	@JsonProperty("extensions")
	private KeyValuePairs extensions = null;

	public InstantiateVnfData vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Information sufficient to identify the VNFD which defines the VNF to be
	 * instantiated.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Information sufficient to identify the VNFD which defines the VNF to be instantiated. ")
	@NotNull

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
	 * Identifier of the VNF deployment flavor to be instantiated.
	 *
	 * @return vnfFlavourId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavor to be instantiated. ")
	@NotNull

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
	 * Identifier of the instantiation level of the deployment flavor to be
	 * instantiated. If not present, the default instantiation level as declared in
	 * the VNFD is instantiated.
	 *
	 * @return vnfInstantiationLevelId
	 **/
	@ApiModelProperty(value = "Identifier of the instantiation level of the deployment flavor to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. ")

	public String getVnfInstantiationLevelId() {
		return vnfInstantiationLevelId;
	}

	public void setVnfInstantiationLevelId(final String vnfInstantiationLevelId) {
		this.vnfInstantiationLevelId = vnfInstantiationLevelId;
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
	@ApiModelProperty(value = "Human-readable name of the VNF instance to be created. ")

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
	@ApiModelProperty(value = "Human-readable description of the VNF instance to be created. ")

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
	@ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")

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
	 * VNFM.
	 *
	 * @return extManagedVirtualLinks
	 **/
	@ApiModelProperty(value = "Information about internal VLs that are managed by other entities than the VNFM. ")

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
	@ApiModelProperty(value = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")

	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	public InstantiateVnfData additionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional input parameters for the instantiation process, specific to the
	 * VNF being instantiated.
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional input parameters for the instantiation process, specific to the VNF being instantiated. ")

	@Valid

	public KeyValuePairs getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
	}

	public InstantiateVnfData metadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * This attribute provides values for the \"metadata\" input parameter of the
	 * Create VNF Identifier operation.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "This attribute provides values for the \"metadata\" input parameter of the Create VNF Identifier operation. ")

	@Valid

	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
	}

	public InstantiateVnfData extensions(final KeyValuePairs extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * This attribute provides values for the \"extensions\" input parameter of the
	 * Instantiate VNF operation.
	 *
	 * @return extensions
	 **/
	@ApiModelProperty(value = "This attribute provides values for the \"extensions\" input parameter of the Instantiate VNF operation. ")

	@Valid

	public KeyValuePairs getExtensions() {
		return extensions;
	}

	public void setExtensions(final KeyValuePairs extensions) {
		this.extensions = extensions;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InstantiateVnfData instantiateVnfData = (InstantiateVnfData) o;
		return Objects.equals(this.vnfdId, instantiateVnfData.vnfdId) &&
				Objects.equals(this.vnfFlavourId, instantiateVnfData.vnfFlavourId) &&
				Objects.equals(this.vnfInstantiationLevelId, instantiateVnfData.vnfInstantiationLevelId) &&
				Objects.equals(this.vnfInstanceName, instantiateVnfData.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, instantiateVnfData.vnfInstanceDescription) &&
				Objects.equals(this.extVirtualLinks, instantiateVnfData.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, instantiateVnfData.extManagedVirtualLinks) &&
				Objects.equals(this.localizationLanguage, instantiateVnfData.localizationLanguage) &&
				Objects.equals(this.additionalParams, instantiateVnfData.additionalParams) &&
				Objects.equals(this.metadata, instantiateVnfData.metadata) &&
				Objects.equals(this.extensions, instantiateVnfData.extensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdId, vnfFlavourId, vnfInstantiationLevelId, vnfInstanceName, vnfInstanceDescription, extVirtualLinks, extManagedVirtualLinks, localizationLanguage, additionalParams, metadata, extensions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InstantiateVnfData {\n");

		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfFlavourId: ").append(toIndentedString(vnfFlavourId)).append("\n");
		sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
		sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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
