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
 * ZoneInfo
 */
@Validated
public class ZoneInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  public ZoneInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of this ZoneInfo instance, for the purpose of referencing it from other structures in the \"Grant\" structure.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The identifier of this ZoneInfo instance, for the purpose of referencing it from other structures in the \"Grant\" structure.")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ZoneInfo vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Get vimConnectionId
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public ZoneInfo zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * The identifier of the resource zone, as managed by the resource management layer (typically, the VIM).
   * @return zoneId
  **/
  @ApiModelProperty(required = true, value = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM).")
      @NotNull

    public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZoneInfo zoneInfo = (ZoneInfo) o;
    return Objects.equals(this.id, zoneInfo.id) &&
        Objects.equals(this.vimConnectionId, zoneInfo.vimConnectionId) &&
        Objects.equals(this.zoneId, zoneInfo.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vimConnectionId, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ZoneInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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
