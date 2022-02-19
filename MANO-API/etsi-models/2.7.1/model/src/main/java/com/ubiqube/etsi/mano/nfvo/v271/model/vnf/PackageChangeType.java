package com.ubiqube.etsi.mano.nfvo.v271.model.vnf;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration PackageChangeType shall comply with the provisions defined in Table 9.5.4.6-1. Permitted Values:  - OP_STATE_CHANGE: The \"operationalState\" attribute has been changed. - PKG_DELETE: The VNF package has been deleted. 
 */
public enum PackageChangeType {
  OP_STATE_CHANGE("OP_STATE_CHANGE"),
    PKG_DELETE("PKG_DELETE");

  private String value;

  PackageChangeType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PackageChangeType fromValue(String text) {
    for (PackageChangeType b : PackageChangeType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
