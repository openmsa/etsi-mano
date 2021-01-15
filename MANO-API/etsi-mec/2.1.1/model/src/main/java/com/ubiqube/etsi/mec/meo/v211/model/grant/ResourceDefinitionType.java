package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of the resource definition referenced.
 */
public enum ResourceDefinitionType {
  COMPUTE("COMPUTE"),
    VL("VL"),
    STORAGE("STORAGE"),
    LINKPORT("LINKPORT");

  private String value;

  ResourceDefinitionType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ResourceDefinitionType fromValue(String text) {
    for (ResourceDefinitionType b : ResourceDefinitionType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
