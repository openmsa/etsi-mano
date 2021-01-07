package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Operational state of the onboarded application package: Ã¢â‚¬Â¢ENABLED: the application package can be used for instantiation of new application instances. Ã¢â‚¬Â¢DISABLED: the application package cannot be used for further application instantiation requests.
 */
public enum AppPkgOperationalState {
  ENABLED("ENABLED"),
    DISABLED("DISABLED");

  private String value;

  AppPkgOperationalState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppPkgOperationalState fromValue(String text) {
    for (AppPkgOperationalState b : AppPkgOperationalState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
