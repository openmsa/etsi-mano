package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Discriminator for the different notification types
 */
public enum AppLcmOpOccNotificationType {
  STARTING("STARTING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

  private String value;

  AppLcmOpOccNotificationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppLcmOpOccNotificationType fromValue(String text) {
    for (AppLcmOpOccNotificationType b : AppLcmOpOccNotificationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
