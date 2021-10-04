package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ChangeStateOpOccStateEnumType defines permitted values for the change state operation. It shall comply with the provisions :   - PROCESSING The change state operation is currently in execution.   - COMPLETED The change state operation has been completed successfully.   - FAILED The change state operation has failed. 
 */
public enum ChangeStateOpOccStateEnumType {
  PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

  private String value;

  ChangeStateOpOccStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ChangeStateOpOccStateEnumType fromValue(String text) {
    for (ChangeStateOpOccStateEnumType b : ChangeStateOpOccStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
