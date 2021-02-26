package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ModificationsTriggeredByVnfPkgChange;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ModifyVnfInfoData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \&quot;changedInfo\&quot; attribute is present,  either the \&quot;changedVnfInfo\&quot; attribute or the \&quot;changedExtConnectivity\&quot; attribute or both shall be present. 
 */
@Schema(description = "Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \"changedInfo\" attribute is present,  either the \"changedVnfInfo\" attribute or the \"changedExtConnectivity\" attribute or both shall be present. ")
@Validated


public class AffectedVnfChangedInfo   {
  @JsonProperty("changedVnfInfo")
  private ModifyVnfInfoData changedVnfInfo = null;

  @JsonProperty("changedExtConnectivity")
  @Valid
  private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

  @JsonProperty("modificationsTriggeredByVnfPkgChange")
  private ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange = null;

  public AffectedVnfChangedInfo changedVnfInfo(ModifyVnfInfoData changedVnfInfo) {
    this.changedVnfInfo = changedVnfInfo;
    return this;
  }

  /**
   * Get changedVnfInfo
   * @return changedVnfInfo
   **/
  @Schema(description = "")
  
    @Valid
    public ModifyVnfInfoData getChangedVnfInfo() {
    return changedVnfInfo;
  }

  public void setChangedVnfInfo(ModifyVnfInfoData changedVnfInfo) {
    this.changedVnfInfo = changedVnfInfo;
  }

  public AffectedVnfChangedInfo changedExtConnectivity(List<ExtVirtualLinkInfo> changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
    return this;
  }

  public AffectedVnfChangedInfo addChangedExtConnectivityItem(ExtVirtualLinkInfo changedExtConnectivityItem) {
    if (this.changedExtConnectivity == null) {
      this.changedExtConnectivity = new ArrayList<>();
    }
    this.changedExtConnectivity.add(changedExtConnectivityItem);
    return this;
  }

  /**
   * Information about changed external connectivity, if applicable. 
   * @return changedExtConnectivity
   **/
  @Schema(description = "Information about changed external connectivity, if applicable. ")
      @Valid
    public List<ExtVirtualLinkInfo> getChangedExtConnectivity() {
    return changedExtConnectivity;
  }

  public void setChangedExtConnectivity(List<ExtVirtualLinkInfo> changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
  }

  public AffectedVnfChangedInfo modificationsTriggeredByVnfPkgChange(ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
    this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
    return this;
  }

  /**
   * Get modificationsTriggeredByVnfPkgChange
   * @return modificationsTriggeredByVnfPkgChange
   **/
  @Schema(description = "")
  
    @Valid
    public ModificationsTriggeredByVnfPkgChange getModificationsTriggeredByVnfPkgChange() {
    return modificationsTriggeredByVnfPkgChange;
  }

  public void setModificationsTriggeredByVnfPkgChange(ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
    this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedVnfChangedInfo affectedVnfChangedInfo = (AffectedVnfChangedInfo) o;
    return Objects.equals(this.changedVnfInfo, affectedVnfChangedInfo.changedVnfInfo) &&
        Objects.equals(this.changedExtConnectivity, affectedVnfChangedInfo.changedExtConnectivity) &&
        Objects.equals(this.modificationsTriggeredByVnfPkgChange, affectedVnfChangedInfo.modificationsTriggeredByVnfPkgChange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(changedVnfInfo, changedExtConnectivity, modificationsTriggeredByVnfPkgChange);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVnfChangedInfo {\n");
    
    sb.append("    changedVnfInfo: ").append(toIndentedString(changedVnfInfo)).append("\n");
    sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
    sb.append("    modificationsTriggeredByVnfPkgChange: ").append(toIndentedString(modificationsTriggeredByVnfPkgChange)).append("\n");
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
