package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 'Type of the LCM operation represented by this application instance LCM operation occurrence.'
 */
public enum OperationTypes {
  INSTANTIATE("INSTANTIATE"),
    OPERATE("OPERATE"),
    TERMINATE("TERMINATE");

  private String value;

  OperationTypes(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperationTypes fromValue(String text) {
    for (OperationTypes b : OperationTypes.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
