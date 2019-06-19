package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type defines the format of a performance report provided by the NFVO  to the OSS/BSS as a result of collecting performance information as part of a PM job.  The type shall comply with the provisions defined in Table 7.5.2.10-1. 
 **/
@ApiModel(description="This type defines the format of a performance report provided by the NFVO  to the OSS/BSS as a result of collecting performance information as part of a PM job.  The type shall comply with the provisions defined in Table 7.5.2.10-1. ")
public class InlineResponse2001PerformanceReport  {
  
  @ApiModelProperty(required = true, value = "List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. NS instance), but can include multiple collected values. ")
  @Valid
 /**
   * List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. NS instance), but can include multiple collected values. 
  **/
  private List<InlineResponse2001PerformanceReportEntries> entries = new ArrayList<InlineResponse2001PerformanceReportEntries>();
 /**
   * List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. NS instance), but can include multiple collected values. 
   * @return entries
  **/
  @JsonProperty("entries")
  @NotNull
  public List<InlineResponse2001PerformanceReportEntries> getEntries() {
    return entries;
  }

  public void setEntries(List<InlineResponse2001PerformanceReportEntries> entries) {
    this.entries = entries;
  }

  public InlineResponse2001PerformanceReport entries(List<InlineResponse2001PerformanceReportEntries> entries) {
    this.entries = entries;
    return this;
  }

  public InlineResponse2001PerformanceReport addEntriesItem(InlineResponse2001PerformanceReportEntries entriesItem) {
    this.entries.add(entriesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001PerformanceReport {\n");
    
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

