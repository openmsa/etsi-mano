package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.AdministrativeStateEnumType;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.OperationalStateEnumType;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Current values of the state of the peer functional entity. 
 */
@ApiModel(description = "Current values of the state of the peer functional entity. ")
@Validated
public class CreatePeerEntityRequestPeerEntityState   {
  @JsonProperty("operationalState")
  private OperationalStateEnumType operationalState = null;

  @JsonProperty("administrativeState")
  private AdministrativeStateEnumType administrativeState = null;

  public CreatePeerEntityRequestPeerEntityState operationalState(OperationalStateEnumType operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OperationalStateEnumType getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(OperationalStateEnumType operationalState) {
    this.operationalState = operationalState;
  }

  public CreatePeerEntityRequestPeerEntityState administrativeState(AdministrativeStateEnumType administrativeState) {
    this.administrativeState = administrativeState;
    return this;
  }

  /**
   * Get administrativeState
   * @return administrativeState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AdministrativeStateEnumType getAdministrativeState() {
    return administrativeState;
  }

  public void setAdministrativeState(AdministrativeStateEnumType administrativeState) {
    this.administrativeState = administrativeState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePeerEntityRequestPeerEntityState createPeerEntityRequestPeerEntityState = (CreatePeerEntityRequestPeerEntityState) o;
    return Objects.equals(this.operationalState, createPeerEntityRequestPeerEntityState.operationalState) &&
        Objects.equals(this.administrativeState, createPeerEntityRequestPeerEntityState.administrativeState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationalState, administrativeState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePeerEntityRequestPeerEntityState {\n");
    
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
    sb.append("    administrativeState: ").append(toIndentedString(administrativeState)).append("\n");
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
