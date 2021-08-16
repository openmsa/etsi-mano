package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm.PmJobCriteria;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a request to create a PM job.  
 */
@Schema(description = "This type represents a request to create a PM job.  ")
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

  public CreatePmJobRequest objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Type of measured object. The applicable measured object type for a measurement  is defined in clause 8.2 of ETSI GS NFV-IFA 031. 
   * @return objectType
   **/
  @Schema(required = true, description = "Type of measured object. The applicable measured object type for a measurement  is defined in clause 8.2 of ETSI GS NFV-IFA 031. ")
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
   * Identifiers of the measured object instance for which performance information  is requested to be collected. This attribute shall contain the identifier of  the instance of the measure object according to their type. See also definitions in clause 8.2 of ETSI GS NFV-IFA 031. If more than one identifier is provided, values shall all refer to measured  object instances of the same type, for which the same criteria is then applicable. 
   * @return objectInstanceIds
   **/
  @Schema(required = true, description = "Identifiers of the measured object instance for which performance information  is requested to be collected. This attribute shall contain the identifier of  the instance of the measure object according to their type. See also definitions in clause 8.2 of ETSI GS NFV-IFA 031. If more than one identifier is provided, values shall all refer to measured  object instances of the same type, for which the same criteria is then applicable. ")
      @NotNull

  @Size(min=1)   public List<String> getObjectInstanceIds() {
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
   * Identifiers of the sub-object instances of the measured object instance for  which performance information is requested to be collected.  May be present if a sub-object is defined in clause 8.2 of ETSI GS NFV-IFA 031 for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\"  attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 8.2 of  ETSI GS NFV-IFA 031 for the related measured object type, measurements will  be taken for all sub-object instances of the measured object instance. 
   * @return subObjectInstanceIds
   **/
  @Schema(description = "Identifiers of the sub-object instances of the measured object instance for  which performance information is requested to be collected.  May be present if a sub-object is defined in clause 8.2 of ETSI GS NFV-IFA 031 for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\"  attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 8.2 of  ETSI GS NFV-IFA 031 for the related measured object type, measurements will  be taken for all sub-object instances of the measured object instance. ")
  
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
        Objects.equals(this.criteria, createPmJobRequest.criteria);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectType, objectInstanceIds, subObjectInstanceIds, criteria);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePmJobRequest {\n");
    
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
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
