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
package com.ubiqube.etsi.mano.nfvo.v351.model.nsd;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding state of the NSD. CREATED = The NSD information object has been created. UPLOADING = The associated NSD content is being uploaded. PROCESSING = The associated NSD content is being processed, e.g. validation. ONBOARDED = The associated NSD content has been on-boarded. ERROR = There was an error during upload or processing of the NSD content. 
 */
public enum NsdOnboardingStateType {
  CREATED("CREATED"),
    UPLOADING("UPLOADING"),
    PROCESSING("PROCESSING"),
    ONBOARDED("ONBOARDED"),
    ERROR("ERROR");

  private String value;

  NsdOnboardingStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsdOnboardingStateType fromValue(String text) {
    for (NsdOnboardingStateType b : NsdOnboardingStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
