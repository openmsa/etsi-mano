package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.VnfLinkPortData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1. 
 */
@Schema(description = "This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1. ")
@Validated


public class ExtManagedVirtualLinkData   {
  @JsonProperty("extManagedVirtualLinkId")
  private String extManagedVirtualLinkId = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vnfLinkPort")
  @Valid
  private List<VnfLinkPortData> vnfLinkPort = null;

  @JsonProperty("extManagedMultisiteVirtualLinkId")
  private String extManagedMultisiteVirtualLinkId = null;

  public ExtManagedVirtualLinkData extManagedVirtualLinkId(String extManagedVirtualLinkId) {
    this.extManagedVirtualLinkId = extManagedVirtualLinkId;
    return this;
  }

  /**
   * Get extManagedVirtualLinkId
   * @return extManagedVirtualLinkId
   **/
  @Schema(description = "")
  
    public String getExtManagedVirtualLinkId() {
    return extManagedVirtualLinkId;
  }

  public void setExtManagedVirtualLinkId(String extManagedVirtualLinkId) {
    this.extManagedVirtualLinkId = extManagedVirtualLinkId;
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

  public ExtManagedVirtualLinkData vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
   **/
  @Schema(description = "")
  
    public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
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
    return Objects.equals(this.extManagedVirtualLinkId, extManagedVirtualLinkData.extManagedVirtualLinkId) &&
        Objects.equals(this.vnfVirtualLinkDescId, extManagedVirtualLinkData.vnfVirtualLinkDescId) &&
        Objects.equals(this.vimId, extManagedVirtualLinkData.vimId) &&
        Objects.equals(this.resourceProviderId, extManagedVirtualLinkData.resourceProviderId) &&
        Objects.equals(this.resourceId, extManagedVirtualLinkData.resourceId) &&
        Objects.equals(this.vnfLinkPort, extManagedVirtualLinkData.vnfLinkPort) &&
        Objects.equals(this.extManagedMultisiteVirtualLinkId, extManagedVirtualLinkData.extManagedMultisiteVirtualLinkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extManagedVirtualLinkId, vnfVirtualLinkDescId, vimId, resourceProviderId, resourceId, vnfLinkPort, extManagedMultisiteVirtualLinkId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtManagedVirtualLinkData {\n");
    
    sb.append("    extManagedVirtualLinkId: ").append(toIndentedString(extManagedVirtualLinkId)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
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
