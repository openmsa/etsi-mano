package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled. DISABLED = The operational state of the resource is disabled. 
 */
public enum NsdOperationalStateType {
  ENABLED("ENABLED"),
    DISABLED("DISABLED");

  private String value;

  NsdOperationalStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsdOperationalStateType fromValue(String text) {
    for (NsdOperationalStateType b : NsdOperationalStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
