package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration InterfaceOperationalStateEnumType defines values representing  the operational state of an NFV-MANO service interface type of managed entity.  The InterfaceOperationalStateEnumType shall comply with the provisions :    - STARTED The managed entity is operational.    - STOPPED The managed entity is not operational.    - STOPPING The managed entity is in the transition to stop.    - STARTING The managed entity is in the transition to start and become operational. 
 */
public enum InterfaceOperationalStateEnumType {
  STARTED("STARTED"),
    STOPPED("STOPPED"),
    STOPPING("STOPPING"),
    STARTING("STARTING");

  private String value;

  InterfaceOperationalStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static InterfaceOperationalStateEnumType fromValue(String text) {
    for (InterfaceOperationalStateEnumType b : InterfaceOperationalStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
