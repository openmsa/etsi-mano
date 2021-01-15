package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The type of the IP addresses.
 */
public enum IpAddressType {
  IPV4("IPV4"),
    IPV6("IPV6");

  private String value;

  IpAddressType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static IpAddressType fromValue(String text) {
    for (IpAddressType b : IpAddressType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
