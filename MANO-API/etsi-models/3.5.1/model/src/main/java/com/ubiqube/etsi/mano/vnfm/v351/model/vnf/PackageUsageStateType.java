package com.ubiqube.etsi.mano.vnfm.v351.model.vnf;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * - IN_USE: \"Individual VNF instance\" resources created from this VNF package exist. - NOT_IN_USE: No \"Individual VNF instance\" resource created from this VNF package exists. 
 */
public enum PackageUsageStateType {
  IN_USE("IN_USE"),
    NOT_IN_USE("NOT_IN_USE");

  private String value;

  PackageUsageStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PackageUsageStateType fromValue(String text) {
    for (PackageUsageStateType b : PackageUsageStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
