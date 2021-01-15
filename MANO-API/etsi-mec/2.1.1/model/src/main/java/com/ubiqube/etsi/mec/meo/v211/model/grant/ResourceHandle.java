package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResourceHandle
 */
@Validated
public class ResourceHandle   {
  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("vimLevelResourceType")
  private String vimLevelResourceType = null;

  public ResourceHandle resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Identifier of the resource in the scope of the VIM.
   * @return resourceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the resource in the scope of the VIM.")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ResourceHandle vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Identifier of the VIM connection to manage the resource.The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"AppInstance\" structure.
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "Identifier of the VIM connection to manage the resource.The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"AppInstance\" structure.")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public ResourceHandle vimLevelResourceType(String vimLevelResourceType) {
    this.vimLevelResourceType = vimLevelResourceType;
    return this;
  }

  /**
   * Type of the resource in the scope of the VIM. See note.
   * @return vimLevelResourceType
  **/
  @ApiModelProperty(value = "Type of the resource in the scope of the VIM. See note.")
  
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
    return Objects.equals(this.resourceId, resourceHandle.resourceId) &&
        Objects.equals(this.vimConnectionId, resourceHandle.vimConnectionId) &&
        Objects.equals(this.vimLevelResourceType, resourceHandle.vimLevelResourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceId, vimConnectionId, vimLevelResourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceHandle {\n");
    
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
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
