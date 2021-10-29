package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.CapacityThresholdCriteria;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.CapacityThresholdLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a capacity threshold. It shall comply with the provisions defined in table 10.5.2.8-1. NOTE 1: The \&quot;objectInstanceId\&quot; aims to identify the \&quot;Individual VIM&#x27;s NFVI capacity information\&quot;, which is associated to a VIM instance. NOTE 2: The \&quot;subObjectInstanceIds\&quot; aim to identify the resource zones in which the available NFVI capacity crosses a threshold value. 
 */
@Schema(description = "This type represents a capacity threshold. It shall comply with the provisions defined in table 10.5.2.8-1. NOTE 1: The \"objectInstanceId\" aims to identify the \"Individual VIM's NFVI capacity information\", which is associated to a VIM instance. NOTE 2: The \"subObjectInstanceIds\" aim to identify the resource zones in which the available NFVI capacity crosses a threshold value. ")
@Validated


public class CapacityThreshold   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectInstanceId")
  private String objectInstanceId = null;

  @JsonProperty("subObjectInstanceIds")
  @Valid
  private List<String> subObjectInstanceIds = null;

  @JsonProperty("criteria")
  private CapacityThresholdCriteria criteria = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private CapacityThresholdLinks _links = null;

  public CapacityThreshold id(String id) {
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

  public CapacityThreshold objectInstanceId(String objectInstanceId) {
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

  public CapacityThreshold subObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
    return this;
  }

  public CapacityThreshold addSubObjectInstanceIdsItem(String subObjectInstanceIdsItem) {
    if (this.subObjectInstanceIds == null) {
      this.subObjectInstanceIds = new ArrayList<>();
    }
    this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the sub-object instances of the measured object instance associate with this capacity threshold. If this attribute is absent, measurements are taken for all sub-object instances of the measured object instance. See note 2. 
   * @return subObjectInstanceIds
   **/
  @Schema(description = "Identifiers of the sub-object instances of the measured object instance associate with this capacity threshold. If this attribute is absent, measurements are taken for all sub-object instances of the measured object instance. See note 2. ")
  
    public List<String> getSubObjectInstanceIds() {
    return subObjectInstanceIds;
  }

  public void setSubObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
  }

  public CapacityThreshold criteria(CapacityThresholdCriteria criteria) {
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
    public CapacityThresholdCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(CapacityThresholdCriteria criteria) {
    this.criteria = criteria;
  }

  public CapacityThreshold callbackUri(String callbackUri) {
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

  public CapacityThreshold _links(CapacityThresholdLinks _links) {
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
    public CapacityThresholdLinks getLinks() {
    return _links;
  }

  public void setLinks(CapacityThresholdLinks _links) {
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
    CapacityThreshold capacityThreshold = (CapacityThreshold) o;
    return Objects.equals(this.id, capacityThreshold.id) &&
        Objects.equals(this.objectInstanceId, capacityThreshold.objectInstanceId) &&
        Objects.equals(this.subObjectInstanceIds, capacityThreshold.subObjectInstanceIds) &&
        Objects.equals(this.criteria, capacityThreshold.criteria) &&
        Objects.equals(this.callbackUri, capacityThreshold.callbackUri) &&
        Objects.equals(this._links, capacityThreshold._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectInstanceId, subObjectInstanceIds, criteria, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CapacityThreshold {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
