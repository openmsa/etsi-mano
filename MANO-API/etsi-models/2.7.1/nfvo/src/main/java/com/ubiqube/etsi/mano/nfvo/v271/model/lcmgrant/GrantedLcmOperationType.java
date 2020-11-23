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
package com.ubiqube.etsi.mano.nfvo.v271.model.lcmgrant;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration GrantedLcmOperationType defines the permitted values  to represent VNF lifecycle operation types in grant requests. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate VNF\" LCM operation. SCALE | Represents the \"Scale VNF\" LCM operation. SCALE_TO_LEVEL | Represents the \"Scale VNF to Level\" LCM operation. CHANGE_FLAVOUR | Represents the \"Change VNF Flavour\" LCM operation. TERMINATE | Represents the \"Terminate VNF\" LCM operation. HEAL | Represents the \"Heal VNF\" LCM operation. OPERATE | Represents the \"Operate VNF\" LCM operation. CHANGE_EXT_CONN | Represents the \"Change external VNF connectivity\" LCM operation.      
 */
public enum GrantedLcmOperationType {
  
  INSTANTIATE("INSTANTIATE"),
  
  SCALE("SCALE"),
  
  SCALE_TO_LEVEL("SCALE_TO_LEVEL"),
  
  CHANGE_FLAVOUR("CHANGE_FLAVOUR"),
  
  TERMINATE("TERMINATE"),
  
  HEAL("HEAL"),
  
  OPERATE("OPERATE"),
  
  CHANGE_EXT_CONN("CHANGE_EXT_CONN");

  private String value;

  GrantedLcmOperationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static GrantedLcmOperationType fromValue(String text) {
    for (GrantedLcmOperationType b : GrantedLcmOperationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

