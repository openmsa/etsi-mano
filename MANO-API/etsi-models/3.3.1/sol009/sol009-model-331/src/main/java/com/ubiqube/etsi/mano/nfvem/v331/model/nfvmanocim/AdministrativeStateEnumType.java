package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration AdministrativeStateEnumType defines values representing the administrative state of a managed entity. The AdministrativeStateEnumType shall comply with the provisions:   - LOCKED The managed entity is administratively prohibited to be used.   - UNLOCKED The managed entity is administratively allowed to be used.   - LOCKING The managed entity is in the transition to be locked. 
 */
public enum AdministrativeStateEnumType {
  LOCKED("LOCKED"),
    UNLOCKED("UNLOCKED"),
    LOCKING("LOCKING");

  private String value;

  AdministrativeStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AdministrativeStateEnumType fromValue(String text) {
    for (AdministrativeStateEnumType b : AdministrativeStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
