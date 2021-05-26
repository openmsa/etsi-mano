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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. 
 */
public enum NsLcmOpType {
  
  INSTANTIATE("INSTANTIATE"),
  
  SCALE("SCALE"),
  
  UPDATE("UPDATE"),
  
  TERMINATE("TERMINATE"),
  
  HEAL("HEAL");

  private String value;

  NsLcmOpType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsLcmOpType fromValue(String text) {
    for (NsLcmOpType b : NsLcmOpType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

