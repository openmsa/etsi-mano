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
package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added and deleted external link ports (link ports attached to external virtual links). 
 */
@Schema(description = "This type provides information about added and deleted external link ports (link ports attached to external virtual links). ")
@Validated


public class AffectedExtLinkPort   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Signals the type of change. Permitted values: - ADDED - REMOVED 
   */
  public enum ChangeTypeEnum {
    ADDED("ADDED"),
    
    REMOVED("REMOVED");

    private String value;

    ChangeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeTypeEnum fromValue(String text) {
      for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("changeType")
  private ChangeTypeEnum changeType = null;

  @JsonProperty("extCpInstanceId")
  private String extCpInstanceId = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  public AffectedExtLinkPort id(String id) {
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

  public AffectedExtLinkPort changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: - ADDED - REMOVED 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change. Permitted values: - ADDED - REMOVED ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedExtLinkPort extCpInstanceId(String extCpInstanceId) {
    this.extCpInstanceId = extCpInstanceId;
    return this;
  }

  /**
   * Get extCpInstanceId
   * @return extCpInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getExtCpInstanceId() {
    return extCpInstanceId;
  }

  public void setExtCpInstanceId(String extCpInstanceId) {
    this.extCpInstanceId = extCpInstanceId;
  }

  public AffectedExtLinkPort resourceHandle(ResourceHandle resourceHandle) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedExtLinkPort affectedExtLinkPort = (AffectedExtLinkPort) o;
    return Objects.equals(this.id, affectedExtLinkPort.id) &&
        Objects.equals(this.changeType, affectedExtLinkPort.changeType) &&
        Objects.equals(this.extCpInstanceId, affectedExtLinkPort.extCpInstanceId) &&
        Objects.equals(this.resourceHandle, affectedExtLinkPort.resourceHandle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, changeType, extCpInstanceId, resourceHandle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedExtLinkPort {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    extCpInstanceId: ").append(toIndentedString(extCpInstanceId)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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
