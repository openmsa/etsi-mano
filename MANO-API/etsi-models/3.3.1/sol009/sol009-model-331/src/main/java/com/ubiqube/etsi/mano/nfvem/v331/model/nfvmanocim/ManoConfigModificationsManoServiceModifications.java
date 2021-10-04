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
 * ManoConfigModificationsManoServiceModifications
 */
@Validated
public class ManoConfigModificationsManoServiceModifications   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public ManoConfigModificationsManoServiceModifications id(String id) {
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

  public ManoConfigModificationsManoServiceModifications name(String name) {
    this.name = name;
    return this;
  }

  /**
   * If present, this attribute signals modification of the \"name\"  attribute in the \"ManoService\". 
   * @return name
  **/
  @ApiModelProperty(value = "If present, this attribute signals modification of the \"name\"  attribute in the \"ManoService\". ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoConfigModificationsManoServiceModifications description(String description) {
    this.description = description;
    return this;
  }

  /**
   * If present, this attribute signals modification of the  \"description\" attribute in the \"ManoService\". 
   * @return description
  **/
  @ApiModelProperty(value = "If present, this attribute signals modification of the  \"description\" attribute in the \"ManoService\". ")
  
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
    ManoConfigModificationsManoServiceModifications manoConfigModificationsManoServiceModifications = (ManoConfigModificationsManoServiceModifications) o;
    return Objects.equals(this.id, manoConfigModificationsManoServiceModifications.id) &&
        Objects.equals(this.name, manoConfigModificationsManoServiceModifications.name) &&
        Objects.equals(this.description, manoConfigModificationsManoServiceModifications.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoConfigModificationsManoServiceModifications {\n");
    
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
