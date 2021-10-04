package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ChangeStateOpOccStateEnumType defines permitted values  for the change state operation. It shall comply with the provisions:   - GRACEFUL To stop the managed entity immediately after accepting the request.   - FORCEFUL To stop the managed entity attempting to gracefully discharge the entity from service. 
 */
public enum StopEnumType {
  GRACEFUL("GRACEFUL"),
    FORCEFUL("FORCEFUL");

  private String value;

  StopEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StopEnumType fromValue(String text) {
    for (StopEnumType b : StopEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
