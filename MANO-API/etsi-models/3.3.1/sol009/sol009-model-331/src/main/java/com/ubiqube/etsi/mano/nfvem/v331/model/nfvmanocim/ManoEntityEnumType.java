package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration ManoEntityEnumType defines the permitted values to  represent NFV-MANO functional entities. It shall comply with  the provisions :   - NFVO The NFV-MANO functional entity is an NFVO.   - VNFM The NFV-MANO functional entity is a VNFM.   - VIM The NFV-MANO functional entity is a VIM. 
 */
public enum ManoEntityEnumType {
  NFVO("NFVO"),
    VNFM("VNFM"),
    VIM("VIM");

  private String value;

  ManoEntityEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ManoEntityEnumType fromValue(String text) {
    for (ManoEntityEnumType b : ManoEntityEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
