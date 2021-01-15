package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Discriminator for the different notification types
 */
public enum AppPkgNotificationType {
  APPPACKAGEONBOARDED("AppPackageOnBoarded"),
    APPPACAKGEENABLED("AppPacakgeEnabled"),
    APPPACAKGEDISABLED("AppPacakgeDisabled"),
    APPPACKAGEDELETED("AppPackageDeleted");

  private String value;

  AppPkgNotificationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppPkgNotificationType fromValue(String text) {
    for (AppPkgNotificationType b : AppPkgNotificationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
