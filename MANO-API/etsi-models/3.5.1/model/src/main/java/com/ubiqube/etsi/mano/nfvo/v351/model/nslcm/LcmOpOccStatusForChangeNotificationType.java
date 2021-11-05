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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration LcmOpNameForChangeNotificationType represents the name of the lifecycle operation that impacts the NS component and trigger an NS change notification. It shall comply with the provisions defined in table 6.5.4.6-1. Value                   | Description VNF_INSTANTIATE         Represents the \"Instantiate VNF\" LCM operation. VNF_SCALE                 Represents the \"Scale VNF\" LCM operation. VNF_SCALE_TO_LEVEL     Represents the \"Scale VNF to Level\" LCM operation. VNF_CHANGE_FLAVOUR     Represents the \"Change VNF Flavour\" LCM operation. VNF_TERMINATE             Represents the \"Terminate VNF\" LCM operation. VNF_HEAL                 Represents the \"Heal VNF\" LCM operation. VNF_OPERATE             Represents the \"Operate VNF\" LCM operation. VNF_CHANGE_EXT_CONN     Represents the \"Change external VNF connectivity\" LCM operation. VNF_MODIFY_INFO         Represents the \"Modify VNF Information\" LCM operation. VNF_CREATE_SNAPSHOT     Represents the \"Create VNF Snapshot\" LCM operation. VNF_REVERT_TO_SNAPSHOT Represents the \"Revert To VNF Snapshot\" LCM operation. VNF_CHANGE_VNFPKG         Represents the \"Change current VNF package\" LCM operation. NS_INSTANTIATE         Represents the \"Instantiate NS\" LCM operation. NS_SCALE                 Represents the \"Scale NS\" LCM operation. NS_UPDATE                 Represents the \"Update NS\" LCM operation. NS_TERMINATE             Represents the \"Terminate NS\" LCM operation. NS_HEAL                 Represents the \"Heal NS\" LCM operation. 
 */
public enum LcmOpOccStatusForChangeNotificationType {
  VNF_INSTANTIATE("VNF_INSTANTIATE"),
    VNF_SCALE("VNF_SCALE"),
    VNF_SCALE_TO_LEVEL("VNF_SCALE_TO_LEVEL"),
    VNF_CHANGE_FLAVOUR("VNF_CHANGE_FLAVOUR"),
    VNF_TERMINATE("VNF_TERMINATE"),
    VNF_HEAL("VNF_HEAL"),
    VNF_OPERATE("VNF_OPERATE"),
    VNF_CHANGE_EXT_CONN("VNF_CHANGE_EXT_CONN"),
    VNF_MODIFY_INFO("VNF_MODIFY_INFO"),
    VNF_CREATE_SNAPSHOT("VNF_CREATE_SNAPSHOT"),
    VNF_REVERT_TO_SNAPSHOT("VNF_REVERT_TO_SNAPSHOT"),
    VNF_CHANGE_VNFPKG("VNF_CHANGE_VNFPKG"),
    NS_INSTANTIATE("NS_INSTANTIATE"),
    NS_SCALE("NS_SCALE"),
    NS_UPDATE("NS_UPDATE"),
    NS_TERMINATE("NS_TERMINATE"),
    NS_HEAL("NS_HEAL");

  private String value;

  LcmOpOccStatusForChangeNotificationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOpOccStatusForChangeNotificationType fromValue(String text) {
    for (LcmOpOccStatusForChangeNotificationType b : LcmOpOccStatusForChangeNotificationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
