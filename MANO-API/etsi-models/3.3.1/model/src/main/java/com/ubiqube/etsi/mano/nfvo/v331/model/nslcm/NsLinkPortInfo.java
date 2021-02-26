package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.NsCpHandle;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a link port of a VL instance. It shall comply with the provisions defined in Table 6.5.3.55-1. 
 */
@Schema(description = "This type represents information about a link port of a VL instance. It shall comply with the provisions defined in Table 6.5.3.55-1. ")
@Validated


public class NsLinkPortInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("nsCpHandle")
  private NsCpHandle nsCpHandle = null;

  public NsLinkPortInfo id(String id) {
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

  public NsLinkPortInfo resourceHandle(ResourceHandle resourceHandle) {
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

  public NsLinkPortInfo nsCpHandle(NsCpHandle nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
    return this;
  }

  /**
   * Get nsCpHandle
   * @return nsCpHandle
   **/
  @Schema(description = "")
  
    @Valid
    public NsCpHandle getNsCpHandle() {
    return nsCpHandle;
  }

  public void setNsCpHandle(NsCpHandle nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsLinkPortInfo nsLinkPortInfo = (NsLinkPortInfo) o;
    return Objects.equals(this.id, nsLinkPortInfo.id) &&
        Objects.equals(this.resourceHandle, nsLinkPortInfo.resourceHandle) &&
        Objects.equals(this.nsCpHandle, nsLinkPortInfo.nsCpHandle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, nsCpHandle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLinkPortInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
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
