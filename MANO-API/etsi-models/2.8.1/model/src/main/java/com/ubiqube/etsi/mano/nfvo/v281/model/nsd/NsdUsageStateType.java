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
package com.ubiqube.etsi.mano.nfvo.v281.model.nsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdUsageStateType shall comply with the provisions defined in Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the resource.IN_USE = The resource is in use.NOT_IN_USE = The resource is not-in-use. 
 */
public enum NsdUsageStateType {
  
  IN_USE("IN_USE"),
  
  NOT_IN_USE("NOT_IN_USE");

  private String value;

  NsdUsageStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsdUsageStateType fromValue(String text) {
    for (NsdUsageStateType b : NsdUsageStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

