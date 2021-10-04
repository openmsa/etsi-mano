package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ManoConfigModificationRequestManoServiceModifications
 */
@Validated
public class ManoConfigModificationRequestManoServiceModifications   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public ManoConfigModificationRequestManoServiceModifications id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ManoConfigModificationRequestManoServiceModifications name(String name) {
    this.name = name;
    return this;
  }

  /**
   * New value for the \"name\" attribute in the \"ManoService\".  NOTE: At least one of these attributes shall be provided if requesting  a modification of the NFV-MANO service identified by \"id\". 
   * @return name
  **/
  @ApiModelProperty(value = "New value for the \"name\" attribute in the \"ManoService\".  NOTE: At least one of these attributes shall be provided if requesting  a modification of the NFV-MANO service identified by \"id\". ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoConfigModificationRequestManoServiceModifications description(String description) {
    this.description = description;
    return this;
  }

  /**
   * New value for the \"description\" attribute in the \"ManoService\". NOTE: At least one of these attributes shall be provided if requesting  a modification of the NFV-MANO service identified by \"id\". 
   * @return description
  **/
  @ApiModelProperty(value = "New value for the \"description\" attribute in the \"ManoService\". NOTE: At least one of these attributes shall be provided if requesting  a modification of the NFV-MANO service identified by \"id\". ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoConfigModificationRequestManoServiceModifications manoConfigModificationRequestManoServiceModifications = (ManoConfigModificationRequestManoServiceModifications) o;
    return Objects.equals(this.id, manoConfigModificationRequestManoServiceModifications.id) &&
        Objects.equals(this.name, manoConfigModificationRequestManoServiceModifications.name) &&
        Objects.equals(this.description, manoConfigModificationRequestManoServiceModifications.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoConfigModificationRequestManoServiceModifications {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
