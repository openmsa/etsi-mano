package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. 
 */
public enum WanLayer3ProtocolData {
  VPN("BGP_IP_VPN");

  private String value;

  WanLayer3ProtocolData(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static WanLayer3ProtocolData fromValue(String text) {
    for (WanLayer3ProtocolData b : WanLayer3ProtocolData.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
