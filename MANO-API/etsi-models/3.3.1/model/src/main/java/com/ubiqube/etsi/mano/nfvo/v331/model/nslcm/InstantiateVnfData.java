package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.VnfLocationConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. 
 */
@Schema(description = "This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. ")
@Validated


public class InstantiateVnfData   {
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

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("locationConstraints")
  private VnfLocationConstraint locationConstraints = null;

  public InstantiateVnfData vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public InstantiateVnfData vnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
    return this;
  }

  /**
   * Get vnfFlavourId
   * @return vnfFlavourId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfFlavourId() {
    return vnfFlavourId;
  }

  public void setVnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
  }

  public InstantiateVnfData vnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
    return this;
  }

  /**
   * Get vnfInstantiationLevelId
   * @return vnfInstantiationLevelId
   **/
  @Schema(description = "")
  
    public String getVnfInstantiationLevelId() {
    return vnfInstantiationLevelId;
  }

  public void setVnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
  }

  public InstantiateVnfData vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Human-readable name of the VNF instance to be created. 
   * @return vnfInstanceName
   **/
  @Schema(description = "Human-readable name of the VNF instance to be created. ")
  
    public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public InstantiateVnfData vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the VNF instance to be created. 
   * @return vnfInstanceDescription
   **/
  @Schema(description = "Human-readable description of the VNF instance to be created. ")
  
    public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public InstantiateVnfData extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public InstantiateVnfData addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. 
   * @return extVirtualLinks
   **/
  @Schema(description = "Information about external VLs to connect the VNF to. ")
      @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public InstantiateVnfData extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public InstantiateVnfData addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * Information about internal VLs that are managed by other entities than the VNFM. It is possible to have several ExtManagedVirtualLinkData for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 6.5.3.27). 
   * @return extManagedVirtualLinks
   **/
  @Schema(description = "Information about internal VLs that are managed by other entities than the VNFM. It is possible to have several ExtManagedVirtualLinkData for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 6.5.3.27). ")
      @Valid
    public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public InstantiateVnfData localizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

  /**
   * Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. 
   * @return localizationLanguage
   **/
  @Schema(description = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")
  
    public String getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public InstantiateVnfData vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Get vnfConfigurableProperties
   * @return vnfConfigurableProperties
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public InstantiateVnfData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public InstantiateVnfData metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public InstantiateVnfData extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Get extensions
   * @return extensions
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public InstantiateVnfData locationConstraints(VnfLocationConstraint locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  /**
   * Get locationConstraints
   * @return locationConstraints
   **/
  @Schema(description = "")
  
    @Valid
    public VnfLocationConstraint getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(VnfLocationConstraint locationConstraints) {
    this.locationConstraints = locationConstraints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstantiateVnfData instantiateVnfData = (InstantiateVnfData) o;
    return Objects.equals(this.vnfdId, instantiateVnfData.vnfdId) &&
        Objects.equals(this.vnfFlavourId, instantiateVnfData.vnfFlavourId) &&
        Objects.equals(this.vnfInstantiationLevelId, instantiateVnfData.vnfInstantiationLevelId) &&
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
    return Objects.hash(vnfdId, vnfFlavourId, vnfInstantiationLevelId, vnfInstanceName, vnfInstanceDescription, extVirtualLinks, extManagedVirtualLinks, localizationLanguage, vnfConfigurableProperties, additionalParams, metadata, extensions, locationConstraints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstantiateVnfData {\n");
    
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfFlavourId: ").append(toIndentedString(vnfFlavourId)).append("\n");
    sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
