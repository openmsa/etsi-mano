/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides encapsulates information about an MSCS endpoint of the MSCS. It shall comply with the provisions defined in table 6.5.3.83-1. 
 */
@Schema(description = "This type provides encapsulates information about an MSCS endpoint of the MSCS. It shall comply with the provisions defined in table 6.5.3.83-1. ")
@Validated


public class MscsEndpointInfo   {
  @JsonProperty("mscsEndpointId")
  private String mscsEndpointId = null;

  /**
   * Directionality of the data traffic in the context of the terminating MSCS endpoint from WAN’s perspective. Permitted values: - INBOUND: to indicate into the WAN. - OUTBOUND: to indicate from the WAN. - BOTH: to indicate bidirectional data traffic to/from the WAN. 
   */
  public enum DirectionalityEnum {
    INBOUND("INBOUND"),
    
    OUTBOUND("OUTBOUND"),
    
    BOTH("BOTH");

    private String value;

    DirectionalityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DirectionalityEnum fromValue(String text) {
      for (DirectionalityEnum b : DirectionalityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("directionality")
  private DirectionalityEnum directionality = null;

  @JsonProperty("connectivityServiceEndpoinId")
  @Valid
  private List<String> connectivityServiceEndpoinId = new ArrayList<>();

  public MscsEndpointInfo mscsEndpointId(String mscsEndpointId) {
    this.mscsEndpointId = mscsEndpointId;
    return this;
  }

  /**
   * Get mscsEndpointId
   * @return mscsEndpointId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getMscsEndpointId() {
    return mscsEndpointId;
  }

  public void setMscsEndpointId(String mscsEndpointId) {
    this.mscsEndpointId = mscsEndpointId;
  }

  public MscsEndpointInfo directionality(DirectionalityEnum directionality) {
    this.directionality = directionality;
    return this;
  }

  /**
   * Directionality of the data traffic in the context of the terminating MSCS endpoint from WAN’s perspective. Permitted values: - INBOUND: to indicate into the WAN. - OUTBOUND: to indicate from the WAN. - BOTH: to indicate bidirectional data traffic to/from the WAN. 
   * @return directionality
   **/
  @Schema(required = true, description = "Directionality of the data traffic in the context of the terminating MSCS endpoint from WAN’s perspective. Permitted values: - INBOUND: to indicate into the WAN. - OUTBOUND: to indicate from the WAN. - BOTH: to indicate bidirectional data traffic to/from the WAN. ")
      @NotNull

    public DirectionalityEnum getDirectionality() {
    return directionality;
  }

  public void setDirectionality(DirectionalityEnum directionality) {
    this.directionality = directionality;
  }

  public MscsEndpointInfo connectivityServiceEndpoinId(List<String> connectivityServiceEndpoinId) {
    this.connectivityServiceEndpoinId = connectivityServiceEndpoinId;
    return this;
  }

  public MscsEndpointInfo addConnectivityServiceEndpoinIdItem(String connectivityServiceEndpoinIdItem) {
    this.connectivityServiceEndpoinId.add(connectivityServiceEndpoinIdItem);
    return this;
  }

  /**
   * References the connectivity service endpoint configuration information applicable to support the MSCS endpoint. More than one connectivity service endpoint can be referred when endpoints are in LAG mode. 
   * @return connectivityServiceEndpoinId
   **/
  @Schema(required = true, description = "References the connectivity service endpoint configuration information applicable to support the MSCS endpoint. More than one connectivity service endpoint can be referred when endpoints are in LAG mode. ")
      @NotNull

    public List<String> getConnectivityServiceEndpoinId() {
    return connectivityServiceEndpoinId;
  }

  public void setConnectivityServiceEndpoinId(List<String> connectivityServiceEndpoinId) {
    this.connectivityServiceEndpoinId = connectivityServiceEndpoinId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MscsEndpointInfo mscsEndpointInfo = (MscsEndpointInfo) o;
    return Objects.equals(this.mscsEndpointId, mscsEndpointInfo.mscsEndpointId) &&
        Objects.equals(this.directionality, mscsEndpointInfo.directionality) &&
        Objects.equals(this.connectivityServiceEndpoinId, mscsEndpointInfo.connectivityServiceEndpoinId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mscsEndpointId, directionality, connectivityServiceEndpoinId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MscsEndpointInfo {\n");
    
    sb.append("    mscsEndpointId: ").append(toIndentedString(mscsEndpointId)).append("\n");
    sb.append("    directionality: ").append(toIndentedString(directionality)).append("\n");
    sb.append("    connectivityServiceEndpoinId: ").append(toIndentedString(connectivityServiceEndpoinId)).append("\n");
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
