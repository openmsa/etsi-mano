package com.ubiqube.etsi.mano.vnfm.v351.model.vnf;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * CREATED: The \"Individual VNF package\" resource has been created. UPLOADING: The associated VNF package content is being uploaded. PROCESSING: The associated VNF package content is being processed, e.g.,             validation. ONBOARDED: The associated VNF package content has been on-boarded successfully. ERROR: There was an error during upload of the VNF package content or external         artifacts, or during VNF package processing. 
 */
public enum PackageOnboardingStateType {
  CREATED("CREATED"),
    UPLOADING("UPLOADING"),
    PROCESSING("PROCESSING"),
    ONBOARDED("ONBOARDED"),
    ERROR("ERROR");

  private String value;

  PackageOnboardingStateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PackageOnboardingStateType fromValue(String text) {
    for (PackageOnboardingStateType b : PackageOnboardingStateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
