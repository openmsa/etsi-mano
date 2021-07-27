package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration PolicyOperationType shall comply with the provisions defined in table 5.6.4.4-1.  It indicates the type of the policy change. Permitted values: * CREATE_POLICY: The policy is created. * TRANSFER_POLICY: The policy is transferred. * DELETE_POLICY: The policy is deleted. * MODIFY_POLICY: The policy is modified. 
 */
public enum PolicyOperationType {
  CREATE_POLICY("CREATE_POLICY"),
    TRANSFER_POLICY("TRANSFER_POLICY"),
    DELETE_POLICY("DELETE_POLICY"),
    MODIFY_POLICY("MODIFY_POLICY");

  private String value;

  PolicyOperationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PolicyOperationType fromValue(String text) {
    for (PolicyOperationType b : PolicyOperationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
