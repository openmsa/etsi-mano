package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Subscribed notification
 */
public enum AppInstNotificationType {
  NOT_INSTANTIATED("NOT_INSTANTIATED"),
    STARTED("STARTED"),
    STOPPED("STOPPED");

  private String value;

  AppInstNotificationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppInstNotificationType fromValue(String text) {
    for (AppInstNotificationType b : AppInstNotificationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
