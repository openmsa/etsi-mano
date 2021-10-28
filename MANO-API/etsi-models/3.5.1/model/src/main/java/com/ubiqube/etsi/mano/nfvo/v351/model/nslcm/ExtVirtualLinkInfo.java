package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ExtLinkPortInfo;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ResourceHandle;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfExtCpData;
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
  private VnfExtCpData currentVnfExtCpData = null;

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

  public ExtVirtualLinkInfo currentVnfExtCpData(VnfExtCpData currentVnfExtCpData) {
    this.currentVnfExtCpData = currentVnfExtCpData;
    return this;
  }

  /**
   * Get currentVnfExtCpData
   * @return currentVnfExtCpData
   **/
  @Schema(description = "")
  
    @Valid
    public VnfExtCpData getCurrentVnfExtCpData() {
    return currentVnfExtCpData;
  }

  public void setCurrentVnfExtCpData(VnfExtCpData currentVnfExtCpData) {
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
