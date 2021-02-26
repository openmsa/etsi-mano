package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.WanConnectionInfoModification;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about the changed NS instance information, if applicable. 
 */
@Schema(description = "Information about the changed NS instance information, if applicable. ")
@Validated


public class AffectedNsChangedInfo   {
  @JsonProperty("wanConnectionInfoModifications")
  @Valid
  private List<WanConnectionInfoModification> wanConnectionInfoModifications = null;

  public AffectedNsChangedInfo wanConnectionInfoModifications(List<WanConnectionInfoModification> wanConnectionInfoModifications) {
    this.wanConnectionInfoModifications = wanConnectionInfoModifications;
    return this;
  }

  public AffectedNsChangedInfo addWanConnectionInfoModificationsItem(WanConnectionInfoModification wanConnectionInfoModificationsItem) {
    if (this.wanConnectionInfoModifications == null) {
      this.wanConnectionInfoModifications = new ArrayList<>();
    }
    this.wanConnectionInfoModifications.add(wanConnectionInfoModificationsItem);
    return this;
  }

  /**
   * Information about the modified WAN related connectivity information, if applicable. 
   * @return wanConnectionInfoModifications
   **/
  @Schema(description = "Information about the modified WAN related connectivity information, if applicable. ")
      @Valid
    public List<WanConnectionInfoModification> getWanConnectionInfoModifications() {
    return wanConnectionInfoModifications;
  }

  public void setWanConnectionInfoModifications(List<WanConnectionInfoModification> wanConnectionInfoModifications) {
    this.wanConnectionInfoModifications = wanConnectionInfoModifications;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedNsChangedInfo affectedNsChangedInfo = (AffectedNsChangedInfo) o;
    return Objects.equals(this.wanConnectionInfoModifications, affectedNsChangedInfo.wanConnectionInfoModifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wanConnectionInfoModifications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedNsChangedInfo {\n");
    
    sb.append("    wanConnectionInfoModifications: ").append(toIndentedString(wanConnectionInfoModifications)).append("\n");
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
