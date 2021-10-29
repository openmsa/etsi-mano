package com.ubiqube.etsi.mano.em.v351.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnfpm.PmJobCriteria;
import com.ubiqube.etsi.mano.em.v351.model.vnfpm.PmJobLinks;
import com.ubiqube.etsi.mano.em.v351.model.vnfpm.PmJobReports;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a PM job. 
 */
@Schema(description = "This type represents a PM job. ")
@Validated


public class PmJob   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectType")
  private String objectType = null;

  @JsonProperty("objectInstanceIds")
  @Valid
  private List<String> objectInstanceIds = new ArrayList<>();

  @JsonProperty("subObjectInstanceIds")
  @Valid
  private List<String> subObjectInstanceIds = null;

  @JsonProperty("criteria")
  private PmJobCriteria criteria = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("reports")
  private PmJobReports reports = null;

  @JsonProperty("_links")
  private PmJobLinks _links = null;

  public PmJob id(String id) {
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

  public PmJob objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return objectType
   **/
  @Schema(required = true, description = "Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
      @NotNull

    public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public PmJob objectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public PmJob addObjectInstanceIdsItem(String objectInstanceIdsItem) {
    this.objectInstanceIds.add(objectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the VNF instances for which performance information is collected. 
   * @return objectInstanceIds
   **/
  @Schema(required = true, description = "Identifiers of the VNF instances for which performance information is collected. ")
      @NotNull

    public List<String> getObjectInstanceIds() {
    return objectInstanceIds;
  }

  public void setObjectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
  }

  public PmJob subObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
    return this;
  }

  public PmJob addSubObjectInstanceIdsItem(String subObjectInstanceIdsItem) {
    if (this.subObjectInstanceIds == null) {
      this.subObjectInstanceIds = new ArrayList<>();
    }
    this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. 
   * @return subObjectInstanceIds
   **/
  @Schema(description = "Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")
  
    public List<String> getSubObjectInstanceIds() {
    return subObjectInstanceIds;
  }

  public void setSubObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
  }

  public PmJob criteria(PmJobCriteria criteria) {
    this.criteria = criteria;
    return this;
  }

  /**
   * Get criteria
   * @return criteria
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PmJobCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(PmJobCriteria criteria) {
    this.criteria = criteria;
  }

  public PmJob callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public PmJob reports(PmJobReports reports) {
    this.reports = reports;
    return this;
  }

  /**
   * Get reports
   * @return reports
   **/
  @Schema(description = "")
  
    @Valid
    public PmJobReports getReports() {
    return reports;
  }

  public void setReports(PmJobReports reports) {
    this.reports = reports;
  }

  public PmJob _links(PmJobLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PmJobLinks getLinks() {
    return _links;
  }

  public void setLinks(PmJobLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PmJob pmJob = (PmJob) o;
    return Objects.equals(this.id, pmJob.id) &&
        Objects.equals(this.objectType, pmJob.objectType) &&
        Objects.equals(this.objectInstanceIds, pmJob.objectInstanceIds) &&
        Objects.equals(this.subObjectInstanceIds, pmJob.subObjectInstanceIds) &&
        Objects.equals(this.criteria, pmJob.criteria) &&
        Objects.equals(this.callbackUri, pmJob.callbackUri) &&
        Objects.equals(this.reports, pmJob.reports) &&
        Objects.equals(this._links, pmJob._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectType, objectInstanceIds, subObjectInstanceIds, criteria, callbackUri, reports, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJob {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    reports: ").append(toIndentedString(reports)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
