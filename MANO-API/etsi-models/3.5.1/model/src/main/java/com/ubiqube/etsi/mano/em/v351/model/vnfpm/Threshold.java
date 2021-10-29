package com.ubiqube.etsi.mano.em.v351.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnfpm.ThresholdCriteria;
import com.ubiqube.etsi.mano.em.v351.model.vnfpm.ThresholdLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a threshold. 
 */
@Schema(description = "This type represents a threshold. ")
@Validated


public class Threshold   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectType")
  private String objectType = null;

  @JsonProperty("objectInstanceId")
  private String objectInstanceId = null;

  @JsonProperty("subObjectInstanceIds")
  @Valid
  private List<String> subObjectInstanceIds = null;

  @JsonProperty("criteria")
  private ThresholdCriteria criteria = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

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

  public Threshold subObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
    return this;
  }

  public Threshold addSubObjectInstanceIdsItem(String subObjectInstanceIdsItem) {
    if (this.subObjectInstanceIds == null) {
      this.subObjectInstanceIds = new ArrayList<>();
    }
    this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the sub-object instances of the measured object instance associated with the threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measurement type. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. 
   * @return subObjectInstanceIds
   **/
  @Schema(description = "Identifiers of the sub-object instances of the measured object instance associated with the threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measurement type. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")
  
    public List<String> getSubObjectInstanceIds() {
    return subObjectInstanceIds;
  }

  public void setSubObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
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

  public Threshold callbackUri(String callbackUri) {
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
        Objects.equals(this.subObjectInstanceIds, threshold.subObjectInstanceIds) &&
        Objects.equals(this.criteria, threshold.criteria) &&
        Objects.equals(this.callbackUri, threshold.callbackUri) &&
        Objects.equals(this._links, threshold._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectType, objectInstanceId, subObjectInstanceIds, criteria, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Threshold {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
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
