package com.ubiqube.etsi.mano.em.v331.model.vnffm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration FaultyResourceType represents those types of faulty resource. 
 */
public enum FaultyResourceType {
  COMPUTE("COMPUTE"),
    STORAGE("STORAGE"),
    NETWORK("NETWORK");

  private String value;

  FaultyResourceType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static FaultyResourceType fromValue(String text) {
    for (FaultyResourceType b : FaultyResourceType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
