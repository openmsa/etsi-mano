package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration NfviCapacityResourceTypeEnumeration shall comply with the provisions in table 10.5.4.3-1. * VR_COMPUTE: For virtualised compute resource. * VR_NETWORK: For virtualised network resource. * VR_STORAGE: For virtualised storage resource. * HOST_COMPUTE: For host compute resource. 
 */
public enum NfviCapacityResourceTypeEnumeration {
  VR_COMPUTE("VR_COMPUTE"),
    VR_NETWORK("VR_NETWORK"),
    VR_STORAGE("VR_STORAGE"),
    HOST_COMPUTE("HOST_COMPUTE");

  private String value;

  NfviCapacityResourceTypeEnumeration(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NfviCapacityResourceTypeEnumeration fromValue(String text) {
    for (NfviCapacityResourceTypeEnumeration b : NfviCapacityResourceTypeEnumeration.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
