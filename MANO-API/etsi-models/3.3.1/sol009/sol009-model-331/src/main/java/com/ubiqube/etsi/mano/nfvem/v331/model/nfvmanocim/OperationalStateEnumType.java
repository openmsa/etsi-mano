package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration OperationalStateEnumType defines values representing the operational state of an NFV-MANO functional application type of managed entity. The OperationalStateEnumType shall comply with the provisions:   - STARTED The managed entity is operational.   - STOPPED The managed entity is not operational.   - STOPPING The managed entity is in the transition to stop.   - STARTING The managed entity is in the transition to start and become operational.   - RESTARTING The managed entity is in the transition to stop and start again. 
 */
public enum OperationalStateEnumType {
  STARTED("STARTED"),
    STOPPED("STOPPED"),
    STOPPING("STOPPING"),
    STARTING("STARTING"),
    RESTARTING("RESTARTING");

  private String value;

  OperationalStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperationalStateEnumType fromValue(String text) {
    for (OperationalStateEnumType b : OperationalStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
