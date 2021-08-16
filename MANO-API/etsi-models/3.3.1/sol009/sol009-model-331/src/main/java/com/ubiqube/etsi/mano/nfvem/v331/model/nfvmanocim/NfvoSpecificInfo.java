package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.NfvoSpecificInfoSupportedNsdFormats;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.NfvoSpecificInfoSupportedVnfdFormats;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information attributes specific to an NFVO entity,  and that can be relevant to more than one NFV-MANO service offered by an  NFVO entity.  
 */
@ApiModel(description = "This type represents information attributes specific to an NFVO entity,  and that can be relevant to more than one NFV-MANO service offered by an  NFVO entity.  ")
@Validated
public class NfvoSpecificInfo   {
  @JsonProperty("maxOnboardedNsdNum")
  private Integer maxOnboardedNsdNum = null;

  @JsonProperty("maxOnboardedVnfPkgNum")
  private Integer maxOnboardedVnfPkgNum = null;

  @JsonProperty("supportedVnfdFormats")
  private NfvoSpecificInfoSupportedVnfdFormats supportedVnfdFormats = null;

  @JsonProperty("supportedNsdFormats")
  private NfvoSpecificInfoSupportedNsdFormats supportedNsdFormats = null;

  public NfvoSpecificInfo maxOnboardedNsdNum(Integer maxOnboardedNsdNum) {
    this.maxOnboardedNsdNum = maxOnboardedNsdNum;
    return this;
  }

  /**
   * Maximum number of NSDs that can be on-boarded on the NFVO.  NOTE: If this attribute is not present, the value of this parameter  is undefined. 
   * @return maxOnboardedNsdNum
  **/
  @ApiModelProperty(value = "Maximum number of NSDs that can be on-boarded on the NFVO.  NOTE: If this attribute is not present, the value of this parameter  is undefined. ")
  
    public Integer getMaxOnboardedNsdNum() {
    return maxOnboardedNsdNum;
  }

  public void setMaxOnboardedNsdNum(Integer maxOnboardedNsdNum) {
    this.maxOnboardedNsdNum = maxOnboardedNsdNum;
  }

  public NfvoSpecificInfo maxOnboardedVnfPkgNum(Integer maxOnboardedVnfPkgNum) {
    this.maxOnboardedVnfPkgNum = maxOnboardedVnfPkgNum;
    return this;
  }

  /**
   * Maximum number of VNF Packages that can be on-boarded on the NFVO.  NOTE: If this attribute is not present, the value of this parameter  is undefined. 
   * @return maxOnboardedVnfPkgNum
  **/
  @ApiModelProperty(value = "Maximum number of VNF Packages that can be on-boarded on the NFVO.  NOTE: If this attribute is not present, the value of this parameter  is undefined. ")
  
    public Integer getMaxOnboardedVnfPkgNum() {
    return maxOnboardedVnfPkgNum;
  }

  public void setMaxOnboardedVnfPkgNum(Integer maxOnboardedVnfPkgNum) {
    this.maxOnboardedVnfPkgNum = maxOnboardedVnfPkgNum;
  }

  public NfvoSpecificInfo supportedVnfdFormats(NfvoSpecificInfoSupportedVnfdFormats supportedVnfdFormats) {
    this.supportedVnfdFormats = supportedVnfdFormats;
    return this;
  }

  /**
   * Get supportedVnfdFormats
   * @return supportedVnfdFormats
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public NfvoSpecificInfoSupportedVnfdFormats getSupportedVnfdFormats() {
    return supportedVnfdFormats;
  }

  public void setSupportedVnfdFormats(NfvoSpecificInfoSupportedVnfdFormats supportedVnfdFormats) {
    this.supportedVnfdFormats = supportedVnfdFormats;
  }

  public NfvoSpecificInfo supportedNsdFormats(NfvoSpecificInfoSupportedNsdFormats supportedNsdFormats) {
    this.supportedNsdFormats = supportedNsdFormats;
    return this;
  }

  /**
   * Get supportedNsdFormats
   * @return supportedNsdFormats
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public NfvoSpecificInfoSupportedNsdFormats getSupportedNsdFormats() {
    return supportedNsdFormats;
  }

  public void setSupportedNsdFormats(NfvoSpecificInfoSupportedNsdFormats supportedNsdFormats) {
    this.supportedNsdFormats = supportedNsdFormats;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfvoSpecificInfo nfvoSpecificInfo = (NfvoSpecificInfo) o;
    return Objects.equals(this.maxOnboardedNsdNum, nfvoSpecificInfo.maxOnboardedNsdNum) &&
        Objects.equals(this.maxOnboardedVnfPkgNum, nfvoSpecificInfo.maxOnboardedVnfPkgNum) &&
        Objects.equals(this.supportedVnfdFormats, nfvoSpecificInfo.supportedVnfdFormats) &&
        Objects.equals(this.supportedNsdFormats, nfvoSpecificInfo.supportedNsdFormats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxOnboardedNsdNum, maxOnboardedVnfPkgNum, supportedVnfdFormats, supportedNsdFormats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfvoSpecificInfo {\n");
    
    sb.append("    maxOnboardedNsdNum: ").append(toIndentedString(maxOnboardedNsdNum)).append("\n");
    sb.append("    maxOnboardedVnfPkgNum: ").append(toIndentedString(maxOnboardedVnfPkgNum)).append("\n");
    sb.append("    supportedVnfdFormats: ").append(toIndentedString(supportedVnfdFormats)).append("\n");
    sb.append("    supportedNsdFormats: ").append(toIndentedString(supportedNsdFormats)).append("\n");
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
