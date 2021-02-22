package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ResourceHandle;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfLinkPortInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. 
 */
@Schema(description = "This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. ")
@Validated


public class VnfVirtualLinkResourceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("networkResource")
  private ResourceHandle networkResource = null;

  @JsonProperty("reservationId")
  private String reservationId = null;

  @JsonProperty("vnfLinkPorts")
  @Valid
  private List<VnfLinkPortInfo> vnfLinkPorts = new ArrayList<>();

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public VnfVirtualLinkResourceInfo id(String id) {
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

  public VnfVirtualLinkResourceInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
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

  public VnfVirtualLinkResourceInfo vnfdId(String vnfdId) {
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

  public VnfVirtualLinkResourceInfo networkResource(ResourceHandle networkResource) {
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

  public VnfVirtualLinkResourceInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

  /**
   * Get reservationId
   * @return reservationId
   **/
  @Schema(description = "")
  
    public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public VnfVirtualLinkResourceInfo vnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
    return this;
  }

  public VnfVirtualLinkResourceInfo addVnfLinkPortsItem(VnfLinkPortInfo vnfLinkPortsItem) {
    this.vnfLinkPorts.add(vnfLinkPortsItem);
    return this;
  }

  /**
   * Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. 
   * @return vnfLinkPorts
   **/
  @Schema(required = true, description = "Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. ")
      @NotNull
    @Valid
    public List<VnfLinkPortInfo> getVnfLinkPorts() {
    return vnfLinkPorts;
  }

  public void setVnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
  }

  public VnfVirtualLinkResourceInfo metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfVirtualLinkResourceInfo vnfVirtualLinkResourceInfo = (VnfVirtualLinkResourceInfo) o;
    return Objects.equals(this.id, vnfVirtualLinkResourceInfo.id) &&
        Objects.equals(this.vnfVirtualLinkDescId, vnfVirtualLinkResourceInfo.vnfVirtualLinkDescId) &&
        Objects.equals(this.vnfdId, vnfVirtualLinkResourceInfo.vnfdId) &&
        Objects.equals(this.networkResource, vnfVirtualLinkResourceInfo.networkResource) &&
        Objects.equals(this.reservationId, vnfVirtualLinkResourceInfo.reservationId) &&
        Objects.equals(this.vnfLinkPorts, vnfVirtualLinkResourceInfo.vnfLinkPorts) &&
        Objects.equals(this.metadata, vnfVirtualLinkResourceInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, vnfdId, networkResource, reservationId, vnfLinkPorts, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfVirtualLinkResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
