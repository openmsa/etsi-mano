package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm.ThresholdCriteria;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm.ThresholdLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Threshold
 */
@Validated


public class Threshold   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectType")
  private String objectType = null;

  @JsonProperty("objectInstanceId")
  private String objectInstanceId = null;

  @JsonProperty("subjObjectInstanceIds")
  @Valid
  private List<String> subjObjectInstanceIds = new ArrayList<>();

  @JsonProperty("criteria")
  private ThresholdCriteria criteria = null;

  @JsonProperty("_links")
  private ThresholdLinks _links = null;

  public Threshold id(String id) {
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

  public Threshold objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Type of measured object. The applicable measured object type for a measurement is defined in clause 8.2 of ETSI GS NFV-IFA 031.
   * @return objectType
   **/
  @Schema(required = true, description = "Type of measured object. The applicable measured object type for a measurement is defined in clause 8.2 of ETSI GS NFV-IFA 031.")
      @NotNull

    public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public Threshold objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

  /**
   * Get objectInstanceId
   * @return objectInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public Threshold subjObjectInstanceIds(List<String> subjObjectInstanceIds) {
    this.subjObjectInstanceIds = subjObjectInstanceIds;
    return this;
  }

  public Threshold addSubjObjectInstanceIdsItem(String subjObjectInstanceIdsItem) {
    this.subjObjectInstanceIds.add(subjObjectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the sub-object instances of the measured object instance  associated with this threshold.  May be present if a sub-object is defined in clause 8.2 of ETSI GS NFV-IFA 031 for the related measured object type. If this attribute is absent and a sub-object is defined in clause 8.2 of ETSI  GS NFV-IFA 031 for the related measured object type, thresholds are set for  all sub-object instances of the measured object instance. 
   * @return subjObjectInstanceIds
   **/
  @Schema(required = true, description = "Identifiers of the sub-object instances of the measured object instance  associated with this threshold.  May be present if a sub-object is defined in clause 8.2 of ETSI GS NFV-IFA 031 for the related measured object type. If this attribute is absent and a sub-object is defined in clause 8.2 of ETSI  GS NFV-IFA 031 for the related measured object type, thresholds are set for  all sub-object instances of the measured object instance. ")
      @NotNull

    public List<String> getSubjObjectInstanceIds() {
    return subjObjectInstanceIds;
  }

  public void setSubjObjectInstanceIds(List<String> subjObjectInstanceIds) {
    this.subjObjectInstanceIds = subjObjectInstanceIds;
  }

  public Threshold criteria(ThresholdCriteria criteria) {
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
    public ThresholdCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(ThresholdCriteria criteria) {
    this.criteria = criteria;
  }

  public Threshold _links(ThresholdLinks _links) {
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
    public ThresholdLinks getLinks() {
    return _links;
  }

  public void setLinks(ThresholdLinks _links) {
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
    Threshold threshold = (Threshold) o;
    return Objects.equals(this.id, threshold.id) &&
        Objects.equals(this.objectType, threshold.objectType) &&
        Objects.equals(this.objectInstanceId, threshold.objectInstanceId) &&
        Objects.equals(this.subjObjectInstanceIds, threshold.subjObjectInstanceIds) &&
        Objects.equals(this.criteria, threshold.criteria) &&
        Objects.equals(this._links, threshold._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectType, objectInstanceId, subjObjectInstanceIds, criteria, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Threshold {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    subjObjectInstanceIds: ").append(toIndentedString(subjObjectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
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
