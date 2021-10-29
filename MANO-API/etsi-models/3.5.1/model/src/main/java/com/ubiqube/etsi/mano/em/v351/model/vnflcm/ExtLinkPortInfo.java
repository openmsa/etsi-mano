package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a link port of an external VL, i.e. a port providing connectivity for the VNF to  an NS VL. It shall comply with the provisions defined in table 5.5.3.9-1. NOTE 1: The use cases UC#4 and UC#5 in Annex A.4 of ETSI GS NFV-IFA 007 provide examples for such a configuration. NOTE 2: The value of \&quot;trunkResourceId\&quot; is scoped by the value of \&quot;vimConnectionId\&quot; in the \&quot;resourceHandle\&quot; attribute. 
 */
@Schema(description = "This type represents information about a link port of an external VL, i.e. a port providing connectivity for the VNF to  an NS VL. It shall comply with the provisions defined in table 5.5.3.9-1. NOTE 1: The use cases UC#4 and UC#5 in Annex A.4 of ETSI GS NFV-IFA 007 provide examples for such a configuration. NOTE 2: The value of \"trunkResourceId\" is scoped by the value of \"vimConnectionId\" in the \"resourceHandle\" attribute. ")
@Validated


public class ExtLinkPortInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  @JsonProperty("secondaryCpInstanceId")
  private String secondaryCpInstanceId = null;

  @JsonProperty("trunkResourceId")
  private String trunkResourceId = null;

  public ExtLinkPortInfo id(String id) {
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

  public ExtLinkPortInfo resourceHandle(ResourceHandle resourceHandle) {
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

  public ExtLinkPortInfo cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Get cpInstanceId
   * @return cpInstanceId
   **/
  @Schema(description = "")
  
    public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public ExtLinkPortInfo secondaryCpInstanceId(String secondaryCpInstanceId) {
    this.secondaryCpInstanceId = secondaryCpInstanceId;
    return this;
  }

  /**
   * Get secondaryCpInstanceId
   * @return secondaryCpInstanceId
   **/
  @Schema(description = "")
  
    public String getSecondaryCpInstanceId() {
    return secondaryCpInstanceId;
  }

  public void setSecondaryCpInstanceId(String secondaryCpInstanceId) {
    this.secondaryCpInstanceId = secondaryCpInstanceId;
  }

  public ExtLinkPortInfo trunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
    return this;
  }

  /**
   * Get trunkResourceId
   * @return trunkResourceId
   **/
  @Schema(description = "")
  
    public String getTrunkResourceId() {
    return trunkResourceId;
  }

  public void setTrunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtLinkPortInfo extLinkPortInfo = (ExtLinkPortInfo) o;
    return Objects.equals(this.id, extLinkPortInfo.id) &&
        Objects.equals(this.resourceHandle, extLinkPortInfo.resourceHandle) &&
        Objects.equals(this.cpInstanceId, extLinkPortInfo.cpInstanceId) &&
        Objects.equals(this.secondaryCpInstanceId, extLinkPortInfo.secondaryCpInstanceId) &&
        Objects.equals(this.trunkResourceId, extLinkPortInfo.trunkResourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, cpInstanceId, secondaryCpInstanceId, trunkResourceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtLinkPortInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    secondaryCpInstanceId: ").append(toIndentedString(secondaryCpInstanceId)).append("\n");
    sb.append("    trunkResourceId: ").append(toIndentedString(trunkResourceId)).append("\n");
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
