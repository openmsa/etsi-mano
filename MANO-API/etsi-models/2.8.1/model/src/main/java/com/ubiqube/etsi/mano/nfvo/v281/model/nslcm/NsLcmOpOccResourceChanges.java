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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedNs;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedPnf;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedSap;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedVnf;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.AffectedVnffg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable 
 */
@ApiModel(description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable ")
@Validated

public class NsLcmOpOccResourceChanges   {
  @JsonProperty("affectedVnfs")
  @Valid
  private List<AffectedVnf> affectedVnfs = null;

  @JsonProperty("affectedPnfs")
  @Valid
  private List<AffectedPnf> affectedPnfs = null;

  @JsonProperty("affectedVls")
  @Valid
  private List<AffectedVirtualLink> affectedVls = null;

  @JsonProperty("affectedVnffgs")
  @Valid
  private List<AffectedVnffg> affectedVnffgs = null;

  @JsonProperty("affectedNss")
  @Valid
  private List<AffectedNs> affectedNss = null;

  @JsonProperty("affectedSaps")
  @Valid
  private List<AffectedSap> affectedSaps = null;

  public NsLcmOpOccResourceChanges affectedVnfs(List<AffectedVnf> affectedVnfs) {
    this.affectedVnfs = affectedVnfs;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedVnfsItem(AffectedVnf affectedVnfsItem) {
    if (this.affectedVnfs == null) {
      this.affectedVnfs = new ArrayList<>();
    }
    this.affectedVnfs.add(affectedVnfsItem);
    return this;
  }

  /**
   * Information about the VNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedVnfs
  **/
  @ApiModelProperty(value = "Information about the VNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")

  @Valid

  public List<AffectedVnf> getAffectedVnfs() {
    return affectedVnfs;
  }

  public void setAffectedVnfs(List<AffectedVnf> affectedVnfs) {
    this.affectedVnfs = affectedVnfs;
  }

  public NsLcmOpOccResourceChanges affectedPnfs(List<AffectedPnf> affectedPnfs) {
    this.affectedPnfs = affectedPnfs;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedPnfsItem(AffectedPnf affectedPnfsItem) {
    if (this.affectedPnfs == null) {
      this.affectedPnfs = new ArrayList<>();
    }
    this.affectedPnfs.add(affectedPnfsItem);
    return this;
  }

  /**
   * Information about the PNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedPnfs
  **/
  @ApiModelProperty(value = "Information about the PNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")

  @Valid

  public List<AffectedPnf> getAffectedPnfs() {
    return affectedPnfs;
  }

  public void setAffectedPnfs(List<AffectedPnf> affectedPnfs) {
    this.affectedPnfs = affectedPnfs;
  }

  public NsLcmOpOccResourceChanges affectedVls(List<AffectedVirtualLink> affectedVls) {
    this.affectedVls = affectedVls;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedVlsItem(AffectedVirtualLink affectedVlsItem) {
    if (this.affectedVls == null) {
      this.affectedVls = new ArrayList<>();
    }
    this.affectedVls.add(affectedVlsItem);
    return this;
  }

  /**
   * Information about the VL instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedVls
  **/
  @ApiModelProperty(value = "Information about the VL instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")

  @Valid

  public List<AffectedVirtualLink> getAffectedVls() {
    return affectedVls;
  }

  public void setAffectedVls(List<AffectedVirtualLink> affectedVls) {
    this.affectedVls = affectedVls;
  }

  public NsLcmOpOccResourceChanges affectedVnffgs(List<AffectedVnffg> affectedVnffgs) {
    this.affectedVnffgs = affectedVnffgs;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedVnffgsItem(AffectedVnffg affectedVnffgsItem) {
    if (this.affectedVnffgs == null) {
      this.affectedVnffgs = new ArrayList<>();
    }
    this.affectedVnffgs.add(affectedVnffgsItem);
    return this;
  }

  /**
   * Information about the VNFFG instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note 
   * @return affectedVnffgs
  **/
  @ApiModelProperty(value = "Information about the VNFFG instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note ")

  @Valid

  public List<AffectedVnffg> getAffectedVnffgs() {
    return affectedVnffgs;
  }

  public void setAffectedVnffgs(List<AffectedVnffg> affectedVnffgs) {
    this.affectedVnffgs = affectedVnffgs;
  }

  public NsLcmOpOccResourceChanges affectedNss(List<AffectedNs> affectedNss) {
    this.affectedNss = affectedNss;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedNssItem(AffectedNs affectedNssItem) {
    if (this.affectedNss == null) {
      this.affectedNss = new ArrayList<>();
    }
    this.affectedNss.add(affectedNssItem);
    return this;
  }

  /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
   * @return affectedNss
  **/
  @ApiModelProperty(value = "Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. ")

  @Valid

  public List<AffectedNs> getAffectedNss() {
    return affectedNss;
  }

  public void setAffectedNss(List<AffectedNs> affectedNss) {
    this.affectedNss = affectedNss;
  }

  public NsLcmOpOccResourceChanges affectedSaps(List<AffectedSap> affectedSaps) {
    this.affectedSaps = affectedSaps;
    return this;
  }

  public NsLcmOpOccResourceChanges addAffectedSapsItem(AffectedSap affectedSapsItem) {
    if (this.affectedSaps == null) {
      this.affectedSaps = new ArrayList<>();
    }
    this.affectedSaps.add(affectedSapsItem);
    return this;
  }

  /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
   * @return affectedSaps
  **/
  @ApiModelProperty(value = "Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. ")

  @Valid

  public List<AffectedSap> getAffectedSaps() {
    return affectedSaps;
  }

  public void setAffectedSaps(List<AffectedSap> affectedSaps) {
    this.affectedSaps = affectedSaps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsLcmOpOccResourceChanges nsLcmOpOccResourceChanges = (NsLcmOpOccResourceChanges) o;
    return Objects.equals(this.affectedVnfs, nsLcmOpOccResourceChanges.affectedVnfs) &&
        Objects.equals(this.affectedPnfs, nsLcmOpOccResourceChanges.affectedPnfs) &&
        Objects.equals(this.affectedVls, nsLcmOpOccResourceChanges.affectedVls) &&
        Objects.equals(this.affectedVnffgs, nsLcmOpOccResourceChanges.affectedVnffgs) &&
        Objects.equals(this.affectedNss, nsLcmOpOccResourceChanges.affectedNss) &&
        Objects.equals(this.affectedSaps, nsLcmOpOccResourceChanges.affectedSaps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affectedVnfs, affectedPnfs, affectedVls, affectedVnffgs, affectedNss, affectedSaps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccResourceChanges {\n");
    
    sb.append("    affectedVnfs: ").append(toIndentedString(affectedVnfs)).append("\n");
    sb.append("    affectedPnfs: ").append(toIndentedString(affectedPnfs)).append("\n");
    sb.append("    affectedVls: ").append(toIndentedString(affectedVls)).append("\n");
    sb.append("    affectedVnffgs: ").append(toIndentedString(affectedVnffgs)).append("\n");
    sb.append("    affectedNss: ").append(toIndentedString(affectedNss)).append("\n");
    sb.append("    affectedSaps: ").append(toIndentedString(affectedSaps)).append("\n");
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

