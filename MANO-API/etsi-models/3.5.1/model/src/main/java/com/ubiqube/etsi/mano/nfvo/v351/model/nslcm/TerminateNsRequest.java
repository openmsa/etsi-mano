package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.TerminateNsData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. NOTE 1: Information needed for terminating specific VNF instances shall only be specified in the \&quot;terminateVnfData\&quot; attribute, and not in the \&quot;terminateNsData\&quot; attribute. NOTE 2:  VNF instance(s) part of this NS instance is(are) terminated as part of Terminate NS operation only if the instance(s) is(are) not used by any other NS instance. 
 */
@Schema(description = "This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. NOTE 1: Information needed for terminating specific VNF instances shall only be specified in the \"terminateVnfData\" attribute, and not in the \"terminateNsData\" attribute. NOTE 2:  VNF instance(s) part of this NS instance is(are) terminated as part of Terminate NS operation only if the instance(s) is(are) not used by any other NS instance. ")
@Validated


public class TerminateNsRequest   {
  @JsonProperty("terminationTime")
  private OffsetDateTime terminationTime = null;

  @JsonProperty("terminateNsData")
  private TerminateNsData terminateNsData = null;

  @JsonProperty("terminateVnfData")
  @Valid
  private List<OffsetDateTime> terminateVnfData = null;

  public TerminateNsRequest terminationTime(OffsetDateTime terminationTime) {
    this.terminationTime = terminationTime;
    return this;
  }

  /**
   * Get terminationTime
   * @return terminationTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getTerminationTime() {
    return terminationTime;
  }

  public void setTerminationTime(OffsetDateTime terminationTime) {
    this.terminationTime = terminationTime;
  }

  public TerminateNsRequest terminateNsData(TerminateNsData terminateNsData) {
    this.terminateNsData = terminateNsData;
    return this;
  }

  /**
   * Get terminateNsData
   * @return terminateNsData
   **/
  @Schema(description = "")
  
    @Valid
    public TerminateNsData getTerminateNsData() {
    return terminateNsData;
  }

  public void setTerminateNsData(TerminateNsData terminateNsData) {
    this.terminateNsData = terminateNsData;
  }

  public TerminateNsRequest terminateVnfData(List<OffsetDateTime> terminateVnfData) {
    this.terminateVnfData = terminateVnfData;
    return this;
  }

  public TerminateNsRequest addTerminateVnfDataItem(OffsetDateTime terminateVnfDataItem) {
    if (this.terminateVnfData == null) {
      this.terminateVnfData = new ArrayList<>();
    }
    this.terminateVnfData.add(terminateVnfDataItem);
    return this;
  }

  /**
   * Provides the information to terminate VNF instance(s). See note 1 and 2. 
   * @return terminateVnfData
   **/
  @Schema(description = "Provides the information to terminate VNF instance(s). See note 1 and 2. ")
      @Valid
    public List<OffsetDateTime> getTerminateVnfData() {
    return terminateVnfData;
  }

  public void setTerminateVnfData(List<OffsetDateTime> terminateVnfData) {
    this.terminateVnfData = terminateVnfData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateNsRequest terminateNsRequest = (TerminateNsRequest) o;
    return Objects.equals(this.terminationTime, terminateNsRequest.terminationTime) &&
        Objects.equals(this.terminateNsData, terminateNsRequest.terminateNsData) &&
        Objects.equals(this.terminateVnfData, terminateNsRequest.terminateVnfData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(terminationTime, terminateNsData, terminateVnfData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateNsRequest {\n");
    
    sb.append("    terminationTime: ").append(toIndentedString(terminationTime)).append("\n");
    sb.append("    terminateNsData: ").append(toIndentedString(terminateNsData)).append("\n");
    sb.append("    terminateVnfData: ").append(toIndentedString(terminateVnfData)).append("\n");
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
