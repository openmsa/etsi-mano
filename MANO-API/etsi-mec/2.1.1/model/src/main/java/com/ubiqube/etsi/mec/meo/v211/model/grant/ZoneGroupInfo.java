package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ZoneGroupInfo
 */
@Validated
public class ZoneGroupInfo   {
  @JsonProperty("zoneId")
  @Valid
  private List<String> zoneId = new ArrayList<>();

  public ZoneGroupInfo zoneId(List<String> zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  public ZoneGroupInfo addZoneIdItem(String zoneIdItem) {
    this.zoneId.add(zoneIdItem);
    return this;
  }

  /**
   * References of identifiers of \"ZoneInfo\" structures, each of which provides information about a resource zone that belongs to this group.
   * @return zoneId
  **/
  @ApiModelProperty(required = true, value = "References of identifiers of \"ZoneInfo\" structures, each of which provides information about a resource zone that belongs to this group.")
      @NotNull

  @Size(min=1)   public List<String> getZoneId() {
    return zoneId;
  }

  public void setZoneId(List<String> zoneId) {
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
    ZoneGroupInfo zoneGroupInfo = (ZoneGroupInfo) o;
    return Objects.equals(this.zoneId, zoneGroupInfo.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ZoneGroupInfo {\n");
    
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
