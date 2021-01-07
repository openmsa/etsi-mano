package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 'Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation.'
 */
public enum OperationParams {
  INSTANTIATE("INSTANTIATE"),
    OPERATE("OPERATE"),
    TERMINATE("TERMINATE");

  private String value;

  OperationParams(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperationParams fromValue(String text) {
    for (OperationParams b : OperationParams.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
