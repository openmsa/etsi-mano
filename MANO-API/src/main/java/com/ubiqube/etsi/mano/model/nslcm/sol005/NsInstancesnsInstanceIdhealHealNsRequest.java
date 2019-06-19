package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This operation supports the healing of an NS instance,  either by healing the complete NS instance or by healing one of more of the VNF instances that are part of this NS.  It shall comply with the provisions defined in Table 6.5.2.13-1. 
 **/
@ApiModel(description="This operation supports the healing of an NS instance,  either by healing the complete NS instance or by healing one of more of the VNF instances that are part of this NS.  It shall comply with the provisions defined in Table 6.5.2.13-1. ")
public class NsInstancesnsInstanceIdhealHealNsRequest  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdhealHealNsRequestHealNsData healNsData = null;

  @ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed, as declared in the VNFD as part of \"HealVnfOpConfig\".         ")
  @Valid
 /**
   * Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed, as declared in the VNFD as part of \"HealVnfOpConfig\".         
  **/
  private List<NsInstancesnsInstanceIdhealHealNsRequestHealVnfData> healVnfData = null;
 /**
   * Get healNsData
   * @return healNsData
  **/
  @JsonProperty("healNsData")
  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData getHealNsData() {
    return healNsData;
  }

  public void setHealNsData(NsInstancesnsInstanceIdhealHealNsRequestHealNsData healNsData) {
    this.healNsData = healNsData;
  }

  public NsInstancesnsInstanceIdhealHealNsRequest healNsData(NsInstancesnsInstanceIdhealHealNsRequestHealNsData healNsData) {
    this.healNsData = healNsData;
    return this;
  }

 /**
   * Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed, as declared in the VNFD as part of \&quot;HealVnfOpConfig\&quot;.         
   * @return healVnfData
  **/
  @JsonProperty("healVnfData")
  public List<NsInstancesnsInstanceIdhealHealNsRequestHealVnfData> getHealVnfData() {
    return healVnfData;
  }

  public void setHealVnfData(List<NsInstancesnsInstanceIdhealHealNsRequestHealVnfData> healVnfData) {
    this.healVnfData = healVnfData;
  }

  public NsInstancesnsInstanceIdhealHealNsRequest healVnfData(List<NsInstancesnsInstanceIdhealHealNsRequestHealVnfData> healVnfData) {
    this.healVnfData = healVnfData;
    return this;
  }

  public NsInstancesnsInstanceIdhealHealNsRequest addHealVnfDataItem(NsInstancesnsInstanceIdhealHealNsRequestHealVnfData healVnfDataItem) {
    this.healVnfData.add(healVnfDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdhealHealNsRequest {\n");
    
    sb.append("    healNsData: ").append(toIndentedString(healNsData)).append("\n");
    sb.append("    healVnfData: ").append(toIndentedString(healVnfData)).append("\n");
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

