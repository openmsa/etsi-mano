package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.VimConnectionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Resource information for an existing resource
 */
@ApiModel(description = "Resource information for an existing resource")
@Validated
public class Resource   {
  @JsonProperty("vimConnectionInfo")
  private VimConnectionInfo vimConnectionInfo = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  public Resource vimConnectionInfo(VimConnectionInfo vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  /**
   * Get vimConnectionInfo
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public VimConnectionInfo getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(VimConnectionInfo vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public Resource resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Get resourceId
   * @return resourceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resource resource = (Resource) o;
    return Objects.equals(this.vimConnectionInfo, resource.vimConnectionInfo) &&
        Objects.equals(this.resourceId, resource.resourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimConnectionInfo, resourceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resource {\n");
    
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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
