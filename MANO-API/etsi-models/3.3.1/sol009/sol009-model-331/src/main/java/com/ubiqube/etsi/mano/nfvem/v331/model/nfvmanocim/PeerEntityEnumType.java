package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration PeerEntityEnumType defines the permitted values  to represent peer functional entities. it shall complains with  the provisions :  - NFVO The peer functional entity is an NFVO. - VNFM The peer functional entity is a VNFM. - VIM   The peer functional entity is a VIM. - WIM   The peer functional entity is a WIM. - EM   The peer functional entity is an EM. - OSS   The peer functional entity is an OSS/BSS. 
 */
public enum PeerEntityEnumType {
  NFVO("NFVO"),
    VNFM("VNFM"),
    VIM("VIM"),
    WIM("WIM"),
    EM("EM"),
    OSS("OSS");

  private String value;

  PeerEntityEnumType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PeerEntityEnumType fromValue(String text) {
    for (PeerEntityEnumType b : PeerEntityEnumType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
