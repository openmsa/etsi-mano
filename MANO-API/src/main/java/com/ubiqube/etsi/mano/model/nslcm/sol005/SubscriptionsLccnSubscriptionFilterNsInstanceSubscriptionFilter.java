package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
  * This type represents subscription filter criteria to match NS instances.  It shall comply with the provisions defined in Table 4.4.1.5-1. 
 **/
@ApiModel(description="This type represents subscription filter criteria to match NS instances.  It shall comply with the provisions defined in Table 4.4.1.5-1. ")
public class SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter  {
  
  @ApiModelProperty(value = "If present, match NS instances that were created based on a NSD identified by one of the nsdId values listed in this attribute. ")
 /**
   * If present, match NS instances that were created based on a NSD identified by one of the nsdId values listed in this attribute. 
  **/
  private List<String> nsdIds = null;

  @ApiModelProperty(value = "If present, match NS instances that contain VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. ")
 /**
   * If present, match NS instances that contain VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. 
  **/
  private List<String> vnfdIds = null;

  @ApiModelProperty(value = "If present, match NS instances that contain PNFs that are represented by a PNFD identified by one of the pnfdId values listed in this attribute. ")
 /**
   * If present, match NS instances that contain PNFs that are represented by a PNFD identified by one of the pnfdId values listed in this attribute. 
  **/
  private List<String> pnfdIds = null;

  @ApiModelProperty(value = "If present, match NS instances with an instance identifier listed in this attribute. ")
 /**
   * If present, match NS instances with an instance identifier listed in this attribute. 
  **/
  private List<String> nsInstanceIds = null;

  @ApiModelProperty(value = "If present, match NS instances with a NS Instance Name listed in this attribute. ")
 /**
   * If present, match NS instances with a NS Instance Name listed in this attribute. 
  **/
  private List<String> nsInstanceNames = null;
 /**
   * If present, match NS instances that were created based on a NSD identified by one of the nsdId values listed in this attribute. 
   * @return nsdIds
  **/
  @JsonProperty("nsdIds")
  public List<String> getNsdIds() {
    return nsdIds;
  }

  public void setNsdIds(List<String> nsdIds) {
    this.nsdIds = nsdIds;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsdIds(List<String> nsdIds) {
    this.nsdIds = nsdIds;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter addNsdIdsItem(String nsdIdsItem) {
    this.nsdIds.add(nsdIdsItem);
    return this;
  }

 /**
   * If present, match NS instances that contain VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. 
   * @return vnfdIds
  **/
  @JsonProperty("vnfdIds")
  public List<String> getVnfdIds() {
    return vnfdIds;
  }

  public void setVnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter vnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter addVnfdIdsItem(String vnfdIdsItem) {
    this.vnfdIds.add(vnfdIdsItem);
    return this;
  }

 /**
   * If present, match NS instances that contain PNFs that are represented by a PNFD identified by one of the pnfdId values listed in this attribute. 
   * @return pnfdIds
  **/
  @JsonProperty("pnfdIds")
  public List<String> getPnfdIds() {
    return pnfdIds;
  }

  public void setPnfdIds(List<String> pnfdIds) {
    this.pnfdIds = pnfdIds;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter pnfdIds(List<String> pnfdIds) {
    this.pnfdIds = pnfdIds;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter addPnfdIdsItem(String pnfdIdsItem) {
    this.pnfdIds.add(pnfdIdsItem);
    return this;
  }

 /**
   * If present, match NS instances with an instance identifier listed in this attribute. 
   * @return nsInstanceIds
  **/
  @JsonProperty("nsInstanceIds")
  public List<String> getNsInstanceIds() {
    return nsInstanceIds;
  }

  public void setNsInstanceIds(List<String> nsInstanceIds) {
    this.nsInstanceIds = nsInstanceIds;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceIds(List<String> nsInstanceIds) {
    this.nsInstanceIds = nsInstanceIds;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter addNsInstanceIdsItem(String nsInstanceIdsItem) {
    this.nsInstanceIds.add(nsInstanceIdsItem);
    return this;
  }

 /**
   * If present, match NS instances with a NS Instance Name listed in this attribute. 
   * @return nsInstanceNames
  **/
  @JsonProperty("nsInstanceNames")
  public List<String> getNsInstanceNames() {
    return nsInstanceNames;
  }

  public void setNsInstanceNames(List<String> nsInstanceNames) {
    this.nsInstanceNames = nsInstanceNames;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceNames(List<String> nsInstanceNames) {
    this.nsInstanceNames = nsInstanceNames;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter addNsInstanceNamesItem(String nsInstanceNamesItem) {
    this.nsInstanceNames.add(nsInstanceNamesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter {\n");
    
    sb.append("    nsdIds: ").append(toIndentedString(nsdIds)).append("\n");
    sb.append("    vnfdIds: ").append(toIndentedString(vnfdIds)).append("\n");
    sb.append("    pnfdIds: ").append(toIndentedString(pnfdIds)).append("\n");
    sb.append("    nsInstanceIds: ").append(toIndentedString(nsInstanceIds)).append("\n");
    sb.append("    nsInstanceNames: ").append(toIndentedString(nsInstanceNames)).append("\n");
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

