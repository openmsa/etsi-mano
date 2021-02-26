package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration LcmOpOccNotificationVerbosityType provides values to control the verbosity of LCM operation occurrence notifications. * FULL: This signals a full notification which contains all change details. * SHORT: This signals a short notification which omits large-volume change details to reduce the size of data to          be sent via the notification mechanism. 
 */
public enum LcmOpOccNotificationVerbosityType {
  FULL("FULL"),
    SHORT("SHORT");

  private String value;

  LcmOpOccNotificationVerbosityType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOpOccNotificationVerbosityType fromValue(String text) {
    for (LcmOpOccNotificationVerbosityType b : LcmOpOccNotificationVerbosityType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
