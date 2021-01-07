package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Match particular notification types.
 */
public enum NotificationTypes {
  APPLCMOPERATIONOCCURRENCENOTIFICATION("AppLcmOperationOccurrenceNotification");

  private String value;

  NotificationTypes(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NotificationTypes fromValue(String text) {
    for (NotificationTypes b : NotificationTypes.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
