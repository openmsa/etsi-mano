package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration LcmCoordResultType defines the permitted values to represent the result of executing an LCM coordination action. The coordination result also implies the action to be performed by the VNFM as the follow-up to this coordination. Value | Description ------|------------ CONTINUE | The related LCM operation shall be continued, staying in the state \"PROCESSING\". ABORT | The related LCM operation shall be aborted by transitioning into the state \"FAILED_TEMP\". CANCELLED | The coordination action has been cancelled upon request of the API consumer, i.e. the VNFM. The related LCM operation shall be aborted by transitioning into the state \"FAILED_TEMP\". 
 */
public enum LcmCoordResultType {
  CONTINUE("CONTINUE"),
    ABORT("ABORT"),
    CANCELLED("CANCELLED");

  private String value;

  LcmCoordResultType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmCoordResultType fromValue(String text) {
    for (LcmCoordResultType b : LcmCoordResultType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
