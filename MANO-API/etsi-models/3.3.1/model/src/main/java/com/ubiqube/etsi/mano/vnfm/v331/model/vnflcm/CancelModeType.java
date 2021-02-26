package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Cancellation mode. GRACEFUL: If the VNF LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\" state, the VNFM shall not start any new resource management operation and shall wait for the ongoing resource management operations in the underlying system, typically the VIM, to finish execution or to time out. After that, the VNFM shall put the operation occurrence into the FAILED_TEMP state. If the VNF LCM operation occurrence is in \"STARTING\" state, the VNFM shall not start any resource management operation and shall wait for the granting request to finish execution or time out. After that, the VNFM shall put the operation occurrence into the ROLLED_BACK state. FORCEFUL: If the VNF LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\" state, the VNFM shall not start any new resource management operation, shall cancel the ongoing resource management operations in the underlying system, typically the VIM, and shall wait for the cancellation to finish or to time out. After that, the VNFM shall put the operation occurrence into the FAILED_TEMP state. If the VNF LCM operation occurrence is in \"STARTING\" state, the VNFM shall not start any resource management operation and put the operation occurrence into the ROLLED_BACK state. 
 */
public enum CancelModeType {
  GRACEFUL("GRACEFUL"),
    FORCEFUL("FORCEFUL");

  private String value;

  CancelModeType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CancelModeType fromValue(String text) {
    for (CancelModeType b : CancelModeType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
