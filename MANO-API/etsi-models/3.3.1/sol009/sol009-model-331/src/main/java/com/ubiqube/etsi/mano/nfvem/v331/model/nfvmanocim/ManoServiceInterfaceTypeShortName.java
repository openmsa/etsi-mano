package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * For the RESTful NFV-MANO APIs, valid values are all values for \&quot;apiName\&quot; as defined  in ETSI GS NFV-SOL 002, ETSI GS NFV-SOL 003, and ETSI GS NFV-SOL 005. For the NFV-MANO service interfaces for which no API is specified by ETSI NFV, valid  values are defined in table 5.6.4.3-1. NOTE: The table is expected to be updated, by removing the corresponding listed entries,  once the interfaces are specified as a RESTful NFV-MANO API.  
 */
@ApiModel(description = "For the RESTful NFV-MANO APIs, valid values are all values for \"apiName\" as defined  in ETSI GS NFV-SOL 002, ETSI GS NFV-SOL 003, and ETSI GS NFV-SOL 005. For the NFV-MANO service interfaces for which no API is specified by ETSI NFV, valid  values are defined in table 5.6.4.3-1. NOTE: The table is expected to be updated, by removing the corresponding listed entries,  once the interfaces are specified as a RESTful NFV-MANO API.  ")
@Validated
public class ManoServiceInterfaceTypeShortName   {

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoServiceInterfaceTypeShortName {\n");
    
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
