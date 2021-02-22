package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsdUsageStateType shall comply with the provisions defined in Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the resource.IN_USE = The resource is in use.NOT_IN_USE = The resource is not-in-use. 
 */
public enum NsdUsageStateType {
  IN_USE("IN_USE"),
    NOT_IN_USE("NOT_IN_USE");

  private String value;

  NsdUsageStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsdUsageStateType fromValue(String text) {
    for (NsdUsageStateType b : NsdUsageStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
