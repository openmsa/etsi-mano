package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate VNF\" LCM operation.    SCALE | Represents the \"Scale VNF\" LCM operation. SCALE_TO_LEVEL | Represents the \"Scale VNF to Level\" LCM operation. CHANGE_FLAVOUR | Represents the \"Change VNF Flavour\" LCM operation. TERMINATE | Represents the \"Terminate VNF\" LCM operation. HEAL | Represents the \"Heal VNF\" LCM operation. OPERATE | Represents the \"Operate VNF\" LCM operation. CHANGE_EXT_CONN | Represents the \"Change external VNF connectivity\" LCM operation. MODIFY_INFO | Represents the \"Modify VNF Information\" LCM operation.      
 */
public enum LcmOperationType {
  
  INSTANTIATE("INSTANTIATE"),
  
  SCALE("SCALE"),
  
  SCALE_TO_LEVEL("SCALE_TO_LEVEL"),
  
  CHANGE_FLAVOUR("CHANGE_FLAVOUR"),
  
  TERMINATE("TERMINATE"),
  
  HEAL("HEAL"),
  
  OPERATE("OPERATE"),
  
  CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
  
  MODIFY_INFO("MODIFY_INFO");

  private String value;

  LcmOperationType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOperationType fromValue(String text) {
    for (LcmOperationType b : LcmOperationType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

