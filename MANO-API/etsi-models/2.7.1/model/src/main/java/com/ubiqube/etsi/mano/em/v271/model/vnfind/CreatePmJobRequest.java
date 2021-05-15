package com.ubiqube.etsi.mano.em.v271.model.vnfind;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.PmJobCriteria;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.SubscriptionAuthentication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a request to create a PM job. 
 */
@ApiModel(description = "This type represents a request to create a PM job. ")
@Validated

public class CreatePmJobRequest   {
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

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  public CreatePmJobRequest objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Type of the measured object. The applicable measured object type for a measurement  is defined in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return objectType
  **/
  @ApiModelProperty(required = true, value = "Type of the measured object. The applicable measured object type for a measurement  is defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
  @NotNull


  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public CreatePmJobRequest objectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public CreatePmJobRequest addObjectInstanceIdsItem(String objectInstanceIdsItem) {
    this.objectInstanceIds.add(objectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the measured object instances for which performance information is requested to be collected. 
   * @return objectInstanceIds
  **/
  @ApiModelProperty(required = true, value = "Identifiers of the measured object instances for which performance information is requested to be collected. ")
  @NotNull


  public List<String> getObjectInstanceIds() {
    return objectInstanceIds;
  }

  public void setObjectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
  }

  public CreatePmJobRequest subObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
    return this;
  }

  public CreatePmJobRequest addSubObjectInstanceIdsItem(String subObjectInstanceIdsItem) {
    if (this.subObjectInstanceIds == null) {
      this.subObjectInstanceIds = new ArrayList<>();
    }
    this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. 
   * @return subObjectInstanceIds
  **/
  @ApiModelProperty(value = "Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")


  public List<String> getSubObjectInstanceIds() {
    return subObjectInstanceIds;
  }

  public void setSubObjectInstanceIds(List<String> subObjectInstanceIds) {
    this.subObjectInstanceIds = subObjectInstanceIds;
  }

  public CreatePmJobRequest criteria(PmJobCriteria criteria) {
    this.criteria = criteria;
    return this;
  }

  /**
   * Criteria of the collection of performance information. 
   * @return criteria
  **/
  @ApiModelProperty(required = true, value = "Criteria of the collection of performance information. ")
  @NotNull

  @Valid

  public PmJobCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(PmJobCriteria criteria) {
    this.criteria = criteria;
  }

  public CreatePmJobRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * The URI of the endpoint to send the notification to. 
   * @return callbackUri
  **/
  @ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to. ")
  @NotNull


  public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public CreatePmJobRequest authentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }

  /**
   * Authentication parameters to configure the use of Authorization when sending notifications corresponding to this PM job, as defined in clause 8.3.4 of ETSI GS NFV-SOL 013. This attribute shall only be present if the API consumer requires authorization of notifications. 
   * @return authentication
  **/
  @ApiModelProperty(value = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this PM job, as defined in clause 8.3.4 of ETSI GS NFV-SOL 013. This attribute shall only be present if the API consumer requires authorization of notifications. ")

  @Valid

  public SubscriptionAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePmJobRequest createPmJobRequest = (CreatePmJobRequest) o;
    return Objects.equals(this.objectType, createPmJobRequest.objectType) &&
        Objects.equals(this.objectInstanceIds, createPmJobRequest.objectInstanceIds) &&
        Objects.equals(this.subObjectInstanceIds, createPmJobRequest.subObjectInstanceIds) &&
        Objects.equals(this.criteria, createPmJobRequest.criteria) &&
        Objects.equals(this.callbackUri, createPmJobRequest.callbackUri) &&
        Objects.equals(this.authentication, createPmJobRequest.authentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectType, objectInstanceIds, subObjectInstanceIds, criteria, callbackUri, authentication);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePmJobRequest {\n");
    
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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

