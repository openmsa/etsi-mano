package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Subscribed notification type.
 */
public enum AppPkgSubscriptionType {
  APPPACKAGEONBOARDING("AppPackageOnBoarding"),
    APPPACAKGEOPERATIONCHANGE("AppPacakgeOperationChange"),
    APPPACKAGEDELETION("AppPackageDeletion");

  private String value;

  AppPkgSubscriptionType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppPkgSubscriptionType fromValue(String text) {
    for (AppPkgSubscriptionType b : AppPkgSubscriptionType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
