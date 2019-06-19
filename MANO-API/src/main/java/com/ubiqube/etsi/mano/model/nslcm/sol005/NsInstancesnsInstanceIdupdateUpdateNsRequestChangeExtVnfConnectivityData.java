package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different external VL. 2) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address assignment) might not be supported without deleting the resource and creating another one with the modified configuration. This type shall comply with the provisions defined in Table 6.5.3.33-1. 
 **/
@ApiModel(description="This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different external VL. 2) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address assignment) might not be supported without deleting the resource and creating another one with the modified configuration. This type shall comply with the provisions defined in Table 6.5.3.33-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;

  @ApiModelProperty(required = true, value = "Information about external VLs to change (e.g. connect the VNF to). ")
  @Valid
 /**
   * Information about external VLs to change (e.g. connect the VNF to). 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLink = new ArrayList<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks>();

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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

 /**
   * Information about external VLs to change (e.g. connect the VNF to). 
   * @return extVirtualLink
  **/
  @JsonProperty("extVirtualLink")
  @NotNull
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> getExtVirtualLink() {
    return extVirtualLink;
  }

  public void setExtVirtualLink(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLink) {
    this.extVirtualLink = extVirtualLink;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData extVirtualLink(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks> extVirtualLink) {
    this.extVirtualLink = extVirtualLink;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData addExtVirtualLinkItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extVirtualLinkItem) {
    this.extVirtualLink.add(extVirtualLinkItem);
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData additionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    extVirtualLink: ").append(toIndentedString(extVirtualLink)).append("\n");
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

