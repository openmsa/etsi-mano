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
package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.NfviCapacityInfoPerZone;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.NfviCapacityMeasurement;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.TimeInterval;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type defines the format of the NFVI capacity information. The type shall comply with the provisions defined in Table 10.5.2.4-1. 
 */
@Schema(description = "This type defines the format of the NFVI capacity information. The type shall comply with the provisions defined in Table 10.5.2.4-1. ")
@Validated


public class NfviCapacityInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("capacityInfoPerZone")
  @Valid
  private List<NfviCapacityInfoPerZone> capacityInfoPerZone = new ArrayList<>();

  @JsonProperty("totalCapacityInfo")
  private NfviCapacityMeasurement totalCapacityInfo = null;

  @JsonProperty("timeInterval")
  private TimeInterval timeInterval = null;

  public NfviCapacityInfo id(String id) {
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

  public NfviCapacityInfo vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public NfviCapacityInfo capacityInfoPerZone(List<NfviCapacityInfoPerZone> capacityInfoPerZone) {
    this.capacityInfoPerZone = capacityInfoPerZone;
    return this;
  }

  public NfviCapacityInfo addCapacityInfoPerZoneItem(NfviCapacityInfoPerZone capacityInfoPerZoneItem) {
    this.capacityInfoPerZone.add(capacityInfoPerZoneItem);
    return this;
  }

  /**
   * Capacity information on a per resource zone basis under control by the associated VIM. 
   * @return capacityInfoPerZone
   **/
  @Schema(required = true, description = "Capacity information on a per resource zone basis under control by the associated VIM. ")
      @NotNull
    @Valid
    public List<NfviCapacityInfoPerZone> getCapacityInfoPerZone() {
    return capacityInfoPerZone;
  }

  public void setCapacityInfoPerZone(List<NfviCapacityInfoPerZone> capacityInfoPerZone) {
    this.capacityInfoPerZone = capacityInfoPerZone;
  }

  public NfviCapacityInfo totalCapacityInfo(NfviCapacityMeasurement totalCapacityInfo) {
    this.totalCapacityInfo = totalCapacityInfo;
    return this;
  }

  /**
   * Get totalCapacityInfo
   * @return totalCapacityInfo
   **/
  @Schema(description = "")
  
    @Valid
    public NfviCapacityMeasurement getTotalCapacityInfo() {
    return totalCapacityInfo;
  }

  public void setTotalCapacityInfo(NfviCapacityMeasurement totalCapacityInfo) {
    this.totalCapacityInfo = totalCapacityInfo;
  }

  public NfviCapacityInfo timeInterval(TimeInterval timeInterval) {
    this.timeInterval = timeInterval;
    return this;
  }

  /**
   * Get timeInterval
   * @return timeInterval
   **/
  @Schema(description = "")
  
    @Valid
    public TimeInterval getTimeInterval() {
    return timeInterval;
  }

  public void setTimeInterval(TimeInterval timeInterval) {
    this.timeInterval = timeInterval;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfviCapacityInfo nfviCapacityInfo = (NfviCapacityInfo) o;
    return Objects.equals(this.id, nfviCapacityInfo.id) &&
        Objects.equals(this.vimId, nfviCapacityInfo.vimId) &&
        Objects.equals(this.capacityInfoPerZone, nfviCapacityInfo.capacityInfoPerZone) &&
        Objects.equals(this.totalCapacityInfo, nfviCapacityInfo.totalCapacityInfo) &&
        Objects.equals(this.timeInterval, nfviCapacityInfo.timeInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vimId, capacityInfoPerZone, totalCapacityInfo, timeInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfviCapacityInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    capacityInfoPerZone: ").append(toIndentedString(capacityInfoPerZone)).append("\n");
    sb.append("    totalCapacityInfo: ").append(toIndentedString(totalCapacityInfo)).append("\n");
    sb.append("    timeInterval: ").append(toIndentedString(timeInterval)).append("\n");
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
