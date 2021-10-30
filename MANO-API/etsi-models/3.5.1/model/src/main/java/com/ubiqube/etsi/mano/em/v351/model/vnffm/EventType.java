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
package com.ubiqube.etsi.mano.em.v351.model.vnffm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration EventType represents those types of events that trigger an alarm. * COMMUNICATIONS_ALARM: An alarm of this type is associated with the   procedure and/or process required conveying information from one point   to another (ITU-T Recommendation X.733). * PROCESSING_ERROR_ALARM: An alarm of this type is associated with a   software or processing fault (ITU-T Recommendation X.733). * ENVIRONMENTAL_ALARM: An alarm of this type is associated with a   condition related to an enclosure in which the equipment resides   (ITU-T Recommendation X.733). * QOS_ALARM: An alarm of this type is associated with degradation in the   quality of a service (ITU-T Recommendation X.733). * EQUIPMENT_ALARM: An alarm of this type is associated with an equipment   fault (ITU-T Recommendation X.733). 
 */
public enum EventType {
  COMMUNICATIONS_ALARM("COMMUNICATIONS_ALARM"),
    PROCESSING_ERROR_ALARM("PROCESSING_ERROR_ALARM"),
    ENVIRONMENTAL_ALARM("ENVIRONMENTAL_ALARM"),
    QOS_ALARM("QOS_ALARM"),
    EQUIPMENT_ALARM("EQUIPMENT_ALARM");

  private String value;

  EventType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EventType fromValue(String text) {
    for (EventType b : EventType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
