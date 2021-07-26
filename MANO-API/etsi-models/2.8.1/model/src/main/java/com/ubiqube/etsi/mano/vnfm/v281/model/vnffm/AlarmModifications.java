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
package com.ubiqube.etsi.mano.vnfm.v281.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications for an \&quot;Individual alarm\&quot; resource, i.e. modifications to a resource representation based on the \&quot;Alarm\&quot; data type. The attributes of \&quot;Alarm\&quot; that can be modified are included in the \&quot;AlarmModifications\&quot; data type. 
 */
@ApiModel(description = "This type represents attribute modifications for an \"Individual alarm\" resource, i.e. modifications to a resource representation based on the \"Alarm\" data type. The attributes of \"Alarm\" that can be modified are included in the \"AlarmModifications\" data type. ")
@Validated

public class AlarmModifications   {
  /**
   * New value of the \"ackState\" attribute in \"Alarm\". Permitted values: * ACKNOWLEDGED * UNACKNOWLEDGED 
   */
  public enum AckStateEnum {
    ACKNOWLEDGED("ACKNOWLEDGED"),
    
    UNACKNOWLEDGED("UNACKNOWLEDGED");

    private String value;

    AckStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AckStateEnum fromValue(String text) {
      for (AckStateEnum b : AckStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("ackState")
  private AckStateEnum ackState = null;

  public AlarmModifications ackState(AckStateEnum ackState) {
    this.ackState = ackState;
    return this;
  }

  /**
   * New value of the \"ackState\" attribute in \"Alarm\". Permitted values: * ACKNOWLEDGED * UNACKNOWLEDGED 
   * @return ackState
  **/
  @ApiModelProperty(required = true, value = "New value of the \"ackState\" attribute in \"Alarm\". Permitted values: * ACKNOWLEDGED * UNACKNOWLEDGED ")
  @NotNull


  public AckStateEnum getAckState() {
    return ackState;
  }

  public void setAckState(AckStateEnum ackState) {
    this.ackState = ackState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlarmModifications alarmModifications = (AlarmModifications) o;
    return Objects.equals(this.ackState, alarmModifications.ackState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ackState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlarmModifications {\n");
    
    sb.append("    ackState: ").append(toIndentedString(ackState)).append("\n");
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

