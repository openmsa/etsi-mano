package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ChangeAdministrativeStateEnumType;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A change of administrative state. Shall be present if the state change request refers to the administrative state.  NOTE: In the present document version, a request shall only include an  operational state change (attribute \&quot;operationalStateChange\&quot;) or an  administrative state change request (attribute \&quot;administrativeStateChange\&quot;),  but not both. 
 */
@ApiModel(description = "A change of administrative state. Shall be present if the state change request refers to the administrative state.  NOTE: In the present document version, a request shall only include an  operational state change (attribute \"operationalStateChange\") or an  administrative state change request (attribute \"administrativeStateChange\"),  but not both. ")
@Validated
public class ChangeStateRequestAdministrativeStateChange   {
  @JsonProperty("administrativeStateAction")
  private ChangeAdministrativeStateEnumType administrativeStateAction = null;

  public ChangeStateRequestAdministrativeStateChange administrativeStateAction(ChangeAdministrativeStateEnumType administrativeStateAction) {
    this.administrativeStateAction = administrativeStateAction;
    return this;
  }

  /**
   * Get administrativeStateAction
   * @return administrativeStateAction
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ChangeAdministrativeStateEnumType getAdministrativeStateAction() {
    return administrativeStateAction;
  }

  public void setAdministrativeStateAction(ChangeAdministrativeStateEnumType administrativeStateAction) {
    this.administrativeStateAction = administrativeStateAction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeStateRequestAdministrativeStateChange changeStateRequestAdministrativeStateChange = (ChangeStateRequestAdministrativeStateChange) o;
    return Objects.equals(this.administrativeStateAction, changeStateRequestAdministrativeStateChange.administrativeStateAction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(administrativeStateAction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeStateRequestAdministrativeStateChange {\n");
    
    sb.append("    administrativeStateAction: ").append(toIndentedString(administrativeStateAction)).append("\n");
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
