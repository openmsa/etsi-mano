package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.ManoManagedObjectReference;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a request to compile the logged data associated to an object instance. It shall comply with the provisions defined in table 8.6.2.8-1. 
 */
@Schema(description = "This type represents a request to compile the logged data associated to an object instance. It shall comply with the provisions defined in table 8.6.2.8-1. ")
@Validated


public class CompileLogRequest   {
  @JsonProperty("objectInstanceId")
  private ManoManagedObjectReference objectInstanceId = null;

  public CompileLogRequest objectInstanceId(ManoManagedObjectReference objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

  /**
   * Get objectInstanceId
   * @return objectInstanceId
   **/
  @Schema(description = "")
  
    @Valid
    public ManoManagedObjectReference getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(ManoManagedObjectReference objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompileLogRequest compileLogRequest = (CompileLogRequest) o;
    return Objects.equals(this.objectInstanceId, compileLogRequest.objectInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompileLogRequest {\n");
    
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
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
