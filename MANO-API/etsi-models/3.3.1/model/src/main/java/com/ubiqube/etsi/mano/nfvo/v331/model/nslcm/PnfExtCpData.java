package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.CpProtocolData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the configuration data on the external CP of the PNF. It shall comply with the provisions defined in Table 6.5.3.16-1. 
 */
@Schema(description = "This type represents the configuration data on the external CP of the PNF. It shall comply with the provisions defined in Table 6.5.3.16-1. ")
@Validated


public class PnfExtCpData   {
  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("cpProtocolData")
  @Valid
  private List<CpProtocolData> cpProtocolData = new ArrayList<>();

  public PnfExtCpData cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Get cpInstanceId
   * @return cpInstanceId
   **/
  @Schema(description = "")
  
    public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public PnfExtCpData cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Get cpdId
   * @return cpdId
   **/
  @Schema(description = "")
  
    public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public PnfExtCpData cpProtocolData(List<CpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public PnfExtCpData addCpProtocolDataItem(CpProtocolData cpProtocolDataItem) {
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }

  /**
   * Address assigned for this CP. 
   * @return cpProtocolData
   **/
  @Schema(required = true, description = "Address assigned for this CP. ")
      @NotNull
    @Valid
    public List<CpProtocolData> getCpProtocolData() {
    return cpProtocolData;
  }

  public void setCpProtocolData(List<CpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PnfExtCpData pnfExtCpData = (PnfExtCpData) o;
    return Objects.equals(this.cpInstanceId, pnfExtCpData.cpInstanceId) &&
        Objects.equals(this.cpdId, pnfExtCpData.cpdId) &&
        Objects.equals(this.cpProtocolData, pnfExtCpData.cpProtocolData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpInstanceId, cpdId, cpProtocolData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfExtCpData {\n");
    
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
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
