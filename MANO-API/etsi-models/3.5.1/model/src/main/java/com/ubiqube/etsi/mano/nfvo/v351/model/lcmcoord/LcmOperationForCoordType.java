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
package com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration LcmOperationForCoordType defines the permitted values to represent  NS lifecycle operation types in NS LCM operation coordination actions.  It shall comply with the provisions defined in table 12.5.4.3-1.  - INSTANTIATE: Represents the \"Instantiate NS\" LCM operation. - SCALE: Represents the \"Scale NS\" LCM operation. - UPDATE: Represents the \"Update NS\" LCM operation. - TERMINATE: Represents the \"Terminate NS\" LCM operation.  - HEAL: Represents the \"Heal NS\" LCM operation. 
 */
public enum LcmOperationForCoordType {
  INSTANTIATE("INSTANTIATE"),
    SCALE("SCALE"),
    UPDATE("UPDATE"),
    TERMINATE("TERMINATE"),
    HEAL("HEAL");

  private String value;

  LcmOperationForCoordType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOperationForCoordType fromValue(String text) {
    for (LcmOperationForCoordType b : LcmOperationForCoordType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
