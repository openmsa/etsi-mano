package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * The type represents the information that is requested to be changed  deployment flavor for an existing VNF instance.  It shall comply with the provisions defined in Table 6.5.3.25-1. 
 **/
@ApiModel(description="The type represents the information that is requested to be changed  deployment flavor for an existing VNF instance.  It shall comply with the provisions defined in Table 6.5.3.25-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String newFlavourId = null;

  @ApiModelProperty(value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String instantiationLevelId = null;

  @ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")
  @Valid
 /**
   * Information about external VLs to connect the VNF to. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLinks = null;

  @ApiModelProperty(value = "information about internal VLs that are managed by NFVO. ")
  @Valid
 /**
   * information about internal VLs that are managed by NFVO. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParams = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  @NotNull
  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return newFlavourId
  **/
  @JsonProperty("newFlavourId")
  @NotNull
  public String getNewFlavourId() {
    return newFlavourId;
  }

  public void setNewFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData newFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return instantiationLevelId
  **/
  @JsonProperty("instantiationLevelId")
  public String getInstantiationLevelId() {
    return instantiationLevelId;
  }

  public void setInstantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData instantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData extVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData addExtVirtualLinksItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extVirtualLinksItem) {
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

 /**
   * information about internal VLs that are managed by NFVO. 
   * @return extManagedVirtualLinks
  **/
  @JsonProperty("extManagedVirtualLinks")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData extManagedVirtualLinks(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData addExtManagedVirtualLinksItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks extManagedVirtualLinksItem) {
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData additionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

