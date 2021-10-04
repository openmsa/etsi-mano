package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ChangeOperationalStateEnumType defines permitted values  for the change state operation.  The ChangeOperationalStateEnumType shall comply with the provisions:   - START To start the managed entity.   - STOP To stop the managed entity.   - RESTART To stop and start again the managed entity. 
 */
public enum ChangeOperationalStateEnumType {
  START("START"),
    STOP("STOP"),
    RESTART("RESTART");

  private String value;

  ChangeOperationalStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ChangeOperationalStateEnumType fromValue(String text) {
    for (ChangeOperationalStateEnumType b : ChangeOperationalStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
