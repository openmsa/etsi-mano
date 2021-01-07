package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 0 = void
 */
public enum AppInstSelectorType {
  VOID("VOID"),
    APP_IDENTITY("APP_IDENTITY"),
    APP_NAME("APP_NAME"),
    APP_D_ID("APP_D_ID"),
    APP_FROM_PROVIDER("APP_FROM_PROVIDER");

  private String value;

  AppInstSelectorType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppInstSelectorType fromValue(String text) {
    for (AppInstSelectorType b : AppInstSelectorType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
