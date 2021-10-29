package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * STARTED: The VNF instance is up and running. STOPPED: The VNF instance has been shut down. 
 */
public enum VnfOperationalStateType {
  STARTED("STARTED"),
    STOPPED("STOPPED");

  private String value;

  VnfOperationalStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static VnfOperationalStateType fromValue(String text) {
    for (VnfOperationalStateType b : VnfOperationalStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
