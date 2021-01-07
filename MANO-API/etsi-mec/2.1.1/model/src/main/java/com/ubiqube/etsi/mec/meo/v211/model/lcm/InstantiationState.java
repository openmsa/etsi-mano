package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Instantiation state of the application instance
 */
public enum InstantiationState {
  NOT_INSTANTIATED("NOT_INSTANTIATED"),
    INSTANTIATED("INSTANTIATED");

  private String value;

  InstantiationState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static InstantiationState fromValue(String text) {
    for (InstantiationState b : InstantiationState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
