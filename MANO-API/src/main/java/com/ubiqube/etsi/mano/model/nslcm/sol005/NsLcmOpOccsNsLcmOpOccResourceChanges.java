package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable 
 **/
@ApiModel(description="This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable ")
public class NsLcmOpOccsNsLcmOpOccResourceChanges  {
  
  @ApiModelProperty(value = "Information about the VNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")
  @Valid
 /**
   * Information about the VNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs> affectedVnfs = null;

  @ApiModelProperty(value = "Information about the PNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")
  @Valid
 /**
   * Information about the PNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs> affectedPnfs = null;

  @ApiModelProperty(value = "Information about the VL instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. ")
  @Valid
 /**
   * Information about the VL instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls> affectedVls = null;

  @ApiModelProperty(value = "Information about the VNFFG instances that were affected during the lifecycle operation, if this notification               represents the result of a lifecycle operation. See note ")
  @Valid
 /**
   * Information about the VNFFG instances that were affected during the lifecycle operation, if this notification               represents the result of a lifecycle operation. See note 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffgs = null;

  @ApiModelProperty(value = "Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. ")
  @Valid
 /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss> affectedNss = null;

  @ApiModelProperty(value = "Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. ")
  @Valid
 /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedSaps = null;
 /**
   * Information about the VNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedVnfs
  **/
  @JsonProperty("affectedVnfs")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs> getAffectedVnfs() {
    return affectedVnfs;
  }

  public void setAffectedVnfs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs> affectedVnfs) {
    this.affectedVnfs = affectedVnfs;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedVnfs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs> affectedVnfs) {
    this.affectedVnfs = affectedVnfs;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedVnfsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs affectedVnfsItem) {
    this.affectedVnfs.add(affectedVnfsItem);
    return this;
  }

 /**
   * Information about the PNF instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedPnfs
  **/
  @JsonProperty("affectedPnfs")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs> getAffectedPnfs() {
    return affectedPnfs;
  }

  public void setAffectedPnfs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs> affectedPnfs) {
    this.affectedPnfs = affectedPnfs;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedPnfs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs> affectedPnfs) {
    this.affectedPnfs = affectedPnfs;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedPnfsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs affectedPnfsItem) {
    this.affectedPnfs.add(affectedPnfsItem);
    return this;
  }

 /**
   * Information about the VL instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. 
   * @return affectedVls
  **/
  @JsonProperty("affectedVls")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls> getAffectedVls() {
    return affectedVls;
  }

  public void setAffectedVls(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls> affectedVls) {
    this.affectedVls = affectedVls;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedVls(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls> affectedVls) {
    this.affectedVls = affectedVls;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedVlsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls affectedVlsItem) {
    this.affectedVls.add(affectedVlsItem);
    return this;
  }

 /**
   * Information about the VNFFG instances that were affected during the lifecycle operation, if this notification               represents the result of a lifecycle operation. See note 
   * @return affectedVnffgs
  **/
  @JsonProperty("affectedVnffgs")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> getAffectedVnffgs() {
    return affectedVnffgs;
  }

  public void setAffectedVnffgs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffgs) {
    this.affectedVnffgs = affectedVnffgs;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedVnffgs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffgs) {
    this.affectedVnffgs = affectedVnffgs;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedVnffgsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs affectedVnffgsItem) {
    this.affectedVnffgs.add(affectedVnffgsItem);
    return this;
  }

 /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
   * @return affectedNss
  **/
  @JsonProperty("affectedNss")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss> getAffectedNss() {
    return affectedNss;
  }

  public void setAffectedNss(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss> affectedNss) {
    this.affectedNss = affectedNss;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedNss(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss> affectedNss) {
    this.affectedNss = affectedNss;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedNssItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss affectedNssItem) {
    this.affectedNss.add(affectedNssItem);
    return this;
  }

 /**
   * Information about the nested NS instances that were affected during the lifecycle operation, if this notification represents the result of a lifecycle operation. See note. 
   * @return affectedSaps
  **/
  @JsonProperty("affectedSaps")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> getAffectedSaps() {
    return affectedSaps;
  }

  public void setAffectedSaps(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedSaps) {
    this.affectedSaps = affectedSaps;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges affectedSaps(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedSaps) {
    this.affectedSaps = affectedSaps;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChanges addAffectedSapsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps affectedSapsItem) {
    this.affectedSaps.add(affectedSapsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChanges {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

