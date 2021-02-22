package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance or by an NS instance. Information about the resource is available from the VIM. 
 */
@Schema(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance or by an NS instance. Information about the resource is available from the VIM. ")
@Validated


public class ResourceHandle   {
  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vimLevelResourceType")
  private String vimLevelResourceType = null;

  public ResourceHandle vimId(String vimId) {
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

  public ResourceHandle resourceProviderId(String resourceProviderId) {
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

  public ResourceHandle resourceId(String resourceId) {
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

  public ResourceHandle vimLevelResourceType(String vimLevelResourceType) {
    this.vimLevelResourceType = vimLevelResourceType;
    return this;
  }

  /**
   * Type of the resource in the scope of the VIM, the WIM or the resource provider. The value set of the \"vimLevelResourceType\" attribute is within the scope of the VIM, the WIM or the resource provider and can be used as information that complements the ResourceHandle. 
   * @return vimLevelResourceType
   **/
  @Schema(description = "Type of the resource in the scope of the VIM, the WIM or the resource provider. The value set of the \"vimLevelResourceType\" attribute is within the scope of the VIM, the WIM or the resource provider and can be used as information that complements the ResourceHandle. ")
  
    public String getVimLevelResourceType() {
    return vimLevelResourceType;
  }

  public void setVimLevelResourceType(String vimLevelResourceType) {
    this.vimLevelResourceType = vimLevelResourceType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceHandle resourceHandle = (ResourceHandle) o;
    return Objects.equals(this.vimId, resourceHandle.vimId) &&
        Objects.equals(this.resourceProviderId, resourceHandle.resourceProviderId) &&
        Objects.equals(this.resourceId, resourceHandle.resourceId) &&
        Objects.equals(this.vimLevelResourceType, resourceHandle.vimLevelResourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimId, resourceProviderId, resourceId, vimLevelResourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceHandle {\n");
    
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vimLevelResourceType: ").append(toIndentedString(vimLevelResourceType)).append("\n");
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
