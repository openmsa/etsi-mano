package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * STARTING: The LCM operation is starting. PROCESSING: The LCM operation is currently in execution. COMPLETED: The LCM operation has been completed successfully. FAILED_TEMP: The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED: The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won't succeed. ROLLING_BACK: The LCM operation is currently being rolled back. ROLLED_BACK: The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. 
 */
public enum LcmOperationStateType {
  STARTING("STARTING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    FAILED_TEMP("FAILED_TEMP"),
    FAILED("FAILED"),
    ROLLING_BACK("ROLLING_BACK"),
    ROLLED_BACK("ROLLED_BACK");

  private String value;

  LcmOperationStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOperationStateType fromValue(String text) {
    for (LcmOperationStateType b : LcmOperationStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
