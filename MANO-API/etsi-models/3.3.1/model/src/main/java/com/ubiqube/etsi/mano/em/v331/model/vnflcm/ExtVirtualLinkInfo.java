package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ExtLinkPortInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ResourceHandle;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfExtCpData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtVirtualLinkInfo
 */
@Validated


public class ExtVirtualLinkInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("extLinkPorts")
  @Valid
  private List<ExtLinkPortInfo> extLinkPorts = null;

  @JsonProperty("currentVnfExtCpData")
  @Valid
  private List<VnfExtCpData> currentVnfExtCpData = new ArrayList<>();

  public ExtVirtualLinkInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExtVirtualLinkInfo resourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  /**
   * Get resourceHandle
   * @return resourceHandle
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public ExtVirtualLinkInfo extLinkPorts(List<ExtLinkPortInfo> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public ExtVirtualLinkInfo addExtLinkPortsItem(ExtLinkPortInfo extLinkPortsItem) {
    if (this.extLinkPorts == null) {
      this.extLinkPorts = new ArrayList<>();
    }
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }

  /**
   * Link ports of this VL. 
   * @return extLinkPorts
   **/
  @Schema(description = "Link ports of this VL. ")
      @Valid
    public List<ExtLinkPortInfo> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<ExtLinkPortInfo> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }

  public ExtVirtualLinkInfo currentVnfExtCpData(List<VnfExtCpData> currentVnfExtCpData) {
    this.currentVnfExtCpData = currentVnfExtCpData;
    return this;
  }

  public ExtVirtualLinkInfo addCurrentVnfExtCpDataItem(VnfExtCpData currentVnfExtCpDataItem) {
    this.currentVnfExtCpData.add(currentVnfExtCpDataItem);
    return this;
  }

  /**
   * Allows the API consumer to read the current CP configuration information for the connection of external CPs to the external virtual link. This attribute reflects the current configuration information that has resulted from merging into this attribute the \"VnfExtCpData\" information which was passed as part of the \"ExtVirtualLinkData\" structure in the input of the most recent VNF LCM operation such as \"InstantiateVnfRequest\", \"ChangeExtVnfConnectivityRequest\", \"ChangeVnfFlavourRequest\" or \"ChangeCurrentVnfPkgRequest\", or has been provided by the NFVO during the granting procedure. If applying such change results in an empty list of \"currentVnfExtCpData\" structure instances, the affected instance of \"ExtVirtualLinkInfo\" shall be removed from its parent data structure. 
   * @return currentVnfExtCpData
   **/
  @Schema(required = true, description = "Allows the API consumer to read the current CP configuration information for the connection of external CPs to the external virtual link. This attribute reflects the current configuration information that has resulted from merging into this attribute the \"VnfExtCpData\" information which was passed as part of the \"ExtVirtualLinkData\" structure in the input of the most recent VNF LCM operation such as \"InstantiateVnfRequest\", \"ChangeExtVnfConnectivityRequest\", \"ChangeVnfFlavourRequest\" or \"ChangeCurrentVnfPkgRequest\", or has been provided by the NFVO during the granting procedure. If applying such change results in an empty list of \"currentVnfExtCpData\" structure instances, the affected instance of \"ExtVirtualLinkInfo\" shall be removed from its parent data structure. ")
      @NotNull
    @Valid
    public List<VnfExtCpData> getCurrentVnfExtCpData() {
    return currentVnfExtCpData;
  }

  public void setCurrentVnfExtCpData(List<VnfExtCpData> currentVnfExtCpData) {
    this.currentVnfExtCpData = currentVnfExtCpData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtVirtualLinkInfo extVirtualLinkInfo = (ExtVirtualLinkInfo) o;
    return Objects.equals(this.id, extVirtualLinkInfo.id) &&
        Objects.equals(this.resourceHandle, extVirtualLinkInfo.resourceHandle) &&
        Objects.equals(this.extLinkPorts, extVirtualLinkInfo.extLinkPorts) &&
        Objects.equals(this.currentVnfExtCpData, extVirtualLinkInfo.currentVnfExtCpData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, extLinkPorts, currentVnfExtCpData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
    sb.append("    currentVnfExtCpData: ").append(toIndentedString(currentVnfExtCpData)).append("\n");
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
