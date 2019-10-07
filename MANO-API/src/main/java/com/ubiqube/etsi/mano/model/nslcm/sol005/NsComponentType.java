package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NsComponentType represents the NS component type. It shall comply with the provisions defined in Table 6.5.4.5-1. Value | Description ------|------------ VNF | Represents the impacted NS component is a VNF. PNF | Represents the impacted NS component is a PNF. NS | Represents the impacted NS component is a nested NS. 
 */
public enum NsComponentType {
  
  VNF("VNF"),
  
  PNF("PNF"),
  
  NS("NS");

  private String value;

  NsComponentType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NsComponentType fromValue(String text) {
    for (NsComponentType b : NsComponentType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

