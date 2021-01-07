package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Only send notifications for application instances that are in one of the states listed in this attribute. If this attribute is absent, match all states.
 */
public enum AppInstanceState {
  NOT_INSTANTIATED("NOT_INSTANTIATED"),
    STARTED("STARTED"),
    STOPPED("STOPPED");

  private String value;

  AppInstanceState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppInstanceState fromValue(String text) {
    for (AppInstanceState b : AppInstanceState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
