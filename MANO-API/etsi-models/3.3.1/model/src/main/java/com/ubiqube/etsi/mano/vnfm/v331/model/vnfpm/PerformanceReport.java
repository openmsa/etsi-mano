package com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm.PerformanceReportEntries;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type defines the format of a performance report provided by the VNFM to the EM as a result of collecting performance information as part of a PM job. The type shall comply with the provisions defined in table 6.5.2.10-1. 
 */
@Schema(description = "This type defines the format of a performance report provided by the VNFM to the EM as a result of collecting performance information as part of a PM job. The type shall comply with the provisions defined in table 6.5.2.10-1. ")
@Validated


public class PerformanceReport   {
  @JsonProperty("entries")
  @Valid
  private List<PerformanceReportEntries> entries = new ArrayList<>();

  public PerformanceReport entries(List<PerformanceReportEntries> entries) {
    this.entries = entries;
    return this;
  }

  public PerformanceReport addEntriesItem(PerformanceReportEntries entriesItem) {
    this.entries.add(entriesItem);
    return this;
  }

  /**
   * List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. VNF instance), but can include multiple collected values. 
   * @return entries
   **/
  @Schema(required = true, description = "List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. VNF instance), but can include multiple collected values. ")
      @NotNull
    @Valid
    public List<PerformanceReportEntries> getEntries() {
    return entries;
  }

  public void setEntries(List<PerformanceReportEntries> entries) {
    this.entries = entries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReport performanceReport = (PerformanceReport) o;
    return Objects.equals(this.entries, performanceReport.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReport {\n");
    
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
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
