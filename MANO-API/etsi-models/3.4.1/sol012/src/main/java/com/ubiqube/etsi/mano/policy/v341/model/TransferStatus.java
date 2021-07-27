package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration TransferStatus shall comply with the provisions defined in table 5.6.4.4-1. It indicates the transfer status of a policy 
 */
public enum TransferStatus {
  CREATED("CREATED"),
    TRANSFERRED("TRANSFERRED");

  private String value;

  TransferStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TransferStatus fromValue(String text) {
    for (TransferStatus b : TransferStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
