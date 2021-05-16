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
package com.ubiqube.etsi.mano.em.v271.model.vnffm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Indicates the relative level of urgency for operator attention. * CRITICAL: The Critical severity level indicates that a service   affecting condition has occurred and an immediate corrective action   is required. Such a severity can be reported, for example, when a   managed object becomes totally out of service and its capability needs   to be restored (ITU-T Recommendation X.733). * MAJOR: The Major severity level indicates that a service affecting   condition has developed and an urgent corrective action is required.   Such a severity can be reported, for example, when there is a severe   degradation in the capability of the managed object and its full   capability needs to be restored (ITU-T Recommendation X.733). * MINOR: The Minor severity level indicates the existence of a   non-service affecting fault condition and that corrective action   should be taken in order to prevent a more serious (for example,   service affecting) fault. Such a severity can be reported, for   example, when the detected alarm condition is not currently degrading   the capacity of the managed object (ITU-T Recommendation X.733). * WARNING: The Warning severity level indicates the detection of a   potential or impending service affecting fault, before any significant   effects have been felt. Action should be taken to further diagnose (if   necessary) and correct the problem in order to prevent it from   becoming a more serious service affecting fault (ITU-T Recommendation   X.733). * INDETERMINATE: The Indeterminate severity level indicates that the   severity level cannot be determined (ITU-T Recommendation X.733). * CLEARED: The Cleared severity level indicates the clearing of one or   more previously reported alarms. This alarm clears all alarms for this   managed object that have the same Alarm type, Probable cause and   Specific problems (if given) (ITU-T Recommendation X.733). 
 */
public enum PerceivedSeverityType {
  
  CRITICAL("CRITICAL"),
  
  MAJOR("MAJOR"),
  
  MINOR("MINOR"),
  
  WARNING("WARNING"),
  
  INDETERMINATE("INDETERMINATE"),
  
  CLEARED("CLEARED");

  private String value;

  PerceivedSeverityType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PerceivedSeverityType fromValue(String text) {
    for (PerceivedSeverityType b : PerceivedSeverityType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

