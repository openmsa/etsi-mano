package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents subscription filter criteria to match NS instances. 
 */
@ApiModel(description = "This type represents subscription filter criteria to match NS instances. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class NsInstanceSubscriptionFilter   {
  @JsonProperty("nsdIds")
  @Valid
  private List<String> nsdIds = null;

  @JsonProperty("vnfdIds")
  @Valid
  private List<String> vnfdIds = null;

  @JsonProperty("pnfdIds")
  @Valid
  private List<String> pnfdIds = null;

  @JsonProperty("nsInstanceIds")
  @Valid
  private List<String> nsInstanceIds = null;

  @JsonProperty("nsInstanceNames")
  @Valid
  private List<String> nsInstanceNames = null;

  public NsInstanceSubscriptionFilter nsdIds(List<String> nsdIds) {
    this.nsdIds = nsdIds;
    return this;
  }

  public NsInstanceSubscriptionFilter addNsdIdsItem(String nsdIdsItem) {
    if (this.nsdIds == null) {
      this.nsdIds = new ArrayList<>();
    }
    this.nsdIds.add(nsdIdsItem);
    return this;
  }

  /**
   * If present, match NS instances that were created based on a NSD identified by one of the nsdId values listed in this attribute. 
   * @return nsdIds
  **/
  @ApiModelProperty(value = "If present, match NS instances that were created based on a NSD identified by one of the nsdId values listed in this attribute. ")


  public List<String> getNsdIds() {
    return nsdIds;
  }

  public void setNsdIds(List<String> nsdIds) {
    this.nsdIds = nsdIds;
  }

  public NsInstanceSubscriptionFilter vnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
    return this;
  }

  public NsInstanceSubscriptionFilter addVnfdIdsItem(String vnfdIdsItem) {
    if (this.vnfdIds == null) {
      this.vnfdIds = new ArrayList<>();
    }
    this.vnfdIds.add(vnfdIdsItem);
    return this;
  }

  /**
   * If present, match NS instances that contain VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. 
   * @return vnfdIds
  **/
  @ApiModelProperty(value = "If present, match NS instances that contain VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. ")


  public List<String> getVnfdIds() {
    return vnfdIds;
  }

  public void setVnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
  }

  public NsInstanceSubscriptionFilter pnfdIds(List<String> pnfdIds) {
    this.pnfdIds = pnfdIds;
    return this;
  }

  public NsInstanceSubscriptionFilter addPnfdIdsItem(String pnfdIdsItem) {
    if (this.pnfdIds == null) {
      this.pnfdIds = new ArrayList<>();
    }
    this.pnfdIds.add(pnfdIdsItem);
    return this;
  }

  /**
   * If present, match NS instances that contain PNFs that are represented by a PNFD identified by one of the pnfdId values listed in this attribute. 
   * @return pnfdIds
  **/
  @ApiModelProperty(value = "If present, match NS instances that contain PNFs that are represented by a PNFD identified by one of the pnfdId values listed in this attribute. ")


  public List<String> getPnfdIds() {
    return pnfdIds;
  }

  public void setPnfdIds(List<String> pnfdIds) {
    this.pnfdIds = pnfdIds;
  }

  public NsInstanceSubscriptionFilter nsInstanceIds(List<String> nsInstanceIds) {
    this.nsInstanceIds = nsInstanceIds;
    return this;
  }

  public NsInstanceSubscriptionFilter addNsInstanceIdsItem(String nsInstanceIdsItem) {
    if (this.nsInstanceIds == null) {
      this.nsInstanceIds = new ArrayList<>();
    }
    this.nsInstanceIds.add(nsInstanceIdsItem);
    return this;
  }

  /**
   * If present, match NS instances with an instance identifier listed in this attribute. 
   * @return nsInstanceIds
  **/
  @ApiModelProperty(value = "If present, match NS instances with an instance identifier listed in this attribute. ")


  public List<String> getNsInstanceIds() {
    return nsInstanceIds;
  }

  public void setNsInstanceIds(List<String> nsInstanceIds) {
    this.nsInstanceIds = nsInstanceIds;
  }

  public NsInstanceSubscriptionFilter nsInstanceNames(List<String> nsInstanceNames) {
    this.nsInstanceNames = nsInstanceNames;
    return this;
  }

  public NsInstanceSubscriptionFilter addNsInstanceNamesItem(String nsInstanceNamesItem) {
    if (this.nsInstanceNames == null) {
      this.nsInstanceNames = new ArrayList<>();
    }
    this.nsInstanceNames.add(nsInstanceNamesItem);
    return this;
  }

  /**
   * If present, match NS instances with a NS Instance Name listed in this attribute. 
   * @return nsInstanceNames
  **/
  @ApiModelProperty(value = "If present, match NS instances with a NS Instance Name listed in this attribute. ")


  public List<String> getNsInstanceNames() {
    return nsInstanceNames;
  }

  public void setNsInstanceNames(List<String> nsInstanceNames) {
    this.nsInstanceNames = nsInstanceNames;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter = (NsInstanceSubscriptionFilter) o;
    return Objects.equals(this.nsdIds, nsInstanceSubscriptionFilter.nsdIds) &&
        Objects.equals(this.vnfdIds, nsInstanceSubscriptionFilter.vnfdIds) &&
        Objects.equals(this.pnfdIds, nsInstanceSubscriptionFilter.pnfdIds) &&
        Objects.equals(this.nsInstanceIds, nsInstanceSubscriptionFilter.nsInstanceIds) &&
        Objects.equals(this.nsInstanceNames, nsInstanceSubscriptionFilter.nsInstanceNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsdIds, vnfdIds, pnfdIds, nsInstanceIds, nsInstanceNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstanceSubscriptionFilter {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

