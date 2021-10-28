package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type contains a mapping between a software image definition the VNFD and the corresponding software image managed by the NFVO in the VIM which is needed during compute resource instantiation. 
 */
@Schema(description = "This type contains a mapping between a software image definition the VNFD and the corresponding software image managed by the NFVO in the VIM which is needed during compute resource instantiation. ")
@Validated


public class VimSoftwareImage   {
  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("vnfdSoftwareImageId")
  private String vnfdSoftwareImageId = null;

  @JsonProperty("vimSoftwareImageId")
  private String vimSoftwareImageId = null;

  public VimSoftwareImage vimConnectionId(String vimConnectionId) {
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

  public VimSoftwareImage resourceProviderId(String resourceProviderId) {
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

  public VimSoftwareImage vnfdSoftwareImageId(String vnfdSoftwareImageId) {
    this.vnfdSoftwareImageId = vnfdSoftwareImageId;
    return this;
  }

  /**
   * Get vnfdSoftwareImageId
   * @return vnfdSoftwareImageId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdSoftwareImageId() {
    return vnfdSoftwareImageId;
  }

  public void setVnfdSoftwareImageId(String vnfdSoftwareImageId) {
    this.vnfdSoftwareImageId = vnfdSoftwareImageId;
  }

  public VimSoftwareImage vimSoftwareImageId(String vimSoftwareImageId) {
    this.vimSoftwareImageId = vimSoftwareImageId;
    return this;
  }

  /**
   * Get vimSoftwareImageId
   * @return vimSoftwareImageId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVimSoftwareImageId() {
    return vimSoftwareImageId;
  }

  public void setVimSoftwareImageId(String vimSoftwareImageId) {
    this.vimSoftwareImageId = vimSoftwareImageId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimSoftwareImage vimSoftwareImage = (VimSoftwareImage) o;
    return Objects.equals(this.vimConnectionId, vimSoftwareImage.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, vimSoftwareImage.resourceProviderId) &&
        Objects.equals(this.vnfdSoftwareImageId, vimSoftwareImage.vnfdSoftwareImageId) &&
        Objects.equals(this.vimSoftwareImageId, vimSoftwareImage.vimSoftwareImageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimConnectionId, resourceProviderId, vnfdSoftwareImageId, vimSoftwareImageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimSoftwareImage {\n");
    
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    vnfdSoftwareImageId: ").append(toIndentedString(vnfdSoftwareImageId)).append("\n");
    sb.append("    vimSoftwareImageId: ").append(toIndentedString(vimSoftwareImageId)).append("\n");
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
