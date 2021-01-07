package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.AppExtCpData;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ExtLinkPortData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtVirtualLinkData
 */
@Validated
public class ExtVirtualLinkData   {
  @JsonProperty("extCps")
  @Valid
  private List<AppExtCpData> extCps = new ArrayList<>();

  @JsonProperty("extLinkPorts")
  @Valid
  private List<ExtLinkPortData> extLinkPorts = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  public ExtVirtualLinkData extCps(List<AppExtCpData> extCps) {
    this.extCps = extCps;
    return this;
  }

  public ExtVirtualLinkData addExtCpsItem(AppExtCpData extCpsItem) {
    this.extCps.add(extCpsItem);
    return this;
  }

  /**
   * External CPs of the application instance to be connected to this external VL.
   * @return extCps
  **/
  @ApiModelProperty(required = true, value = "External CPs of the application instance to be connected to this external VL.")
      @NotNull
    @Valid
  @Size(min=1)   public List<AppExtCpData> getExtCps() {
    return extCps;
  }

  public void setExtCps(List<AppExtCpData> extCps) {
    this.extCps = extCps;
  }

  public ExtVirtualLinkData extLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public ExtVirtualLinkData addExtLinkPortsItem(ExtLinkPortData extLinkPortsItem) {
    if (this.extLinkPorts == null) {
      this.extLinkPorts = new ArrayList<>();
    }
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }

  /**
   * Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the MEPM shall create the link ports on the external VL.
   * @return extLinkPorts
  **/
  @ApiModelProperty(value = "Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the MEPM shall create the link ports on the external VL.")
      @Valid
    public List<ExtLinkPortData> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }

  public ExtVirtualLinkData id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the external VL instance. The identifier is assigned by the MEC entity that manages this VL instance.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The identifier of the external VL instance. The identifier is assigned by the MEC entity that manages this VL instance.")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExtVirtualLinkData resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * The identifier of the resource in the scope of the VIM.
   * @return resourceId
  **/
  @ApiModelProperty(required = true, value = "The identifier of the resource in the scope of the VIM.")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ExtVirtualLinkData vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Identifier of the VIM connection to manage this resource. 
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "Identifier of the VIM connection to manage this resource. ")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtVirtualLinkData extVirtualLinkData = (ExtVirtualLinkData) o;
    return Objects.equals(this.extCps, extVirtualLinkData.extCps) &&
        Objects.equals(this.extLinkPorts, extVirtualLinkData.extLinkPorts) &&
        Objects.equals(this.id, extVirtualLinkData.id) &&
        Objects.equals(this.resourceId, extVirtualLinkData.resourceId) &&
        Objects.equals(this.vimConnectionId, extVirtualLinkData.vimConnectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extCps, extLinkPorts, id, resourceId, vimConnectionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtVirtualLinkData {\n");
    
    sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
    sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
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
