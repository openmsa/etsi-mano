package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Onboarding state of application package
 */
public enum OnboardingState {
  CREATED("CREATED"),
    UPLOADING("UPLOADING"),
    PROCESSING("PROCESSING"),
    ONBOARDED("ONBOARDED");

  private String value;

  OnboardingState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OnboardingState fromValue(String text) {
    for (OnboardingState b : OnboardingState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
