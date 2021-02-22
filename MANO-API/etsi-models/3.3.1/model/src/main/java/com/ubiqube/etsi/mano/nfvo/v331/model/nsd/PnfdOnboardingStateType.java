package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration PnfdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding state of the individual PNF descriptor resource. CREATED = The PNF descriptor resource has been created. UPLOADING = The associated PNFD content is being uploaded. PROCESSING = The associated PNFD content is being processed, e.g. validation. ONBOARDED = The associated PNFD content has been on-boarded. ERROR = There was an error during upload or processing of the associated PNFD content. 
 */
public enum PnfdOnboardingStateType {
  CREATED("CREATED"),
    UPLOADING("UPLOADING"),
    PROCESSING("PROCESSING"),
    ONBOARDING("ONBOARDING"),
    ERROR("ERROR");

  private String value;

  PnfdOnboardingStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PnfdOnboardingStateType fromValue(String text) {
    for (PnfdOnboardingStateType b : PnfdOnboardingStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
