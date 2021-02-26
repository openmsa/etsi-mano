package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.CpProtocolInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. 
 */
@Schema(description = "This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. ")
@Validated


public class SapInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("sapdId")
  private String sapdId = null;

  @JsonProperty("sapName")
  private String sapName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("sapProtocolInfo")
  @Valid
  private List<CpProtocolInfo> sapProtocolInfo = new ArrayList<>();

  public SapInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SapInfo sapdId(String sapdId) {
    this.sapdId = sapdId;
    return this;
  }

  /**
   * Get sapdId
   * @return sapdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getSapdId() {
    return sapdId;
  }

  public void setSapdId(String sapdId) {
    this.sapdId = sapdId;
  }

  public SapInfo sapName(String sapName) {
    this.sapName = sapName;
    return this;
  }

  /**
   * Human readable name for the SAP instance. 
   * @return sapName
   **/
  @Schema(required = true, description = "Human readable name for the SAP instance. ")
      @NotNull

    public String getSapName() {
    return sapName;
  }

  public void setSapName(String sapName) {
    this.sapName = sapName;
  }

  public SapInfo description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Human readable description for the SAP instance. 
   * @return description
   **/
  @Schema(description = "Human readable description for the SAP instance. ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SapInfo sapProtocolInfo(List<CpProtocolInfo> sapProtocolInfo) {
    this.sapProtocolInfo = sapProtocolInfo;
    return this;
  }

  public SapInfo addSapProtocolInfoItem(CpProtocolInfo sapProtocolInfoItem) {
    this.sapProtocolInfo.add(sapProtocolInfoItem);
    return this;
  }

  /**
   * Network protocol information for this SAP. 
   * @return sapProtocolInfo
   **/
  @Schema(required = true, description = "Network protocol information for this SAP. ")
      @NotNull
    @Valid
    public List<CpProtocolInfo> getSapProtocolInfo() {
    return sapProtocolInfo;
  }

  public void setSapProtocolInfo(List<CpProtocolInfo> sapProtocolInfo) {
    this.sapProtocolInfo = sapProtocolInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SapInfo sapInfo = (SapInfo) o;
    return Objects.equals(this.id, sapInfo.id) &&
        Objects.equals(this.sapdId, sapInfo.sapdId) &&
        Objects.equals(this.sapName, sapInfo.sapName) &&
        Objects.equals(this.description, sapInfo.description) &&
        Objects.equals(this.sapProtocolInfo, sapInfo.sapProtocolInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sapdId, sapName, description, sapProtocolInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SapInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
    sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    sapProtocolInfo: ").append(toIndentedString(sapProtocolInfo)).append("\n");
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
