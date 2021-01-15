package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 'The lifecycle management operation for which granting is requested'
 */
public enum GrantRequestOperation {
  INSTANTIATE("INSTANTIATE"),
    OPERATE("OPERATE"),
    TERMINATE("TERMINATE");

  private String value;

  GrantRequestOperation(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static GrantRequestOperation fromValue(String text) {
    for (GrantRequestOperation b : GrantRequestOperation.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
