package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. 
 */
public enum NsLcmOpType {
  INSTANTIATE("INSTANTIATE"),
    SCALE("SCALE"),
    UPDATE("UPDATE"),
    TERMINATE("TERMINATE"),
    HEAL("HEAL");

  private String value;

  NsLcmOpType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsLcmOpType fromValue(String text) {
    for (NsLcmOpType b : NsLcmOpType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
