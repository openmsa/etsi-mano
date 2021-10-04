package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration UsageStateEnumType defines values representing the usage  state of a managed entity.  The UsageStateEnumType shall comply with the provisions:   - IN_USE The managed entity is currently being used.   - NOT_IN_USE The managed entity is currently not being used. 
 */
public enum UsageStateEnumType {
  IN_USE("IN_USE"),
    NOT_IN_USE("NOT_IN_USE");

  private String value;

  UsageStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static UsageStateEnumType fromValue(String text) {
    for (UsageStateEnumType b : UsageStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
