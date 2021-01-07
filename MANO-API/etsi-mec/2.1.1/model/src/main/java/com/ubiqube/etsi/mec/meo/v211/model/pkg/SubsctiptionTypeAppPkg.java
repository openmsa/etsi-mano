package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 'Subscribed notification type'
 */
public enum SubsctiptionTypeAppPkg {
  APPPACKAGEONBOARDING("AppPackageOnBoarding"),
    APPPACAKGEOPERATIONCHANGE("AppPacakgeOperationChange"),
    APPPACKAGEDELETION("AppPackageDeletion");

  private String value;

  SubsctiptionTypeAppPkg(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SubsctiptionTypeAppPkg fromValue(String text) {
    for (SubsctiptionTypeAppPkg b : SubsctiptionTypeAppPkg.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
