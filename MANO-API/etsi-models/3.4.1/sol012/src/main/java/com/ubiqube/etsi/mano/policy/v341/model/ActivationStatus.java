package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ActivationStatus shall comply with the provisions defined in table 5.6.4.3-1. It indicates the activation status of a policy. 
 */
public enum ActivationStatus {
  ACTIVATED("ACTIVATED"),
    DEACTIVATED("DEACTIVATED");

  private String value;

  ActivationStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ActivationStatus fromValue(String text) {
    for (ActivationStatus b : ActivationStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
