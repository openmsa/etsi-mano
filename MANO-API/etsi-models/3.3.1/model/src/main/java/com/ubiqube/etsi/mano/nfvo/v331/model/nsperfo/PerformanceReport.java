/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.nsperfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsperfo.PerformanceReportEntries;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type defines the format of a performance report provided by the NFVO to the OSS/BSS as a result of collecting performance information as part of a PM job. The type shall comply with the provisions defined in Table 7.5.2.10-1. 
 */
@Schema(description = "This type defines the format of a performance report provided by the NFVO to the OSS/BSS as a result of collecting performance information as part of a PM job. The type shall comply with the provisions defined in Table 7.5.2.10-1. ")
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
   * List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. NS instance), but can include multiple collected values. 
   * @return entries
   **/
  @Schema(required = true, description = "List of performance information entries. Each performance report entry is for a given metric of a given object (i.e. NS instance), but can include multiple collected values. ")
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
