package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfLinkPortData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtManagedVirtualLinkData
 */
@Validated


public class ExtManagedVirtualLinkData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vnfLinkPort")
  @Valid
  private List<VnfLinkPortData> vnfLinkPort = null;

  @JsonProperty("extManagedMultisiteVirtualLinkId")
  private String extManagedMultisiteVirtualLinkId = null;

  public ExtManagedVirtualLinkData id(String id) {
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

  public ExtManagedVirtualLinkData vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
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

  public ExtManagedVirtualLinkData vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Get vimConnectionId
   * @return vimConnectionId
   **/
  @Schema(description = "")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public ExtManagedVirtualLinkData resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Get resourceProviderId
   * @return resourceProviderId
   **/
  @Schema(description = "")
  
    public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public ExtManagedVirtualLinkData resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Get resourceId
   * @return resourceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ExtManagedVirtualLinkData vnfLinkPort(List<VnfLinkPortData> vnfLinkPort) {
    this.vnfLinkPort = vnfLinkPort;
    return this;
  }

  public ExtManagedVirtualLinkData addVnfLinkPortItem(VnfLinkPortData vnfLinkPortItem) {
    if (this.vnfLinkPort == null) {
      this.vnfLinkPort = new ArrayList<>();
    }
    this.vnfLinkPort.add(vnfLinkPortItem);
    return this;
  }

  /**
   * Externally provided link ports to be used to connect VNFC connection points to this externally-managed VL on this network resource. If this attribute is not present, the VNFM shall create the link ports on the externally-managed VL. 
   * @return vnfLinkPort
   **/
  @Schema(description = "Externally provided link ports to be used to connect VNFC connection points to this externally-managed VL on this network resource. If this attribute is not present, the VNFM shall create the link ports on the externally-managed VL. ")
      @Valid
    public List<VnfLinkPortData> getVnfLinkPort() {
    return vnfLinkPort;
  }

  public void setVnfLinkPort(List<VnfLinkPortData> vnfLinkPort) {
    this.vnfLinkPort = vnfLinkPort;
  }

  public ExtManagedVirtualLinkData extManagedMultisiteVirtualLinkId(String extManagedMultisiteVirtualLinkId) {
    this.extManagedMultisiteVirtualLinkId = extManagedMultisiteVirtualLinkId;
    return this;
  }

  /**
   * Get extManagedMultisiteVirtualLinkId
   * @return extManagedMultisiteVirtualLinkId
   **/
  @Schema(description = "")
  
    public String getExtManagedMultisiteVirtualLinkId() {
    return extManagedMultisiteVirtualLinkId;
  }

  public void setExtManagedMultisiteVirtualLinkId(String extManagedMultisiteVirtualLinkId) {
    this.extManagedMultisiteVirtualLinkId = extManagedMultisiteVirtualLinkId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtManagedVirtualLinkData extManagedVirtualLinkData = (ExtManagedVirtualLinkData) o;
    return Objects.equals(this.id, extManagedVirtualLinkData.id) &&
        Objects.equals(this.vnfVirtualLinkDescId, extManagedVirtualLinkData.vnfVirtualLinkDescId) &&
        Objects.equals(this.vimConnectionId, extManagedVirtualLinkData.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, extManagedVirtualLinkData.resourceProviderId) &&
        Objects.equals(this.resourceId, extManagedVirtualLinkData.resourceId) &&
        Objects.equals(this.vnfLinkPort, extManagedVirtualLinkData.vnfLinkPort) &&
        Objects.equals(this.extManagedMultisiteVirtualLinkId, extManagedVirtualLinkData.extManagedMultisiteVirtualLinkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, vimConnectionId, resourceProviderId, resourceId, vnfLinkPort, extManagedMultisiteVirtualLinkId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtManagedVirtualLinkData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vnfLinkPort: ").append(toIndentedString(vnfLinkPort)).append("\n");
    sb.append("    extManagedMultisiteVirtualLinkId: ").append(toIndentedString(extManagedMultisiteVirtualLinkId)).append("\n");
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
