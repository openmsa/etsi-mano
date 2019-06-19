package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. 
 **/
@ApiModel(description="This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfdId = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vnfFlavourId = null;

  @ApiModelProperty(value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vnfInstantiationLevelId = null;

  @ApiModelProperty(value = "Human-readable name of the VNF instance to be created. ")
 /**
   * Human-readable name of the VNF instance to be created. 
  **/
  private String vnfInstanceName = null;

  @ApiModelProperty(value = "Human-readable description of the VNF instance to be created. ")
 /**
   * Human-readable description of the VNF instance to be created. 
  **/
  private String vnfInstanceDescription = null;

  @ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")
  @Valid
 /**
   * Information about external VLs to connect the VNF to. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLinks = null;

  @ApiModelProperty(value = "Information about internal VLs that are managed by other entities than the VNFM. ")
  @Valid
 /**
   * Information about internal VLs that are managed by other entities than the VNFM. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks = null;

  @ApiModelProperty(value = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646 [16]. ")
 /**
   * Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646 [16]. 
  **/
  private String localizationLanguage = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParams = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfdId
  **/
  @JsonProperty("vnfdId")
  @NotNull
  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vnfFlavourId
  **/
  @JsonProperty("vnfFlavourId")
  @NotNull
  public String getVnfFlavourId() {
    return vnfFlavourId;
  }

  public void setVnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData vnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vnfInstantiationLevelId
  **/
  @JsonProperty("vnfInstantiationLevelId")
  public String getVnfInstantiationLevelId() {
    return vnfInstantiationLevelId;
  }

  public void setVnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData vnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
    return this;
  }

 /**
   * Human-readable name of the VNF instance to be created. 
   * @return vnfInstanceName
  **/
  @JsonProperty("vnfInstanceName")
  public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

 /**
   * Human-readable description of the VNF instance to be created. 
   * @return vnfInstanceDescription
  **/
  @JsonProperty("vnfInstanceDescription")
  public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

 /**
   * Information about external VLs to connect the VNF to. 
   * @return extVirtualLinks
  **/
  @JsonProperty("extVirtualLinks")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData extVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData addExtVirtualLinksItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extVirtualLinksItem) {
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

 /**
   * Information about internal VLs that are managed by other entities than the VNFM. 
   * @return extManagedVirtualLinks
  **/
  @JsonProperty("extManagedVirtualLinks")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData extManagedVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData addExtManagedVirtualLinksItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks extManagedVirtualLinksItem) {
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

 /**
   * Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646 [16]. 
   * @return localizationLanguage
  **/
  @JsonProperty("localizationLanguage")
  public String getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData localizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return additionalParams
  **/
  @JsonProperty("additionalParams")
  public Object getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData additionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData {\n");
    
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfFlavourId: ").append(toIndentedString(vnfFlavourId)).append("\n");
    sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

