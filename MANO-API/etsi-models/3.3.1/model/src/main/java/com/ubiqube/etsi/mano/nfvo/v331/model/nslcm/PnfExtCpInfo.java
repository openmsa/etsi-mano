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
 * This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. 
 */
@Schema(description = "This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. ")
@Validated


public class PnfExtCpInfo   {
  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("cpProtocolData")
  @Valid
  private List<CpProtocolData> cpProtocolData = null;

  public PnfExtCpInfo cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Get cpInstanceId
   * @return cpInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public PnfExtCpInfo cpdId(String cpdId) {
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

  public PnfExtCpInfo cpProtocolData(List<CpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public PnfExtCpInfo addCpProtocolDataItem(CpProtocolData cpProtocolDataItem) {
    if (this.cpProtocolData == null) {
      this.cpProtocolData = new ArrayList<>();
    }
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }

  /**
   * Parameters for configuring the network protocols on the CP. 
   * @return cpProtocolData
   **/
  @Schema(description = "Parameters for configuring the network protocols on the CP. ")
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
    PnfExtCpInfo pnfExtCpInfo = (PnfExtCpInfo) o;
    return Objects.equals(this.cpInstanceId, pnfExtCpInfo.cpInstanceId) &&
        Objects.equals(this.cpdId, pnfExtCpInfo.cpdId) &&
        Objects.equals(this.cpProtocolData, pnfExtCpInfo.cpProtocolData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpInstanceId, cpdId, cpProtocolData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfExtCpInfo {\n");
    
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
