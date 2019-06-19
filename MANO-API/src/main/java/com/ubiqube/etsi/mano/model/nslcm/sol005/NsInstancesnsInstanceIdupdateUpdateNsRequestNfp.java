package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type contains information used to create or modify NFP instance parameters  for the update of an existing VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.38-1. 
 **/
@ApiModel(description="This type contains information used to create or modify NFP instance parameters  for the update of an existing VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.38-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestNfp  {
  
  @ApiModelProperty(value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String nfpInfoId = null;

  @ApiModelProperty(value = "Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. ")
 /**
   * Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. 
  **/
  private String nfpName = null;

  @ApiModelProperty(value = "Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. ")
 /**
   * Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. 
  **/
  private String description = null;

  @ApiModelProperty(value = "Identifier(s) of the CPs and SAPs which the NFP passes by.  Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. ")
  @Valid
 /**
   * Identifier(s) of the CPs and SAPs which the NFP passes by.  Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. 
  **/
  private List<NsInstancesNsInstanceNsCpHandle> nsCpHandle = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule nfpRule = null;
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
   * @return nfpInfoId
  **/
  @JsonProperty("nfpInfoId")
  public String getNfpInfoId() {
    return nfpInfoId;
  }

  public void setNfpInfoId(String nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp nfpInfoId(String nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
    return this;
  }

 /**
   * Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. 
   * @return nfpName
  **/
  @JsonProperty("nfpName")
  public String getNfpName() {
    return nfpName;
  }

  public void setNfpName(String nfpName) {
    this.nfpName = nfpName;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp nfpName(String nfpName) {
    this.nfpName = nfpName;
    return this;
  }

 /**
   * Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. 
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Identifier(s) of the CPs and SAPs which the NFP passes by.  Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. 
   * @return nsCpHandle
  **/
  @JsonProperty("nsCpHandle")
  public List<NsInstancesNsInstanceNsCpHandle> getNsCpHandle() {
    return nsCpHandle;
  }

  public void setNsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp nsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp addNsCpHandleItem(NsInstancesNsInstanceNsCpHandle nsCpHandleItem) {
    this.nsCpHandle.add(nsCpHandleItem);
    return this;
  }

 /**
   * Get nfpRule
   * @return nfpRule
  **/
  @JsonProperty("nfpRule")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule getNfpRule() {
    return nfpRule;
  }

  public void setNfpRule(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule nfpRule) {
    this.nfpRule = nfpRule;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfp nfpRule(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule nfpRule) {
    this.nfpRule = nfpRule;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestNfp {\n");
    
    sb.append("    nfpInfoId: ").append(toIndentedString(nfpInfoId)).append("\n");
    sb.append("    nfpName: ").append(toIndentedString(nfpName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
    sb.append("    nfpRule: ").append(toIndentedString(nfpRule)).append("\n");
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

