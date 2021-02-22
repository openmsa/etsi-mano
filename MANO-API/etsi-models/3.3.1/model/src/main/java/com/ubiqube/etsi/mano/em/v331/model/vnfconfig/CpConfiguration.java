package com.ubiqube.etsi.mano.em.v331.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnfconfig.CpAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a CP instance. 
 */
@Schema(description = "This type represents configuration parameters of a CP instance. ")
@Validated


public class CpConfiguration   {
  @JsonProperty("cpId")
  private String cpId = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("addresses")
  @Valid
  private List<CpAddress> addresses = new ArrayList<>();

  public CpConfiguration cpId(String cpId) {
    this.cpId = cpId;
    return this;
  }

  /**
   * Get cpId
   * @return cpId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCpId() {
    return cpId;
  }

  public void setCpId(String cpId) {
    this.cpId = cpId;
  }

  public CpConfiguration cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Get cpdId
   * @return cpdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public CpConfiguration addresses(List<CpAddress> addresses) {
    this.addresses = addresses;
    return this;
  }

  public CpConfiguration addAddressesItem(CpAddress addressesItem) {
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * Network address and port assigned to the CP. 
   * @return addresses
   **/
  @Schema(required = true, description = "Network address and port assigned to the CP. ")
      @NotNull
    @Valid
    public List<CpAddress> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<CpAddress> addresses) {
    this.addresses = addresses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpConfiguration cpConfiguration = (CpConfiguration) o;
    return Objects.equals(this.cpId, cpConfiguration.cpId) &&
        Objects.equals(this.cpdId, cpConfiguration.cpdId) &&
        Objects.equals(this.addresses, cpConfiguration.addresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpId, cpdId, addresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpConfiguration {\n");
    
    sb.append("    cpId: ").append(toIndentedString(cpId)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
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
