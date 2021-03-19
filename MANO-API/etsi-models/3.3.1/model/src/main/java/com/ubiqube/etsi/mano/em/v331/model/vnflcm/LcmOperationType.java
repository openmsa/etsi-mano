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
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate VNF\" LCM operation.    SCALE | Represents the \"Scale VNF\" LCM operation. SCALE_TO_LEVEL | Represents the \"Scale VNF to Level\" LCM operation. CHANGE_FLAVOUR | Represents the \"Change VNF Flavour\" LCM operation. TERMINATE | Represents the \"Terminate VNF\" LCM operation. HEAL | Represents the \"Heal VNF\" LCM operation. OPERATE | Represents the \"Operate VNF\" LCM operation. CHANGE_EXT_CONN | Represents the \"Change external VNF connectivity\" LCM operation. MODIFY_INFO | Represents the \"Modify VNF Information\" LCM operation. CREATE_SNAPSHOT | Represents the \"Create VNF Snapshot\" LCM operation. REVERT_TO_SNAPSHOT | Represents the “Revert-To VNF Snapshot\" LCM operation. CHANGE_VNFPKG | Represents the \"Change current VNF package\" LCM operation. 
 */
public enum LcmOperationType {
  INSTANTIATE("INSTANTIATE"),
    SCALE("SCALE"),
    SCALE_TO_LEVEL("SCALE_TO_LEVEL"),
    CHANGE_FLAVOUR("CHANGE_FLAVOUR"),
    TERMINATE("TERMINATE"),
    HEAL("HEAL"),
    OPERATE("OPERATE"),
    CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
    MODIFY_INFO("MODIFY_INFO"),
    CREATE_SNAPSHOT("CREATE_SNAPSHOT"),
    REVERT_TO_SNAPSHOT("REVERT_TO_SNAPSHOT"),
    CHANGE_VNFPKG("CHANGE_VNFPKG");

  private String value;

  LcmOperationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOperationType fromValue(String text) {
    for (LcmOperationType b : LcmOperationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
