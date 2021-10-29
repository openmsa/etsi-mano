package com.ubiqube.etsi.mano.em.v351.model.lcmcoord;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The enumeration LcmOperationForCoordType defines the permitted values to represent VNF lifecycle operation types in VNF LCM operation coordination actions. * INSTANTIATE: Represents the \"Instantiate VNF\" LCM operation. * SCALE: Represents the \"Scale VNF\" LCM operation. * SCALE_TO_LEVEL: Represents the \"Scale VNF to Level\" LCM operation. * CHANGE_FLAVOUR: Represents the \"Change VNF Flavour\" LCM operation. * TERMINATE: Represents the \"Terminate VNF\" LCM operation. * HEAL: Represents the \"Heal VNF\" LCM operation. * OPERATE: Represents the \"Operate VNF\" LCM operation. * CHANGE_EXT_CONN: Represents the \"Change external VNF connectivity\" LCM operation. * MODIFY_INFO: Represents the \"Modify VNF Information\" LCM operation. * CREATE_SNAPSHOT: Represents the \"Create VNF Snapshot\" LCM operation. * REVERT_TO_SNAPSHOT: Represents the \"Revert To VNF Snapshot\" LCM operation. * CHANGE_VNFPKG: Represents the \"Change current VNF package\" LCM operation. 
 */
public enum LcmOperationForCoordType {
  INSTANTIATE("INSTANTIATE"),
    SCALE("SCALE"),
    SCALE_TO_LEVEL("SCALE_TO_LEVEL"),
    CHANGE_FLAVOUR("CHANGE_FLAVOUR"),
    TERMINATE("TERMINATE"),
    HEAL("HEAL"),
    OPERATE("OPERATE"),
    CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
    MODIFY_INFO("MODIFY_INFO"),
    CREATE_SNAPSHOT("CREATE_SNAPSHOT"),
    REVERT_TO_SNAPSHOT("REVERT_TO_SNAPSHOT"),
    CHANGE_VNFPKG("CHANGE_VNFPKG");

  private String value;

  LcmOperationForCoordType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LcmOperationForCoordType fromValue(String text) {
    for (LcmOperationForCoordType b : LcmOperationForCoordType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
