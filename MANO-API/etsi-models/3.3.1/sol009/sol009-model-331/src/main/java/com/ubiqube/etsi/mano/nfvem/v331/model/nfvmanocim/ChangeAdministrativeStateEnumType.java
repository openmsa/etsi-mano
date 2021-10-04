package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ChangeAdministrativeStateEnumType defines permitted values  for the change of administrative state operation.  The ChangeAdministrativeStateEnumType shall comply with the provisions:   - LOCK To lock the managed entity.   - UNLOCK To unlock the managed entity. 
 */
public enum ChangeAdministrativeStateEnumType {
  LOCK("LOCK"),
    UNLOCK("UNLOCK");

  private String value;

  ChangeAdministrativeStateEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ChangeAdministrativeStateEnumType fromValue(String text) {
    for (ChangeAdministrativeStateEnumType b : ChangeAdministrativeStateEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
