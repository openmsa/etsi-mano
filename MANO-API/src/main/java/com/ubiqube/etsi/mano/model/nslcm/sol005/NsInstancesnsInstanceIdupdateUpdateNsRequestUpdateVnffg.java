package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type specifies the parameters used for the update of an existing VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.37-1. 
 **/
@ApiModel(description="This type specifies the parameters used for the update of an existing VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.37-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String vnffgInfoId = null;

  @ApiModelProperty(value = "Indicate the desired new NFP(s) for a given VNFFG after the operations of addition/removal of NS components (e.g. VNFs, VLs, etc.) have been completed, or indicate the updated or newly created NFP classification and selection rule which applied to an existing NFP. ")
  @Valid
 /**
   * Indicate the desired new NFP(s) for a given VNFFG after the operations of addition/removal of NS components (e.g. VNFs, VLs, etc.) have been completed, or indicate the updated or newly created NFP classification and selection rule which applied to an existing NFP. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfp> nfp = null;

  @ApiModelProperty(value = "Identifier(s) of the NFP to be deleted from a given VNFFG. ")
 /**
   * Identifier(s) of the NFP to be deleted from a given VNFFG. 
  **/
  private List<String> nfpInfoId = null;
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
   * @return vnffgInfoId
  **/
  @JsonProperty("vnffgInfoId")
  @NotNull
  public String getVnffgInfoId() {
    return vnffgInfoId;
  }

  public void setVnffgInfoId(String vnffgInfoId) {
    this.vnffgInfoId = vnffgInfoId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg vnffgInfoId(String vnffgInfoId) {
    this.vnffgInfoId = vnffgInfoId;
    return this;
  }

 /**
   * Indicate the desired new NFP(s) for a given VNFFG after the operations of addition/removal of NS components (e.g. VNFs, VLs, etc.) have been completed, or indicate the updated or newly created NFP classification and selection rule which applied to an existing NFP. 
   * @return nfp
  **/
  @JsonProperty("nfp")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfp> getNfp() {
    return nfp;
  }

  public void setNfp(List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfp> nfp) {
    this.nfp = nfp;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg nfp(List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfp> nfp) {
    this.nfp = nfp;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg addNfpItem(NsInstancesnsInstanceIdupdateUpdateNsRequestNfp nfpItem) {
    this.nfp.add(nfpItem);
    return this;
  }

 /**
   * Identifier(s) of the NFP to be deleted from a given VNFFG. 
   * @return nfpInfoId
  **/
  @JsonProperty("nfpInfoId")
  public List<String> getNfpInfoId() {
    return nfpInfoId;
  }

  public void setNfpInfoId(List<String> nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg nfpInfoId(List<String> nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg addNfpInfoIdItem(String nfpInfoIdItem) {
    this.nfpInfoId.add(nfpInfoIdItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg {\n");
    
    sb.append("    vnffgInfoId: ").append(toIndentedString(vnffgInfoId)).append("\n");
    sb.append("    nfp: ").append(toIndentedString(nfp)).append("\n");
    sb.append("    nfpInfoId: ").append(toIndentedString(nfpInfoId)).append("\n");
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

