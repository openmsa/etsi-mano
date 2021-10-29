package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.VnfLinkPortInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtManagedVirtualLinkInfo
 */
@Validated


public class ExtManagedVirtualLinkInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("networkResource")
  private ResourceHandle networkResource = null;

  @JsonProperty("vnfLinkPorts")
  @Valid
  private List<VnfLinkPortInfo> vnfLinkPorts = null;

  public ExtManagedVirtualLinkInfo id(String id) {
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

  public ExtManagedVirtualLinkInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

  /**
   * Get vnfVirtualLinkDescId
   * @return vnfVirtualLinkDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public ExtManagedVirtualLinkInfo vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public ExtManagedVirtualLinkInfo networkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
    return this;
  }

  /**
   * Get networkResource
   * @return networkResource
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public ExtManagedVirtualLinkInfo vnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
    return this;
  }

  public ExtManagedVirtualLinkInfo addVnfLinkPortsItem(VnfLinkPortInfo vnfLinkPortsItem) {
    if (this.vnfLinkPorts == null) {
      this.vnfLinkPorts = new ArrayList<>();
    }
    this.vnfLinkPorts.add(vnfLinkPortsItem);
    return this;
  }

  /**
   * Link ports of this VL. 
   * @return vnfLinkPorts
   **/
  @Schema(description = "Link ports of this VL. ")
      @Valid
    public List<VnfLinkPortInfo> getVnfLinkPorts() {
    return vnfLinkPorts;
  }

  public void setVnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtManagedVirtualLinkInfo extManagedVirtualLinkInfo = (ExtManagedVirtualLinkInfo) o;
    return Objects.equals(this.id, extManagedVirtualLinkInfo.id) &&
        Objects.equals(this.vnfVirtualLinkDescId, extManagedVirtualLinkInfo.vnfVirtualLinkDescId) &&
        Objects.equals(this.vnfdId, extManagedVirtualLinkInfo.vnfdId) &&
        Objects.equals(this.networkResource, extManagedVirtualLinkInfo.networkResource) &&
        Objects.equals(this.vnfLinkPorts, extManagedVirtualLinkInfo.vnfLinkPorts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, vnfdId, networkResource, vnfLinkPorts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtManagedVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
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
